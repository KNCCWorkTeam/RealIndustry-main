package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.BlockEntities;
import com.beswk.realindustry.util.Class.EnergyType;
import com.beswk.realindustry.util.Items;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;

public class GrinderBlockEntity extends IMachineBlockEntity {
    public GrinderBlockEntity(BlockPos position, BlockState state) {
        super(20,10,EnergyType.FE,"grinder", 1000, 200, 200, 0, BlockEntities.GRINDER_ENTITY, position, state);
    }

    @Override
    public ItemStack completeMap(Item item) {
        HashMap<Item,ItemStack> itemItemStackHashMap = new HashMap<>();
        itemItemStackHashMap.put(net.minecraft.world.item.Items.IRON_ORE,new ItemStack(Items.IRON_OXIDE_DUST,3));
        itemItemStackHashMap.put(net.minecraft.world.item.Items.DEEPSLATE_IRON_ORE,new ItemStack(Items.IRON_OXIDE_DUST,4));
        itemItemStackHashMap.put(net.minecraft.world.item.Items.RAW_IRON,new ItemStack(Items.IRON_OXIDE_DUST,2));
        itemItemStackHashMap.put(Items.IRON_OXIDE_DUST,new ItemStack(Items.IRON_DUST,1));
        itemItemStackHashMap.put(net.minecraft.world.item.Items.IRON_INGOT,new ItemStack(Items.IRON_DUST,1));
        itemItemStackHashMap.put(net.minecraft.world.item.Items.COPPER_ORE,new ItemStack(Items.COPPER_DUST,3));
        itemItemStackHashMap.put(net.minecraft.world.item.Items.DEEPSLATE_COPPER_ORE,new ItemStack(Items.COPPER_DUST,4));
        itemItemStackHashMap.put(net.minecraft.world.item.Items.RAW_COPPER,new ItemStack(Items.COPPER_DUST,2));
        itemItemStackHashMap.put(net.minecraft.world.item.Items.COPPER_INGOT,new ItemStack(Items.COPPER_DUST,1));
        return itemItemStackHashMap.get(item);
    }
}