package com.beswk.realindustry.Minecraft;

import com.beswk.realindustry.util.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public abstract class MinecraftItem extends Item {
    public MinecraftItem(CreativeModeTab tab) {
        super(Registry.getItem(tab));
    }
}
