package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.BlockEntities;
import com.beswk.realindustry.util.Class.EnergyType;
import com.beswk.realindustry.util.Class.TypeEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public class KEGeneratorBlockEntity extends IEnergyTypeTransferBlockEntity {
    public KEGeneratorBlockEntity(BlockPos position, BlockState state) {
        super(BlockEntities.KE_GENERATOR_ENTITY, position, state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        ArrayList<Conductionable> nearConductionableKE = new ArrayList<>();
        ArrayList<Conductionable> nearConductionableFE = new ArrayList<>();
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
                if (conductionable.getEnergyStorage().ifSameEnergyType(EnergyType.KE))
                    nearConductionableKE.add(conductionable);
                else if (conductionable.getEnergyStorage().ifSameEnergyType(EnergyType.FE))
                    nearConductionableFE.add(conductionable);
            }
        }
        for (Conductionable ke:nearConductionableKE) {
            for (Conductionable fe:nearConductionableFE) {
                TypeEnergyStorage.transferEnergy(ke.getEnergyStorage(),fe.getEnergyStorage());
            }
        }
    }
}