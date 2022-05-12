package com.beswk.realindustry.util.Class;

public enum EnergyType {
    //electricity energy
    FE(1),
    //kinetic energy
    KE(2),
    //heat energy
    HE(3);

    final int energyTime;
    EnergyType(int energyTime) {
        this.energyTime = energyTime;
    }

    public int getEnergyTime() {
        return energyTime;
    }
}
