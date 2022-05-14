package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.BlockEntities;
import com.beswk.realindustry.util.Class.EnergyType;
import com.beswk.realindustry.util.Class.TypeEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public class SteamTurbineBlockEntity extends IEnergyTypeTransferBlockEntity{
    public SteamTurbineBlockEntity(BlockPos position, BlockState state) {
        super(EnergyType.HE,EnergyType.FE,BlockEntities.STEAM_TURBINE_ENTITY, position, state);
    }
}
