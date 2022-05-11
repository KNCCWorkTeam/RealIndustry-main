package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.EnergyStorage;

public class CableBlockEntity extends BlockEntity {
    public EnergyStorage energyStorage = new EnergyStorage(100,200,200,0);
    public CableBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(BlockEntities.CABLE_ENTITY, p_155229_, p_155230_);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        BlockEntity entity = level.getBlockEntity(blockPos);
        for (int i = 0; i < 6; i++) {
            BlockEntity nearGenerator = null;
            switch (i) {
                case 0 -> nearGenerator = level.getBlockEntity(blockPos.above());
                case 1 -> nearGenerator = level.getBlockEntity(blockPos.below());
                case 2 -> nearGenerator = level.getBlockEntity(blockPos.east());
                case 3 -> nearGenerator = level.getBlockEntity(blockPos.west());
                case 4 -> nearGenerator = level.getBlockEntity(blockPos.south());
                case 5 -> nearGenerator = level.getBlockEntity(blockPos.north());
            }
            if (nearGenerator instanceof GeneratorBlockEntity generatorBlockEntity&&generatorBlockEntity.energyStorage.getEnergyStored() >= 5&&entity instanceof CableBlockEntity cableBlockEntity) {
                cableBlockEntity.energyStorage = new EnergyStorage(cableBlockEntity.energyStorage.getMaxEnergyStored(), 200, 200, cableBlockEntity.energyStorage.getEnergyStored() + 5);
                generatorBlockEntity.energyStorage = new EnergyStorage(generatorBlockEntity.energyStorage.getMaxEnergyStored(), 200, 200, generatorBlockEntity.energyStorage.getEnergyStored() - 5);
                System.out.println("{"+blockPos+":"+((CableBlockEntity) entity).energyStorage.getEnergyStored()+"}");
            }
            if (nearGenerator instanceof CableBlockEntity cableBlockEntity&&cableBlockEntity.energyStorage.getEnergyStored() >= 5&&entity instanceof CableBlockEntity cableBlockEntityThis&&cableBlockEntityThis.energyStorage.getEnergyStored()<=cableBlockEntity.energyStorage.getEnergyStored()) {
                cableBlockEntityThis.energyStorage = new EnergyStorage(cableBlockEntityThis.energyStorage.getMaxEnergyStored(), 200, 200, cableBlockEntityThis.energyStorage.getEnergyStored() + 5);
                cableBlockEntity.energyStorage = new EnergyStorage(cableBlockEntity.energyStorage.getMaxEnergyStored(), 200, 200, cableBlockEntity.energyStorage.getEnergyStored() - 5);
                System.out.println("{"+blockPos+":"+((CableBlockEntity) entity).energyStorage.getEnergyStored()+"}");
            }
        }

    }
}
