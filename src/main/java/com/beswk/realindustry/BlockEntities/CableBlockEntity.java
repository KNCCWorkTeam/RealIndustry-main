package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.BlockEntities;
import com.beswk.realindustry.util.Class.TypeEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class CableBlockEntity extends ICableBlockEntity {
    public CableBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(50, BlockEntities.CABLE_ENTITY, p_155229_, p_155230_);
    }
}