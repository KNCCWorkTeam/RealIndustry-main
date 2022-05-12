package com.beswk.realindustry.Blocks;

import com.beswk.realindustry.BlockEntities.InternalCombustionEngineBlockEntity;
import com.beswk.realindustry.util.BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class InternalCombustionEngineBlock extends GeneratorBlock {
    public InternalCombustionEngineBlock() {
        super("internal_combustion_engine");
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return blockEntityType == BlockEntities.INTERNAL_COMBUSTION_ENGINE_ENTITY ? InternalCombustionEngineBlockEntity::tick : null;
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new InternalCombustionEngineBlockEntity(pos, state);
    }

    @Override
    AbstractContainerMenu createMenu(Level world,BlockPos pos,int integer,Inventory inventory,Player player) {
        if (world.getBlockEntity(pos) instanceof InternalCombustionEngineBlockEntity internalCombustionEngineBlockEntity) {
            return internalCombustionEngineBlockEntity.createMenu(integer,inventory);
        }
        return null;
    }
}
