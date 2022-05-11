package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.BlockEntities;
import com.beswk.realindustry.util.Class.EnergyType;
import com.beswk.realindustry.util.Class.TypeEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CableBlockEntity extends BlockEntity {
    public TypeEnergyStorage energyStorage = new TypeEnergyStorage(EnergyType.FE,100,200,200,0);
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
                cableBlockEntity.energyStorage.addEnergy(generatorBlockEntity.energyStorage,5);
                System.out.println("Cable at "+blockPos+":"+cableBlockEntity.energyStorage);
            }
            if (nearGenerator instanceof CableBlockEntity cableBlockEntity&&cableBlockEntity.energyStorage.getEnergyStored() >= 5&&entity instanceof CableBlockEntity cableBlockEntityThis&&cableBlockEntityThis.energyStorage.getEnergyStored()<=cableBlockEntity.energyStorage.getEnergyStored()) {
                cableBlockEntityThis.energyStorage.addEnergy(cableBlockEntity.energyStorage,5);
                System.out.println("Cable at "+blockPos+":"+cableBlockEntityThis.energyStorage);
            }
        }
    }
}
