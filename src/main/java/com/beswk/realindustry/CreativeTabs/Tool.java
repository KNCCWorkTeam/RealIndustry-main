package com.beswk.realindustry.CreativeTabs;

import com.beswk.realindustry.util.Items;
import com.beswk.realindustry.util.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Tool extends CreativeModeTab {
    public Tool() {
        super("tool_tab");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.ENDERITE_SWORD);
    }
}
