package com.beswk.realindustry.Blocks;

import com.beswk.realindustry.BlockEntities.Conductionable;
import com.beswk.realindustry.BlockEntities.IEnergyTypeTransferBlockEntity;
import com.beswk.realindustry.BlockEntities.IMachineBlockEntity;
import com.beswk.realindustry.Minecraft.MinecraftBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public abstract class IEnergyTypeTransferBlock extends MinecraftBlock implements EntityBlock {
    public IEnergyTypeTransferBlock() {
        super(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY, 3.5f,3.5f, SoundType.METAL);
    }

    @Override
    public void onRemove(BlockState p_60515_, Level p_60516_, BlockPos p_60517_, BlockState p_60518_, boolean p_60519_) {
        super.onRemove(p_60515_, p_60516_, p_60517_, p_60518_, p_60519_);
        try {
            if (p_60516_.getBlockEntity(p_60517_) instanceof Conductionable conductionable) {
                conductionable.getConductionableChannel().remove(conductionable);
            }
        } catch (Exception e) {
            //
        }
    }
}
