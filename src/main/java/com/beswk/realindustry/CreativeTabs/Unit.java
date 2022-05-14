package com.beswk.realindustry.CreativeTabs;

import com.beswk.realindustry.util.Items;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Unit extends CreativeModeTab {
    public Unit() {
        super("unit_tab");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.UNIT);
    }
}
