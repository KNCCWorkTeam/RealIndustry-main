package com.beswk.realindustry.util;

import com.beswk.realindustry.BlockEntities.InternalCombustionEngineEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntities {
    public static final BlockEntityType<?> INTERNAL_COMBUSTION_ENGINE_ENTITY = Registry.registerBlockEntity("internal_combustion_engine_entity",InternalCombustionEngineEntity::new,Blocks.INTERNAL_COMBUSTION_ENGINE);
}
