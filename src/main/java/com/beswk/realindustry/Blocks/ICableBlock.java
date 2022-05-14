package com.beswk.realindustry.Blocks;

import com.beswk.realindustry.BlockEntities.Conductionable;
import com.beswk.realindustry.BlockEntities.IAccumulatorBlockEntity;
import com.beswk.realindustry.BlockEntities.ICableBlockEntity;
import com.beswk.realindustry.Minecraft.MinecraftBlock;
import com.beswk.realindustry.util.BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.Nullable;

public abstract class ICableBlock extends MinecraftBlock implements EntityBlock {
    public ICableBlock() {
        super(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY, 3.5f,3.5f, SoundType.METAL);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return blockEntityType == BlockEntities.CABLE_ENTITY ? ICableBlockEntity::tick : null;
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
