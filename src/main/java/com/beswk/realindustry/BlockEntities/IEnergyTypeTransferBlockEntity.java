package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.Class.EnergyTypeTransferEnergyStorage;
import com.beswk.realindustry.util.Class.TypeEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class IEnergyTypeTransferBlockEntity extends BlockEntity implements Conductionable {
    public IEnergyTypeTransferBlockEntity(BlockEntityType<?> type, BlockPos position, BlockState state) {
        super(type, position, state);
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
