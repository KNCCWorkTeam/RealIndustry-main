package com.beswk.realindustry.CreativeTabs;

import com.beswk.realindustry.util.Blocks;
import com.beswk.realindustry.util.Items;
import com.beswk.realindustry.util.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Machine extends CreativeModeTab {
    public Machine() {
        super("machine_tab");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Blocks.CABLE.asItem());
    }
}
