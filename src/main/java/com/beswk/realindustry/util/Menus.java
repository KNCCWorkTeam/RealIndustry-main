package com.beswk.realindustry.util;

import com.beswk.realindustry.Menu.GeneratorMenu;
import com.beswk.realindustry.Menu.GrinderMenu;
import com.beswk.realindustry.Menu.InternalCombustionEngineMenu;
import com.beswk.realindustry.Menu.MachineMenu;
import com.beswk.realindustry.RealIndustry;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Menus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, RealIndustry.MOD_ID);

    public static final MenuType<GeneratorMenu> INTERNAL_COMBUSTION_MACHINE_MENU = Registry.registerMenu("internal_combustion_machine", InternalCombustionEngineMenu::new);
    public static final MenuType<MachineMenu> GRINDER = Registry.registerMenu("grinder", GrinderMenu::new);
}
