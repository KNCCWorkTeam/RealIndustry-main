package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.Class.ConductionableChannel;
import com.beswk.realindustry.util.Class.TypeEnergyStorage;

public interface Conductionable {
    TypeEnergyStorage getEnergyStorage();
    EnergyExportType getEnergyExportType();
    ConductionableChannel getConductionableChannel();
    enum EnergyExportType{
        GENERATOR_ELECTRICITY,GENERATOR_NOT_ELECTRICITY,CABLE,MACHINE,ENERGY_TYPE_TRANSFER,ENERGY_STORAGE
    }
}
