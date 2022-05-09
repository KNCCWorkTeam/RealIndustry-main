package com.beswk.realindustry.util;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.ArrayList;
import java.util.List;

public class OreFeature {
    public static PlacedFeature ender_dragon_debris_ore_gen;
    public static void registerOreFeatures() {
        Tag<Block> block = new Tag<>() {
            @Override
            public boolean contains(Block p_13287_) {
                return p_13287_.equals(net.minecraft.world.level.block.Blocks.END_STONE);
            }

            @Override
            public List<Block> getValues() {
                List<Block> list = new ArrayList<>();
                list.add(net.minecraft.world.level.block.Blocks.END_STONE);
                return list;
            }
        };
        OreConfiguration ender_dragon_debris_configure = new OreConfiguration(new TagMatchTest(block), Blocks.ENDER_DRAGON_DEBRIS.defaultBlockState(), 3,1);
        ender_dragon_debris_ore_gen = registerPlacedOreFeature("ore_ender_dragon_debris", new ConfiguredFeature<>(Feature.ORE, ender_dragon_debris_configure),
                CountPlacement.of(2),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(25)));
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> PlacedFeature registerPlacedOreFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(registryName,feature.placed(placementModifiers));
    }

    public static void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.NETHER) {
        } else if (event.getCategory() == Biome.BiomeCategory.THEEND) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ender_dragon_debris_ore_gen);
        } else {
        }
    }
}
