package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.Menu.SeniorGeneratorMenu;
import com.beswk.realindustry.util.Class.EnergyType;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class ISeniorGeneratorBlockEntity extends IGeneratorBlockEntity{
    public ISeniorGeneratorBlockEntity(EnergyType type, String displayName, int capacity, int maxReceive, int maxExtract, int energy, int generateAmountPerTick, BlockEntityType<?> entity, BlockPos position, BlockState state) {
        super(type, displayName, capacity, maxReceive, maxExtract, energy, generateAmountPerTick, entity, position, state);
        stacks = NonNullList.withSize(4, ItemStack.EMPTY);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == 0)
            return false;
        if (index == 1)
            return false;
        if (index == 3)
            return false;
        return true;
    }


    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index == 2)
            return false;
        return true;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new SeniorGeneratorMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
    }
}
