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

        MenuType<?> INTERNAL_COMBUSTION_MACHINE_MENU = Menus.INTERNAL_COMBUSTION_MACHINE_MENU;
        MenuType<?> GRINDER_MENU = Menus.GRINDER;

        RegistryObject<Item> ENDERITE_HELMET = Items.ENDERITE_HELMET;
        RegistryObject<Item> ENDERITE_CHESTPLATE = Items.ENDERITE_CHESTPLATE;
        RegistryObject<Item> ENDERITE_LEGGINGS = Items.ENDERITE_LEGGINGS;
        RegistryObject<Item> ENDERITE_BOOTS = Items.ENDERITE_BOOTS;

        BlockEntityType<?> INTERNAL_COMBUSTION_ENGINE_ENTITY = BlockEntities.INTERNAL_COMBUSTION_ENGINE_ENTITY;
        BlockEntityType<?> GRINDER_ENTITY = BlockEntities.GRINDER;

        Items.ITEMS.register(RealIndustry.bus);
    }
}
