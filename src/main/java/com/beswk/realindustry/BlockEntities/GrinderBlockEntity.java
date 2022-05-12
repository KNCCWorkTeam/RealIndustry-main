package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.Menu.MachineMenu;
import com.beswk.realindustry.util.BlockEntities;
import com.beswk.realindustry.util.Class.EnergyType;
import com.beswk.realindustry.util.Items;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;

public class GrinderBlockEntity extends IMachineBlockEntity {
    public GrinderBlockEntity(BlockPos position, BlockState state) {
        super(5,10,EnergyType.FE,"grinder", 1000, 200, 200, 0, BlockEntities.GRINDER_ENTITY, position, state);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new MachineMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
    }


    @Override
    public ItemStack completeMap(Item item) {
        HashMap<Item,ItemStack> itemItemStackHashMap = new HashMap<>();
        itemItemStackHashMap.put(net.minecraft.world.item.Items.IRON_ORE,new ItemStack(Items.IRON_OXIDE_DUST,3));
        itemItemStackHashMap.put(net.minecraft.world.item.Items.DEEPSLATE_IRON_ORE,new ItemStack(Items.IRON_OXIDE_DUST,4));
        itemItemStackHashMap.put(net.minecraft.world.item.Items.RAW_IRON,new ItemStack(Items.IRON_OXIDE_DUST,2));
        itemItemStackHashMap.put(Items.IRON_OXIDE_DUST,new ItemStack(Items.IRON_DUST,2));
        itemItemStackHashMap.put(net.minecraft.world.item.Items.IRON_INGOT,new ItemStack(Items.IRON_DUST,1));
        return itemItemStackHashMap.get(item);
    }
}