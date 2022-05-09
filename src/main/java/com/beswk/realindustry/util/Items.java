package com.beswk.realindustry.util;

import com.beswk.realindustry.Items.EnderiteIngot;
import com.beswk.realindustry.Items.EnderiteScrap;
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

    public static final Item PISTON_RING = Registry.registerItem(RealIndustry.INDUSTRY_MATERIAL,"piston_ring",RealIndustry.bus);

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
