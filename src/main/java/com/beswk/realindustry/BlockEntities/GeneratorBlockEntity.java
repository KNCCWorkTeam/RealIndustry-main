package com.beswk.realindustry.BlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public abstract class GeneratorBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    NonNullList<ItemStack> stacks = NonNullList.withSize(1, ItemStack.EMPTY);
    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

    private int burnTimeOdd = 0;
    public ContainerData data = new SimpleContainerData(1);
    public String displayName;
    Item burningFuel;
    int capacity;
    int maxReceive;
    int maxExtract;
    int energy;
    int generateAmountPerTick;

    public GeneratorBlockEntity(String displayName,int capacity,int maxReceive,int maxExtract,int energy,int generateAmountPerTick,BlockEntityType<?> entity, BlockPos position, BlockState state) {
        super(entity, position, state);
        this.displayName = displayName;
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = energy;
        this.generateAmountPerTick = generateAmountPerTick;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        BlockEntity entity = level.getBlockEntity(blockPos);
        if (entity instanceof GeneratorBlockEntity generatorBlockEntity) {
            System.out.println(generatorBlockEntity.fuelEfficient(generatorBlockEntity.stacks.get(0).getItem()));
            EnergyStorage before = generatorBlockEntity.energyStorage;
            if (generatorBlockEntity.getBurnTimeOdd()==0) {
                if (before.getEnergyStored() + generatorBlockEntity.generateAmountPerTick <= 400000) {
                    if (generatorBlockEntity.fuelEfficient(generatorBlockEntity.stacks.get(0).getItem())!=0) {
                        generatorBlockEntity.addBurnTimeOdd(1000);
                        generatorBlockEntity.stacks.get(0).shrink(1);
                        generatorBlockEntity.burningFuel = generatorBlockEntity.stacks.get(0).getItem();
                    }
                }
            } else {
                generatorBlockEntity.reduceBurnTimeOdd(1000 / generatorBlockEntity.fuelEfficient(generatorBlockEntity.burningFuel));
                if (before.getEnergyStored() + generatorBlockEntity.generateAmountPerTick <= 400000) {
                    generatorBlockEntity.energyStorage = new EnergyStorage(generatorBlockEntity.capacity, generatorBlockEntity.maxReceive, generatorBlockEntity.maxExtract, before.getEnergyStored() + generatorBlockEntity.generateAmountPerTick);
                } else {
                    generatorBlockEntity.energyStorage = new EnergyStorage(generatorBlockEntity.capacity, generatorBlockEntity.maxReceive, generatorBlockEntity.maxExtract, generatorBlockEntity.capacity);
                }
            }
        }
    }

    public abstract int fuelEfficient(Item item);


        @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        if (!this.tryLoadLootTable(compound))
            this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compound, this.stacks);
        if (compound.get("energyStorage") instanceof IntTag intTag)
            energyStorage.deserializeNBT(intTag);
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.stacks);
        }
        compound.put("energyStorage", energyStorage.serializeNBT());
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    @Override
    public int getContainerSize() {
        return stacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.stacks)
            if (!itemstack.isEmpty())
                return false;
        return true;
    }

    @Override
    public Component getDefaultName() {
        return new TextComponent(displayName);
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent(displayName);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.stacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> stacks) {
        this.stacks = stacks;
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return IntStream.range(0, this.getContainerSize()).toArray();
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return this.canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return index != 0;
    }

    EnergyStorage energyStorage = new EnergyStorage(400000, 200, 200, 0) {
        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            int retval = super.receiveEnergy(maxReceive, simulate);
            if (!simulate) {
                setChanged();
                level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 2);
            }
            return retval;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            int retval = super.extractEnergy(maxExtract, simulate);
            if (!simulate) {
                setChanged();
                level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 2);
            }
            return retval;
        }
    };

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return handlers[facing.ordinal()].cast();
        if (!this.remove && capability == CapabilityEnergy.ENERGY)
            return LazyOptional.of(() -> energyStorage).cast();
        return super.getCapability(capability, facing);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        for (LazyOptional<? extends IItemHandler> handler : handlers)
            handler.invalidate();
    }

    public void addBurnTimeOdd(int time) {
        this.burnTimeOdd += time;
        this.data.set(0,getBurnTimeOdd());
    }

    public void reduceBurnTimeOdd(int time) {
        this.burnTimeOdd -= time;
        this.data.set(0,getBurnTimeOdd());
    }

    public int getBurnTimeOdd() {
        return burnTimeOdd;
    }
}