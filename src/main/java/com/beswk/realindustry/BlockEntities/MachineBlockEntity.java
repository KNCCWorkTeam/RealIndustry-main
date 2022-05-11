package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.Class.ObjectData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.stream.IntStream;

public abstract class MachineBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(8, ItemStack.EMPTY);

    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

    private int process = 0;
    public ObjectData data = new ObjectData(1,1);
    public String displayName;
    int capacity;
    int maxReceive;
    int maxExtract;
    int energy;

    public MachineBlockEntity(String displayName, int capacity, int maxReceive, int maxExtract, int energy, BlockEntityType<?> entity, BlockPos position, BlockState state) {
        super(entity, position, state);
        this.displayName = displayName;
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = energy;
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);

        if (!this.tryLoadLootTable(compound))
            this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

        ContainerHelper.loadAllItems(compound, this.stacks);

    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);

        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.stacks);
        }

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
            process = 0;
            complete();
        }
    }

    public int getProcessOdd() {
        return process;
    }

    public Item getInputItem() {
        Item item = null;
        if (!stacks.get(0).isEmpty()) {
            item = stacks.get(0).getItem();
        } else if (!stacks.get(1).isEmpty()) {
            item = stacks.get(1).getItem();
        } else if (!stacks.get(2).isEmpty()) {
            item = stacks.get(2).getItem();
        } else if (!stacks.get(3).isEmpty()) {
            item = stacks.get(3).getItem();
        } else if (!stacks.get(4).isEmpty()) {
            item = stacks.get(4).getItem();
        } else if (!stacks.get(5).isEmpty()) {
            item = stacks.get(5).getItem();
        }
        return item;
    }

    public void complete() {
        Item item = null;
        if (!stacks.get(0).isEmpty()) {
            stacks.get(0).shrink(1);
            item = stacks.get(0).getItem();
        } else if (!stacks.get(1).isEmpty()) {
            stacks.get(1).shrink(1);
            item = stacks.get(1).getItem();
        } else if (!stacks.get(2).isEmpty()) {
            stacks.get(2).shrink(1);
            item = stacks.get(2).getItem();
        } else if (!stacks.get(3).isEmpty()) {
            stacks.get(3).shrink(1);
            item = stacks.get(3).getItem();
        } else if (!stacks.get(4).isEmpty()) {
            stacks.get(4).shrink(1);
            item = stacks.get(4).getItem();
        } else if (!stacks.get(5).isEmpty()) {
            stacks.get(5).shrink(1);
            item = stacks.get(5).getItem();
        }
        if (item!=null) {
            ItemStack output = completeMap(item);
            if (output!=null) {
                if (stacks.get(6).is(output.getItem())) {
                    stacks.get(6).setCount(stacks.get(6).getCount() + output.getCount());
                } else if (stacks.get(6).isEmpty()) {
                    stacks.set(6, output);
                } else if (stacks.get(7).is(output.getItem())) {
                    stacks.get(7).setCount(stacks.get(7).getCount() + output.getCount());
                } else if (stacks.get(7).isEmpty()) {
                    stacks.set(7, output);
                }
            }
        }
    }

    public abstract ItemStack completeMap(Item item);
}
