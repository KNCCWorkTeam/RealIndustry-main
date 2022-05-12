package com.beswk.realindustry.util;

import com.beswk.realindustry.BlockEntities.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntities {
    public static final BlockEntityType<?> INTERNAL_COMBUSTION_ENGINE_ENTITY = Registry.registerBlockEntity("internal_combustion_engine_entity", InternalCombustionEngineBlockEntity::new,Blocks.INTERNAL_COMBUSTION_ENGINE);
    public static final BlockEntityType<?> GRINDER_ENTITY = Registry.registerBlockEntity("grinder_entity", GrinderBlockEntity::new,Blocks.GRINDER);
    public static final BlockEntityType<?> CABLE_ENTITY = Registry.registerBlockEntity("cable_entity", CableBlockEntity::new,Blocks.CABLE);
    public static final BlockEntityType<?> KE_GENERATOR_ENTITY = Registry.registerBlockEntity("ke_generator_entity", KEGeneratorBlockEntity::new,Blocks.KE_GENERATOR);
}
