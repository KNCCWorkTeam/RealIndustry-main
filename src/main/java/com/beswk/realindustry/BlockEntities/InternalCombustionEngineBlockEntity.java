package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.BlockEntities;
import com.beswk.realindustry.util.Class.EnergyType;
import com.beswk.realindustry.util.Items;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;

public class InternalCombustionEngineBlockEntity extends ISeniorGeneratorBlockEntity {
    public InternalCombustionEngineBlockEntity(BlockPos position, BlockState state) {
        super(EnergyType.KE,"internal_combustion_engine",1000,200,200,0,10,BlockEntities.INTERNAL_COMBUSTION_ENGINE_ENTITY, position, state);
    }

    @Override
    public int fuelEfficient(Item item) {
        HashMap<Item,Integer> itemIntegerHashMap = new HashMap<>();
        itemIntegerHashMap.put(Items.FUEL_OIL_UNIT,1000);
        itemIntegerHashMap.put(Items.DIESEL_OIL_UNIT,2000);
        return itemIntegerHashMap.get(item)==null?-1:itemIntegerHashMap.get(item);
    }


    @Override
    public Item fuelBurnEndProduce(Item item) {
        HashMap<Item,Item> itemIntegerHashMap = new HashMap<>();
        itemIntegerHashMap.put(Items.FUEL_OIL_UNIT,Items.UNIT);
        itemIntegerHashMap.put(Items.DIESEL_OIL_UNIT,Items.UNIT);
        return itemIntegerHashMap.get(item);
    }
}
