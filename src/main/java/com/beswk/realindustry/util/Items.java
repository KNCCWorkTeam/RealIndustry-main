package com.beswk.realindustry.util;

import com.beswk.realindustry.Items.EnderiteIngot;
import com.beswk.realindustry.Items.EnderiteScrap;
import com.beswk.realindustry.Minecraft.MinecraftItem;
import com.beswk.realindustry.RealIndustry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RealIndustry.MOD_ID);
    
    public static final Item ENDERITE_INGOT = Registry.registerItem(new EnderiteIngot(),"enderite_ingot", RealIndustry.bus);
    public static final Item ENDERITE_SCRAP = Registry.registerItem(new EnderiteScrap(),"enderite_scrap", RealIndustry.bus);

    public static final Item IRON_HAMMER = Registry.registerItem(new Item(Registry.getItem(RealIndustry.TOOL).stacksTo(16)), "iron_hammer", RealIndustry.bus);
    public static final Item IRON_CUTTER = Registry.registerItem(new Item(Registry.getItem(RealIndustry.TOOL).stacksTo(16)),"iron_cutter", RealIndustry.bus);

    public static final Item IRON_DUST = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"iron_dust",RealIndustry.bus);
    public static final Item IRON_PLATE = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"iron_plate", RealIndustry.bus);
    public static final Item IRON_OXIDE_DUST = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"fe2o3",RealIndustry.bus);
    public static final Item COPPER_DUST = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"copper_dust",RealIndustry.bus);
    public static final Item COPPER_COIL = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"copper_coil", RealIndustry.bus);
    public static final Item COPPER_PLATE = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"copper_plate",RealIndustry.bus);
    public static final Item COPPER_WIRE = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"copper_wire", RealIndustry.bus);

    public static final Item PISTON_RING = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"piston_ring",RealIndustry.bus);
    public static final Item PISTON_RING_SET = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"piston_ring_set",RealIndustry.bus);
    public static final Item PISTON_PIN = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"piston_pin",RealIndustry.bus);
    public static final Item PISTON_PIN_RING = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"piston_pin_ring",RealIndustry.bus);
    public static final Item PISTON = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"piston",RealIndustry.bus);
    public static final Item CRANK_TRAIN = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"crank_train",RealIndustry.bus);
    public static final Item CRANK_TRAIN_AND_PISTON = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"crank_train_and_piston", RealIndustry.bus);
    public static final Item SPARK_PLUG = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"spark_plug", RealIndustry.bus);
    public static final Item IRON_GEAR = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"iron_gear", RealIndustry.bus);
    public static final Item GRINDER_GEAR = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"grinder_gear", RealIndustry.bus);
    public static final Item MOTOR = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"motor", RealIndustry.bus);
    public static final Item ELECTROMAGNET = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"electromagnet", RealIndustry.bus);
    public static final Item OBSIDIAN_ALLOY = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"obsidian_alloy", RealIndustry.bus);
    public static final Item IRON_FAN = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"iron_fan", RealIndustry.bus);

    public static final Item UNIT = Registry.registerItem(RealIndustry.UNIT,"unit", RealIndustry.bus);
    public static final Item WATER_UNIT = Registry.registerItem(RealIndustry.UNIT,"water_unit", RealIndustry.bus);
    public static final Item FUEL_OIL_UNIT = Registry.registerItem(RealIndustry.UNIT,"fuel_oil_unit", RealIndustry.bus);
    public static final Item DIESEL_OIL_UNIT = Registry.registerItem(RealIndustry.UNIT,"diesel_oil_unit", RealIndustry.bus);

    public static final Item ENDERITE_SWORD = Registry.registerItem(new SwordItem(Tiers.Enderite, 3,-2.4f, Registry.getItem(RealIndustry.TOOL)),"enderite_sword",RealIndustry.bus);
    public static final Item ENDERITE_HOE = Registry.registerItem(new HoeItem(Tiers.Enderite, -4,0, Registry.getItem(RealIndustry.TOOL)),"enderite_hoe",RealIndustry.bus);
    public static final Item ENDERITE_AXE = Registry.registerItem(new AxeItem(Tiers.Enderite, 5,-3, Registry.getItem(RealIndustry.TOOL)),"enderite_axe",RealIndustry.bus);
    public static final Item ENDERITE_PICKAXE = Registry.registerItem(new PickaxeItem(Tiers.Enderite, 1,-2.8f, Registry.getItem(RealIndustry.TOOL)),"enderite_pickaxe",RealIndustry.bus);
    public static final Item ENDERITE_SHOVEL = Registry.registerItem(new ShovelItem(Tiers.Enderite, 1.5f,-3, Registry.getItem(RealIndustry.TOOL)),"enderite_shovel",RealIndustry.bus);

    public static final RegistryObject<Item> ENDERITE_HELMET = ITEMS.register("enderite_helmet",
            () -> new ArmorItem(Material.ENDERITE, EquipmentSlot.HEAD, new Item.Properties().tab(RealIndustry.TOOL)));

    public static final RegistryObject<Item> ENDERITE_CHESTPLATE = ITEMS.register("enderite_chestplate",
            () -> new ArmorItem(Material.ENDERITE, EquipmentSlot.CHEST, new Item.Properties().tab(RealIndustry.TOOL)));

    public static final RegistryObject<Item> ENDERITE_LEGGINGS = ITEMS.register("enderite_leggings",
            () -> new ArmorItem(Material.ENDERITE, EquipmentSlot.LEGS, new Item.Properties().tab(RealIndustry.TOOL)));

    public static final RegistryObject<Item> ENDERITE_BOOTS = ITEMS.register("enderite_boots",
            () -> new ArmorItem(Material.ENDERITE, EquipmentSlot.FEET, new Item.Properties().tab(RealIndustry.TOOL)));

    public static final Item AN_UNBREAKABLE_ALLIANCE = Registry.registerItem(new RecordItem(0, new SoundEvent(new ResourceLocation("realindustry","13")),
            new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).rarity(Rarity.RARE)),"national_anthem_of_ussr",RealIndustry.bus);
}
