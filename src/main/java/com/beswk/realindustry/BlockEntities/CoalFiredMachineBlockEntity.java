package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.BlockEntities;
import com.beswk.realindustry.util.Class.EnergyType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;

public class CoalFiredMachineBlockEntity extends IGeneratorBlockEntity{
    public CoalFiredMachineBlockEntity(BlockPos position, BlockState state) {
        super(EnergyType.HE,"coal_fired_machine",1000,200,200,0,10, BlockEntities.COAL_FIRED_MACHINE_ENTITY, position, state);
    }

    @Override
    public int fuelEfficient(Item item) {
        HashMap<Item,Integer> itemIntegerHashMap = new HashMap<>();
        itemIntegerHashMap.put(Items.COAL,100);
        itemIntegerHashMap.put(Items.COAL_BLOCK,1000);
        itemIntegerHashMap.put(Items.CHARCOAL,1000);
        return itemIntegerHashMap.get(item)==null?-1:itemIntegerHashMap.get(item);
    }
}
