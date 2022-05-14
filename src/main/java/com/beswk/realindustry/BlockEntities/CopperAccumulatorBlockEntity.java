package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.BlockEntities;
import com.beswk.realindustry.util.Class.EnergyType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class CopperAccumulatorBlockEntity extends IAccumulatorBlockEntity{
    public CopperAccumulatorBlockEntity(BlockPos position, BlockState state) {
        super(BlockEntities.COPPER_ACCUMULATOR_ENTITY, EnergyType.FE, "copper_accumulator", 20000, 200, 200, 0, position, state);
    }
}
