package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.Menu.MachineMenu;
import com.beswk.realindustry.util.Class.ConductionableChannel;
import com.beswk.realindustry.util.Class.EnergyType;
import com.beswk.realindustry.util.Class.ObjectData;
import com.beswk.realindustry.util.Class.TypeEnergyStorage;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public abstract class IMachineBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer,Conductionable {
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(8, ItemStack.EMPTY);

    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

    int energyCost;
    int speed;
    private int process = 0;
    public ObjectData data = new ObjectData(1,1);
    public String displayName;
    public TypeEnergyStorage energyStorage;
    ConductionableChannel conductionables;
    @Override
    public ConductionableChannel getConductionableChannel() {
        return conductionables;
    }

    public IMachineBlockEntity(int energyCost,int speed,EnergyType type, String displayName, int capacity, int maxReceive, int maxExtract, int energy, BlockEntityType<?> entity, BlockPos position, BlockState state) {
        super(entity, position, state);
        this.energyCost = energyCost;
        this.speed = speed;
        this.displayName = displayName;
        this.energyStorage = new TypeEnergyStorage(type,capacity, maxReceive, maxExtract, energy) {
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
    public AbstractContainerMenu createMenu(int p_59637_, Inventory p_59638_) {
        return new MachineMenu(p_59637_, p_59638_,new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
    }

    @Override
    public TypeEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    @Override
    public EnergyExportType getEnergyExportType() {
        return EnergyExportType.MACHINE;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        BlockEntity entity = level.getBlockEntity(blockPos);
        if (entity instanceof IMachineBlockEntity machineBlockEntity) {
            machineBlockEntity.conductionables = new ConductionableChannel(level,blockPos);
            if (machineBlockEntity.completeMap(machineBlockEntity.getInputItem())!=null) {
                if (machineBlockEntity.energyStorage.getEnergyStored() >= 5) {
                    machineBlockEntity.energyStorage.processReduceEnergy(machineBlockEntity,machineBlockEntity.energyCost,machineBlockEntity.speed);
                }
            }
        }
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
        if (index == 6)
            return false;
        if (index == 7)
            return false;
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
        if (index == 1)
            return false;
        if (index == 2)
            return false;
        if (index == 3)
            return false;
        if (index == 4)
            return false;
        if (index == 5)
            return false;
        return true;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return handlers[facing.ordinal()].cast();

        return super.getCapability(capability, facing);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        for (LazyOptional<? extends IItemHandler> handler : handlers)
            handler.invalidate();
    }


    public void addProcess(int time) {
        this.process += time;
        this.data.set(0,getProcessOdd());
        if (process>=1000) {
            complete();
            process = 0;
            this.data.set(0,getProcessOdd());
        }
    }

    public int getProcessOdd() {
        return process;
    }

    public Item getInputItem() {
        if (getInputItemIndex()==-1) return null;
        return stacks.get(getInputItemIndex()).getItem();
    }

    public int getInputItemIndex() {
        if (!stacks.get(0).isEmpty()) {
            return 0;
        } else if (!stacks.get(1).isEmpty()) {
            return 1;
        } else if (!stacks.get(2).isEmpty()) {
            return 2;
        } else if (!stacks.get(3).isEmpty()) {
            return 3;
        } else if (!stacks.get(4).isEmpty()) {
            return 4;
        } else if (!stacks.get(5).isEmpty()) {
            return 5;
        }
        return -1;
    }

    public void complete() {
        if (getInputItem()!=null) {
            ItemStack output = completeMap(getInputItem());
            if (output!=null) {
                if (stacks.get(6).is(output.getItem())) {
                    stacks.get(6).setCount(stacks.get(6).getCount() + output.getCount());
                    stacks.get(getInputItemIndex()).shrink(1);
                } else if (stacks.get(6).isEmpty()) {
                    stacks.set(6, output);
                    stacks.get(getInputItemIndex()).shrink(1);
                } else if (stacks.get(7).is(output.getItem())) {
                    stacks.get(7).setCount(stacks.get(7).getCount() + output.getCount());
                    stacks.get(getInputItemIndex()).shrink(1);
                } else if (stacks.get(7).isEmpty()) {
                    stacks.set(7, output);
                    stacks.get(getInputItemIndex()).shrink(1);
                }
            }
        }
    }

    public abstract ItemStack completeMap(Item item);
}
