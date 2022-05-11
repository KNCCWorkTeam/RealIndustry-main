package com.beswk.realindustry.Menu;

import com.beswk.realindustry.util.Menus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class InternalCombustionEngineMenu extends GeneratorMenu{
    public InternalCombustionEngineMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(Menus.INTERNAL_COMBUSTION_MACHINE_MENU, id, inv, extraData);
    }
}
