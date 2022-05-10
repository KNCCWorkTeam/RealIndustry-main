package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.Menu.GeneratorMenu;
import com.beswk.realindustry.util.BlockEntities;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import java.util.stream.IntStream;

import io.netty.buffer.Unpooled;

public class InternalCombustionEngineEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

    private int burnTimeOdd = 0;

    public InternalCombustionEngineEntity(BlockPos position, BlockState state) {
        super(BlockEntities.INTERNAL_COMBUSTION_ENGINE_ENTITY, position, state);
    }

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
        return new TextComponent("generator");
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new GeneratorMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("internal_combustion_engine");
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
        if (index == 0)
            return false;
        return true;
    }

    private EnergyStorage energyStorage = new EnergyStorage(400000, 200, 200, 0) {
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
    }

    public void reduceBurnTimeOdd(int time) {
        this.burnTimeOdd -= time;
    }

    public boolean ifBurnTimeOddEmpty() {
        return burnTimeOdd == 0;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        BlockEntity entity = level.getBlockEntity(blockPos);
        if (entity instanceof InternalCombustionEngineEntity internalCombustionEngineEntity) {
            if (internalCombustionEngineEntity.ifBurnTimeOddEmpty()) {
                if (!internalCombustionEngineEntity.stacks.get(0).isEmpty()) {
                    /*
                    EnergyStorage before = internalCombustionEngineEntity.energyStorage;
                    if (before.getEnergyStored() + 100 <= 400000) {
                        internalCombustionEngineEntity.energyStorage = new EnergyStorage(400000, 200, 200, before.getEnergyStored() + 100);
                    } else {
                        internalCombustionEngineEntity.energyStorage = new EnergyStorage(400000, 200, 200, 400000);
                    }
                     */
                    internalCombustionEngineEntity.addBurnTimeOdd(100);
                    internalCombustionEngineEntity.stacks.get(0).shrink(1);
                }
            } else {
                internalCombustionEngineEntity.reduceBurnTimeOdd(1);
                EnergyStorage before = internalCombustionEngineEntity.energyStorage;
                if (before.getEnergyStored() + 100 <= 400000) {
                    internalCombustionEngineEntity.energyStorage = new EnergyStorage(400000, 200, 200, before.getEnergyStored() + 10);
                } else {
                    internalCombustionEngineEntity.energyStorage = new EnergyStorage(400000, 200, 200, 400000);
                }
            }
            System.out.println(internalCombustionEngineEntity.energyStorage.getEnergyStored());
        }
    }
}