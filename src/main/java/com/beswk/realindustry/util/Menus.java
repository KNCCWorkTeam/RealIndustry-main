package com.beswk.realindustry.util;

import com.beswk.realindustry.Menu.*;
import com.beswk.realindustry.RealIndustry;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Menus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, RealIndustry.MOD_ID);

    public static final MenuType<GeneratorMenu> GENERATOR_MENU = Registry.registerMenu("generator_menu", GeneratorMenu::new);
    public static final MenuType<MachineMenu> MACHINE_MENU = Registry.registerMenu("grinder", MachineMenu::new);
    public static final MenuType<SeniorGeneratorMenu> SENIOR_GENERATOR_MENU = Registry.registerMenu("senior_generator",SeniorGeneratorMenu::new);
    public static final MenuType<AccumulatorMenu> ACCUMULATOR_MENU = Registry.registerMenu("accumulator",AccumulatorMenu::new);
}
