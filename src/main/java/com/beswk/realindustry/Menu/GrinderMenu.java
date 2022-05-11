package com.beswk.realindustry.Menu;

import com.beswk.realindustry.util.Menus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class GrinderMenu extends MachineMenu{
    public GrinderMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(Menus.GRINDER,id, inv, extraData);
    }
}
