package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class KEGeneratorBlockEntity extends IEnergyTypeTransferBlockEntity {
    public KEGeneratorBlockEntity(BlockPos position, BlockState state) {
        super(BlockEntities.KE_GENERATOR_ENTITY, position, state);
    }
}
