package com.beswk.realindustry.Blocks;

import com.beswk.realindustry.Minecraft.MinecraftBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public abstract class IEnergyTypeTransferBlock extends MinecraftBlock implements EntityBlock {
    public IEnergyTypeTransferBlock() {
        super(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY, 3.5f,3.5f, SoundType.METAL);
    }
}
