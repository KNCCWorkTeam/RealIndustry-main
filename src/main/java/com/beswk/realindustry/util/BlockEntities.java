package com.beswk.realindustry.util;

import com.beswk.realindustry.BlockEntities.CableBlockEntity;
import com.beswk.realindustry.BlockEntities.GrinderBlockEntity;
import com.beswk.realindustry.BlockEntities.InternalCombustionEngineBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntities {
    public static final BlockEntityType<?> INTERNAL_COMBUSTION_ENGINE_ENTITY = Registry.registerBlockEntity("internal_combustion_engine_entity", InternalCombustionEngineBlockEntity::new,Blocks.INTERNAL_COMBUSTION_ENGINE);
    public static final BlockEntityType<?> GRINDER_ENTITY = Registry.registerBlockEntity("grinder_entity", GrinderBlockEntity::new,Blocks.GRINDER);
    public static final BlockEntityType<?> CABLE_ENTITY = Registry.registerBlockEntity("cable_entity", CableBlockEntity::new,Blocks.CABLE);
}
