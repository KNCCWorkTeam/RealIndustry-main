package com.beswk.realindustry.Blocks;

import com.beswk.realindustry.BlockEntities.KEGeneratorBlockEntity;
import com.beswk.realindustry.util.BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class KEGeneratorBlock extends IEnergyTypeTransferBlock {
    public KEGeneratorBlock() {
        super();
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
