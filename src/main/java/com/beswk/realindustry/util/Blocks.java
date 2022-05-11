package com.beswk.realindustry.util;

import com.beswk.realindustry.Blocks.CableBlock;
import com.beswk.realindustry.Blocks.GeneratorBlock;
import com.beswk.realindustry.Blocks.GrinderBlock;
import com.beswk.realindustry.Blocks.InternalCombustionEngineBlock;
import com.beswk.realindustry.RealIndustry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class Blocks {
    public static final Block ENDERITE_BLOCK = Registry.registerBlock(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY, 100f,2400f, SoundType.METAL,"enderite_block",RealIndustry.MATERIAL,RealIndustry.bus);
    public static final Block ENDER_DRAGON_DEBRIS = Registry.registerBlock(Material.STONE, MaterialColor.SAND, 40f, 2500f, SoundType.ANCIENT_DEBRIS,"ender_dragon_debris",RealIndustry.MATERIAL,RealIndustry.bus);
    public static final Block INTERNAL_COMBUSTION_ENGINE = Registry.registerBlock(new InternalCombustionEngineBlock(),"internal_combustion_engine",RealIndustry.MACHINE,RealIndustry.bus);
    public static final Block GRINDER = Registry.registerBlock(new GrinderBlock(),"grinder",RealIndustry.MACHINE,RealIndustry.bus);
    public static final Block CABLE = Registry.registerBlock(new CableBlock(),"cable",RealIndustry.MACHINE,RealIndustry.bus);
}
