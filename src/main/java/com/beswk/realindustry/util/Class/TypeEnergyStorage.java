package com.beswk.realindustry.util.Class;

import com.beswk.realindustry.BlockEntities.IGeneratorBlockEntity;
import com.beswk.realindustry.BlockEntities.IMachineBlockEntity;
import net.minecraftforge.energy.EnergyStorage;

public class TypeEnergyStorage extends EnergyStorage {
    EnergyType type;
    int maxReceive;
    int maxExtract;

    public TypeEnergyStorage(EnergyType type,int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
        this.type = type;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    public TypeEnergyStorage(EnergyType type,int capacity, int maxExport, int energy) {
        this(type, capacity, maxExport, maxExport, energy);
    }

    public void addEnergy(TypeEnergyStorage typeEnergyStorage,int energy) {
        if (ifSameEnergyType(typeEnergyStorage)) {
            if (addEnergy(energy)) {
                typeEnergyStorage.addEnergy(-energy);
            }
        }
    }

    public void generatorAddEnergy(IGeneratorBlockEntity IGeneratorBlockEntity, int energy) {
        addEnergy(energy);
    }

    public void processReduceEnergy(IMachineBlockEntity machineBlockEntity, int energy, int process) {
        if (ifSameEnergyType(machineBlockEntity.energyStorage)) {
            if (addEnergy(-energy)) {
                machineBlockEntity.addProcess(process);
            }
        }
    }

    private boolean addEnergy(int energy) {
        if (getEnergyStored()+energy>getMaxEnergyStored()) {
            this.energy = getMaxEnergyStored();
            return false;
        } else {
            this.energy += energy;
            return true;
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

    public int getMaxReceive() {
        return maxReceive;
    }

    public int getMaxExtract() {
        return maxExtract;
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

    public static int transportEnergy(TypeEnergyStorage output,TypeEnergyStorage input) {
        int energy = Math.min(Math.min(input.getMaxReceive(),input.getMaxEnergyStored()-input.getEnergyStored()),Math.min(output.getMaxExtract(),output.getEnergyStored()));
        if (input.ifSameEnergyType(output)) {
            input.addEnergy(output,energy);
            return energy;
        }
        return -1;
    }

    public static int transferEnergy(TypeEnergyStorage output,TypeEnergyStorage input) {
        if (input.ifSameEnergyType(output)) {
            return transportEnergy(input,output);
        } else {
            int inputMaxReceive = input.getMaxReceive()*input.type.getEnergyTime();
            int outputMaxExtract = output.getMaxExtract()*output.type.getEnergyTime();
            int outputEnergyStored = output.getEnergyStored()*output.type.getEnergyTime();
            int inputEnergySpaceOdd = input.getMaxEnergyStored()-input.getEnergyStored();
            int energy = Math.min(Math.min(inputEnergySpaceOdd,inputMaxReceive),Math.min(outputMaxExtract,outputEnergyStored));
            output.addEnergy(-energy/output.type.getEnergyTime());
            input.addEnergy(energy/input.type.getEnergyTime());
            return energy;
        }
    }

    @Override
    public String toString() {
        return "Storage:"+getEnergyStored()+";Type:"+type;
    }
}
