package com.beswk.realindustry.util;

import com.beswk.realindustry.RealIndustry;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class Initialize {
    public Initialize() {
        Block ENDERITE_BLOCK = Blocks.ENDERITE_BLOCK;
        Block ENDER_DRAGON_DEBRIS = Blocks.ENDER_DRAGON_DEBRIS;
        Block INTERNAL_COMBUSTION_ENGINE = Blocks.INTERNAL_COMBUSTION_ENGINE;
        Block GRINDER = Blocks.GRINDER;

        Item ENDERITE_INGOT = Items.ENDERITE_INGOT;
        Item ENDERITE_SCRAP = Items.ENDERITE_SCRAP;
        Item ENDERITE_SWORD = Items.ENDERITE_SWORD;
        Item ENDERITE_HOE = Items.ENDERITE_HOE;
        Item ENDERITE_AXE = Items.ENDERITE_AXE;
        Item ENDERITE_PICKAXE = Items.ENDERITE_PICKAXE;
        Item ENDERITE_SHOVEL = Items.ENDERITE_SHOVEL;
        Item AN_UNBREAKABLE_ALLIANCE = Items.AN_UNBREAKABLE_ALLIANCE;
        Item IRON_DUST = Items.IRON_DUST;
        Item IRON_OXIDE_DUST = Items.IRON_OXIDE_DUST;
        Item IRON_HAMMER = Items.IRON_HAMMER;
        Item IRON_CUTTER = Items.IRON_CUTTER;
        Item IRON_PLATE = Items.IRON_PLATE;
        Item PISTON_RING = Items.PISTON_RING;
        Item PISTON_RING_SET = Items.PISTON_RING_SET;
        Item PISTON_PIN = Items.PISTON_PIN;
        Item PISTON_PIN_RING = Items.PISTON_PIN_RING;
        Item PISTON = Items.PISTON;
        Item CRANK_TRAIN = Items.CRANK_TRAIN;
        Item CRANK_TRAIN_AND_PISTON = Items.CRANK_TRAIN_AND_PISTON;
        Item SPARK_PLUG = Items.SPARK_PLUG;
        Item IRON_GEAR = Items.IRON_GEAR;
        Item GRINDER_GEAR = Items.GRINDER_GEAR;
        Item MOTOR = Items.MOTOR;
        Item COPPER_COIL = Items.COPPER_COIL;
        Item ELECTROMAGNET = Items.ELECTROMAGNET;
        Item COPPER_PLATE = Items.COPPER_PLATE;
        Item COPPER_WIRE = Items.COPPER_WIRE;

        MenuType<?> SENIOR_GENERATOR_MENU = Menus.SENIOR_GENERATOR_MENU;
        MenuType<?> GENERATOR_MENU = Menus.GENERATOR_MENU;
        MenuType<?> MACHINE_MENU = Menus.MACHINE_MENU;

        RegistryObject<Item> ENDERITE_HELMET = Items.ENDERITE_HELMET;
        RegistryObject<Item> ENDERITE_CHESTPLATE = Items.ENDERITE_CHESTPLATE;
        RegistryObject<Item> ENDERITE_LEGGINGS = Items.ENDERITE_LEGGINGS;
        RegistryObject<Item> ENDERITE_BOOTS = Items.ENDERITE_BOOTS;

        BlockEntityType<?> INTERNAL_COMBUSTION_ENGINE_ENTITY = BlockEntities.INTERNAL_COMBUSTION_ENGINE_ENTITY;
        BlockEntityType<?> GRINDER_ENTITY = BlockEntities.GRINDER_ENTITY;

        Items.ITEMS.register(RealIndustry.bus);
    }
}
