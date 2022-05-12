package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.Class.EnergyType;
import com.beswk.realindustry.util.Class.TypeEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class IAutoOutputElectricityGeneratorBlockEntity extends IGeneratorBlockEntity{
    public IAutoOutputElectricityGeneratorBlockEntity(EnergyType type , String displayName, int capacity, int maxReceive, int maxExtract, int energy, int generateAmountPerTick, BlockEntityType<?> entity, BlockPos position, BlockState state) {
        super(type, displayName, capacity, maxReceive, maxExtract, energy, generateAmountPerTick, entity, position, state);
    }

    @Override
    public EnergyExportType getEnergyExportType() {
        return EnergyExportType.GENERATOR_NOT_ELECTRICITY;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        IGeneratorBlockEntity.tick(level, blockPos, blockState, t);
        IAutoOutputElectricityGeneratorBlockEntity autoOutputElectricityGeneratorBlockEntity = (IAutoOutputElectricityGeneratorBlockEntity) level.getBlockEntity(blockPos);
        for (int i = 0; i < 6; i++) {
            BlockEntity entity = null;
            switch (i) {
                case 0 -> entity = level.getBlockEntity(blockPos.above());
                case 1 -> entity = level.getBlockEntity(blockPos.below());
                case 2 -> entity = level.getBlockEntity(blockPos.east());
                case 3 -> entity = level.getBlockEntity(blockPos.west());
                case 4 -> entity = level.getBlockEntity(blockPos.north());
                case 5 -> entity = level.getBlockEntity(blockPos.south());
            }
            if (entity instanceof Conductionable conductionable) {
                if (conductionable.getEnergyExportType()==EnergyExportType.CABLE||conductionable.getEnergyExportType()==EnergyExportType.MACHINE)
                    TypeEnergyStorage.transportEnergy(autoOutputElectricityGeneratorBlockEntity.energyStorage,conductionable.getEnergyStorage());
            }
        }
    }
}
