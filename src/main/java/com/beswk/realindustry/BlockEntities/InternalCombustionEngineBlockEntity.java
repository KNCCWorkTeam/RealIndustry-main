package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.EnergyStorage;

public class InternalCombustionEngineBlockEntity extends GeneratorBlockEntity{
    public InternalCombustionEngineBlockEntity(BlockPos position, BlockState state) {
        super("internal_combustion_engine",40000,200,200,0,BlockEntities.INTERNAL_COMBUSTION_ENGINE_ENTITY, position, state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        BlockEntity entity = level.getBlockEntity(blockPos);
        if (entity instanceof GeneratorBlockEntity generatorBlockEntity) {
            EnergyStorage before = generatorBlockEntity.energyStorage;
            if (generatorBlockEntity.getBurnTimeOdd()==0) {
                if (before.getEnergyStored() + 100 <= 400000) {
                    if (!generatorBlockEntity.stacks.get(0).isEmpty()) {
                        generatorBlockEntity.addBurnTimeOdd(100);
                        generatorBlockEntity.stacks.get(0).shrink(1);
                    }
                }
            } else {
                generatorBlockEntity.reduceBurnTimeOdd(1);
                if (before.getEnergyStored() + 100 <= 400000) {
                    generatorBlockEntity.energyStorage = new EnergyStorage(400000, 200, 200, before.getEnergyStored() + 10);
                } else {
                    generatorBlockEntity.energyStorage = new EnergyStorage(400000, 200, 200, 400000);
                }
            }
        }
    }
}
