package com.beswk.realindustry.Minecraft;

import com.beswk.realindustry.util.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public abstract class MinecraftBlock extends Block {
    public MinecraftBlock(Material material, MaterialColor materialColor, float strength, float resistance, SoundType sound) {
        super(Registry.getBlock(material, materialColor, strength, resistance, sound));
    }
}
