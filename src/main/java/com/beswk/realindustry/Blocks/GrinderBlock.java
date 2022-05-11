package com.beswk.realindustry.Blocks;

import com.beswk.realindustry.BlockEntities.GeneratorBlockEntity;
import com.beswk.realindustry.BlockEntities.GrinderBlockEntities;
import com.beswk.realindustry.util.BlockEntities;
import com.beswk.realindustry.util.Class.MachineComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class GrinderBlock extends MachineBlock{
    public GrinderBlock() {
        super("grinder");
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return blockEntityType == BlockEntities.GRINDER ? GrinderBlockEntities::tick : null;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GrinderBlockEntities(pos, state);
    }

    @Override
    public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        super.use(blockstate, world, pos, entity, hand, hit);
        if (entity instanceof ServerPlayer player) {
            player.openMenu(new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return new MachineComponent(displayName,world.getBlockEntity(pos));
                }

                @Nullable
                @Override
                public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
                    if (world.getBlockEntity(pos) instanceof GrinderBlockEntities grinderBlockEntities) {
                        return grinderBlockEntities.createMenu(p_39954_,p_39955_);
                    }
                    return null;
                }
            });
        }
        return InteractionResult.SUCCESS;
    }
}
