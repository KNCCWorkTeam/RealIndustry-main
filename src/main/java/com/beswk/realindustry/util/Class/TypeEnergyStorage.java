package com.beswk.realindustry.util.Class;

import com.beswk.realindustry.BlockEntities.GeneratorBlockEntity;
import com.beswk.realindustry.BlockEntities.MachineBlockEntity;
import net.minecraftforge.energy.EnergyStorage;

public class TypeEnergyStorage extends EnergyStorage {
    EnergyType type;
    public TypeEnergyStorage(EnergyType type,int capacity) {
        super(capacity);
        this.type = type;
    }

    public TypeEnergyStorage(EnergyType type,int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
        this.type = type;
    }

    public TypeEnergyStorage(EnergyType type,int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
        this.type = type;
    }

    public TypeEnergyStorage(EnergyType type,int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
        this.type = type;
    }

    public void addEnergy(TypeEnergyStorage typeEnergyStorage,int energy) {
        if (ifSameEnergyType(typeEnergyStorage)) {
            addEnergy(energy);
            typeEnergyStorage.addEnergy(-energy);
        }
    }

    public void addEnergy(GeneratorBlockEntity generatorBlockEntity, int energy) {
        if (ifSameEnergyType(generatorBlockEntity.energyStorage)) {
            addEnergy(energy);
        }
    }

    public void addEnergy(MachineBlockEntity machineBlockEntity, int energy,int process) {
        if (ifSameEnergyType(machineBlockEntity.energyStorage)) {
            addEnergy(energy);
            machineBlockEntity.addProcess(process);
        }
    }

    private void addEnergy(int energy) {
        if (getEnergyStored()+energy>getMaxEnergyStored()) {
            this.energy = getMaxEnergyStored();
        } else {
            this.energy += energy;
        }
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public boolean ifSameEnergyType(EnergyType type) {
        return this.type==type;
    }

    public boolean ifSameEnergyType(TypeEnergyStorage typeEnergyStorage) {
        return ifSameEnergyType(typeEnergyStorage.type);
    }

    public static boolean ifSameEnergyType(TypeEnergyStorage... typeEnergyStorages) {
        EnergyType type;
        type = typeEnergyStorages[0].type;
        for (TypeEnergyStorage typeEnergyStorage:typeEnergyStorages) {
            if (typeEnergyStorage.type!=type) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Storage:"+getEnergyStored()+";Type:"+type;
    }
}
