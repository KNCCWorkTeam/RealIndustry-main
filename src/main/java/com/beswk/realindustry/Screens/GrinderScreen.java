package com.beswk.realindustry.Screens;

import com.beswk.realindustry.Menu.MachineMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class GrinderScreen extends MachineScreen{
    public GrinderScreen(MachineMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
    }
}
