package com.beswk.realindustry.util;

import com.beswk.realindustry.BlockEntities.GeneratorBlockEntity;
import com.beswk.realindustry.BlockEntities.InternalCombustionEngineBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntities {
    public static final BlockEntityType<?> INTERNAL_COMBUSTION_ENGINE_ENTITY = Registry.registerBlockEntity("internal_combustion_engine_entity", InternalCombustionEngineBlockEntity::new,Blocks.INTERNAL_COMBUSTION_ENGINE);
}
