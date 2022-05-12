package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.Menu.GrinderMenu;
import com.beswk.realindustry.util.BlockEntities;
import com.beswk.realindustry.util.Class.EnergyType;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;

public class GrinderBlockEntity extends IMachineBlockEntity {
    public GrinderBlockEntity(BlockPos position, BlockState state) {
        super(EnergyType.FE,"grinder", 1000, 200, 200, 0, BlockEntities.GRINDER_ENTITY, position, state);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new GrinderMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
    }


    @Override
    public ItemStack completeMap(Item item) {
        HashMap<Item,ItemStack> itemItemStackHashMap = new HashMap<>();
        itemItemStackHashMap.put(Items.IRON_ORE,new ItemStack(Items.IRON_INGOT,2));
        return itemItemStackHashMap.get(item);
    }
}