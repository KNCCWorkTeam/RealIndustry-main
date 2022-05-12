package com.beswk.realindustry.Blocks;

import com.beswk.realindustry.BlockEntities.KEGeneratorBlockEntity;
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

public class KEGeneratorBlock extends MinecraftBlock implements EntityBlock {
    public KEGeneratorBlock() {
        super(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY, 3.5f,3.5f, SoundType.METAL);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return blockEntityType == BlockEntities.KE_GENERATOR_ENTITY ? KEGeneratorBlockEntity::tick : null;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new KEGeneratorBlockEntity(pos, state);
    }
}
