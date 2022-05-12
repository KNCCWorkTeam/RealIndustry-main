package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.Class.EnergyType;
import com.beswk.realindustry.util.Class.TypeEnergyStorage;
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
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public abstract class IGeneratorBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer,Conductionable {
    NonNullList<ItemStack> stacks = NonNullList.withSize(1, ItemStack.EMPTY);
    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

    private int burnTimeOdd = 0;
    public ContainerData data = new SimpleContainerData(1);
    public String displayName;
    int efficient;
    public TypeEnergyStorage energyStorage;
    int capacity;
    int maxReceive;
    int maxExtract;
    int energy;
    int generateAmountPerTick;

    public IGeneratorBlockEntity(EnergyType type, String displayName, int capacity, int maxReceive, int maxExtract, int energy, int generateAmountPerTick, BlockEntityType<?> entity, BlockPos position, BlockState state) {
        super(entity, position, state);
        this.displayName = displayName;
        this.generateAmountPerTick = generateAmountPerTick;
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = energy;
        energyStorage = new TypeEnergyStorage(type,capacity, maxReceive, maxExtract, energy) {
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
    }

    @Override
    public TypeEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    @Override
    public EnergyExportType getEnergyExportType() {
        return EnergyExportType.GENERATOR_ELECTRICITY;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        BlockEntity entity = level.getBlockEntity(blockPos);
        if (entity instanceof IGeneratorBlockEntity iGeneratorBlockEntity) {
            if (iGeneratorBlockEntity.getBurnTimeOdd()==0) {
                if (iGeneratorBlockEntity.fuelEfficient(iGeneratorBlockEntity.stacks.get(0).getItem())!=-1) {
                    iGeneratorBlockEntity.addBurnTimeOdd(1000);
                    iGeneratorBlockEntity.stacks.get(0).shrink(1);
                    iGeneratorBlockEntity.efficient = iGeneratorBlockEntity.fuelEfficient(iGeneratorBlockEntity.stacks.get(0).getItem());
                }
            } else {
                if (iGeneratorBlockEntity.efficient!=-1) {
                    iGeneratorBlockEntity.reduceBurnTimeOdd(1000 / iGeneratorBlockEntity.efficient);
                    iGeneratorBlockEntity.energyStorage.generatorAddEnergy(iGeneratorBlockEntity, iGeneratorBlockEntity.generateAmountPerTick);
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
        if (burnTimeOdd>1000) {
            reduceBurnTimeOdd(getBurnTimeOdd()-1000);
        }
        this.data.set(0,getBurnTimeOdd());
    }

    public void reduceBurnTimeOdd(int time) {
        this.burnTimeOdd -= time;
        if (burnTimeOdd<=0)
            addBurnTimeOdd(-getBurnTimeOdd());
        this.data.set(0,getBurnTimeOdd());
    }

    public int getBurnTimeOdd() {
        return burnTimeOdd;
    }
}