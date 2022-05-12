package com.beswk.realindustry.util;

import com.beswk.realindustry.BlockEntities.CableBlockEntity;
import com.beswk.realindustry.Blocks.*;
import com.beswk.realindustry.RealIndustry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.NotNull;

public class Blocks {
    public static final Block ENDERITE_BLOCK = Registry.registerBlock(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY, 100f,2400f, SoundType.METAL,"enderite_block",RealIndustry.MATERIAL,RealIndustry.bus);
    public static final Block ENDER_DRAGON_DEBRIS = Registry.registerBlock(Material.STONE, MaterialColor.SAND, 40f, 2500f, SoundType.ANCIENT_DEBRIS,"ender_dragon_debris",RealIndustry.MATERIAL,RealIndustry.bus);
    public static final Block INTERNAL_COMBUSTION_ENGINE = Registry.registerBlock(new InternalCombustionEngineBlock(),"internal_combustion_engine",RealIndustry.MACHINE,RealIndustry.bus);
    public static final Block GRINDER = Registry.registerBlock(new GrinderBlock(), "grinder", RealIndustry.MACHINE, RealIndustry.bus);
    public static final Block KE_GENERATOR = Registry.registerBlock(new KEGeneratorBlock(),"ke_generator",RealIndustry.MACHINE,RealIndustry.bus);
    public static final Block STEAM_TURBINE = Registry.registerBlock(new SteamTurbineBlock(),"steam_turbine",RealIndustry.MACHINE,RealIndustry.bus);
    public static final Block COAL_FIRED_MACHINE = Registry.registerBlock(new CoalFiredMachineBlock(),"coal_fired_machine",RealIndustry.MACHINE,RealIndustry.bus);
    public static final Block CABLE = Registry.registerBlock(new ICableBlock() {
        @Override
        public @NotNull BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
            return new CableBlockEntity(p_153215_,p_153216_);
        }
    }, "cable", RealIndustry.MACHINE, RealIndustry.bus);
}
