package com.beswk.realindustry.Blocks;

import com.beswk.realindustry.BlockEntities.CopperAccumulatorBlockEntity;
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

public class CopperAccumulatorBlock extends IAccumulatorBlock{
    public CopperAccumulatorBlock() {
        super("copper_accumulator");
    }

    @Override
    AbstractContainerMenu createMenu(Level world, BlockPos pos, int integer, Inventory inventory, Player player) {
        if (world.getBlockEntity(pos) instanceof CopperAccumulatorBlockEntity copperAccumulatorBlockEntity) {
            return copperAccumulatorBlockEntity.createMenu(integer,inventory);
        }
        return null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new CopperAccumulatorBlockEntity(p_153215_,p_153216_);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        return p_153214_ == BlockEntities.COPPER_ACCUMULATOR_ENTITY ? CopperAccumulatorBlockEntity::tick : null;
    }
}
