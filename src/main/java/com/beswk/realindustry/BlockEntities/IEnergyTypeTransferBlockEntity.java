package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.Class.ConductionableChannel;
import com.beswk.realindustry.util.Class.EnergyType;
import com.beswk.realindustry.util.Class.EnergyTypeTransferEnergyStorage;
import com.beswk.realindustry.util.Class.TypeEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public abstract class IEnergyTypeTransferBlockEntity extends BlockEntity implements Conductionable {
    EnergyType inputType;
    EnergyType outputType;
    public IEnergyTypeTransferBlockEntity(EnergyType inputEnergyType,EnergyType outputEnergyType,BlockEntityType<?> type, BlockPos position, BlockState state) {
        super(type, position, state);
        this.inputType = inputEnergyType;
        this.outputType = outputEnergyType;
    }

    ConductionableChannel conductionables;
    @Override
    public ConductionableChannel getConductionableChannel() {
        return conductionables;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        if (level!=null&&blockPos!=null) {
            IEnergyTypeTransferBlockEntity blockEntity = (IEnergyTypeTransferBlockEntity) level.getBlockEntity(blockPos);
            blockEntity.conductionables = new ConductionableChannel(level, blockPos);
        }
        IEnergyTypeTransferBlockEntity iEnergyTypeTransferBlockEntity = (IEnergyTypeTransferBlockEntity) level.getBlockEntity(blockPos);
        ArrayList<Conductionable> generators = new ArrayList<>();
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
            if (entity instanceof IGeneratorBlockEntity iGeneratorBlockEntity) {
                if (iGeneratorBlockEntity.getEnergyStorage().ifSameEnergyType(iEnergyTypeTransferBlockEntity.inputType))
                    generators.add(iGeneratorBlockEntity);
            }
        }
        ArrayList<Conductionable> machines = iEnergyTypeTransferBlockEntity.conductionables.getConductionable(EnergyExportType.MACHINE);
        ArrayList<Conductionable> accumulators = iEnergyTypeTransferBlockEntity.conductionables.getConductionable(EnergyExportType.ENERGY_STORAGE);
        for (Conductionable generator:generators) {
            for (Conductionable machine:machines) {
                if (generator instanceof IGeneratorBlockEntity iGeneratorBlockEntity&&iGeneratorBlockEntity.energyStorage.ifSameEnergyType(iEnergyTypeTransferBlockEntity.inputType)&&machine instanceof IMachineBlockEntity iMachineBlockEntity&&iMachineBlockEntity.energyStorage.ifSameEnergyType(iEnergyTypeTransferBlockEntity.outputType)) {
                    TypeEnergyStorage.transferEnergy(iGeneratorBlockEntity.energyStorage,iMachineBlockEntity.energyStorage);
                }
            }
            for (Conductionable accumulator:accumulators) {
                if (generator instanceof IGeneratorBlockEntity iGeneratorBlockEntity&&iGeneratorBlockEntity.energyStorage.ifSameEnergyType(iEnergyTypeTransferBlockEntity.inputType)&&accumulator instanceof IAccumulatorBlockEntity iAccumulatorBlock&&iAccumulatorBlock.energyStorage.ifSameEnergyType(EnergyType.FE)) {
                    TypeEnergyStorage.transferEnergy(iGeneratorBlockEntity.energyStorage,iAccumulatorBlock.energyStorage);
                }
            }
        }


        /*
        ArrayList<Conductionable> nearConductionableIn = new ArrayList<>();
        ArrayList<Conductionable> nearConductionableOut = new ArrayList<>();
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
                if (conductionable.getEnergyStorage().ifSameEnergyType(iEnergyTypeTransferBlockEntity.inputType))
                    nearConductionableIn.add(conductionable);
                else if (conductionable.getEnergyStorage().ifSameEnergyType(iEnergyTypeTransferBlockEntity.outputType))
                    nearConductionableOut.add(conductionable);
            }
        }
        for (Conductionable in:nearConductionableIn) {
            for (Conductionable out:nearConductionableOut) {
                TypeEnergyStorage.transferEnergy(in.getEnergyStorage(),out.getEnergyStorage());
            }
        }

         */
    }



    @Override
    public TypeEnergyStorage getEnergyStorage() {
        return new EnergyTypeTransferEnergyStorage();
    }

    @Override
    public EnergyExportType getEnergyExportType() {
        return EnergyExportType.ENERGY_TYPE_TRANSFER;
    }
}
