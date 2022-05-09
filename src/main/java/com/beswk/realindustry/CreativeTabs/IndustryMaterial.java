package com.beswk.realindustry.CreativeTabs;

import com.beswk.realindustry.util.Items;
import com.beswk.realindustry.util.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class IndustryMaterial extends CreativeModeTab {
    public IndustryMaterial() {
        super("industry_material_tab");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.PISTON_RING);
    }
}
