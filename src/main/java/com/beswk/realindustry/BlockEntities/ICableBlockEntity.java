package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.Class.EnergyType;
import com.beswk.realindustry.util.Class.TypeEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public abstract class ICableBlockEntity extends BlockEntity implements Conductionable {
    public TypeEnergyStorage energyStorage = null;
    public ICableBlockEntity(int capacity, int maxExport, BlockEntityType<?> entity, BlockPos p_155229_, BlockState p_155230_) {
        super(entity, p_155229_, p_155230_);
        energyStorage = new TypeEnergyStorage(EnergyType.FE,capacity,maxExport,0);
    }

    @Override
    public TypeEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    @Override
    public EnergyExportType getEnergyExportType() {
        return EnergyExportType.CABLE;
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        if (compound.get("energyStorage") instanceof IntTag intTag)
            energyStorage.deserializeNBT(intTag);
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("energyStorage", energyStorage.serializeNBT());
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        ArrayList<Conductionable> nearConductionable = new ArrayList<>();
        ICableBlockEntity blockEntity = (ICableBlockEntity)level.getBlockEntity(blockPos);
        for (int i = 0; i < 6; i++) {
            BlockEntity entity = null;
            switch (i) {
                case 0 -> entity = level.getBlockEntity(blockPos.above());
                case 1 -> entity = level.getBlockEntity(blockPos.below());
                case 2 -> entity = level.getBlockEntity(blockPos.east());
                case 3 -> entity = level.getBlockEntity(blockPos.west());
                case 4 -> entity = level.getBlockEntity(blockPos.south());
                case 5 -> entity = level.getBlockEntity(blockPos.north());
            }
            if (entity instanceof Conductionable conductionable && conductionable.getEnergyStorage().ifSameEnergyType(EnergyType.FE)) {
                nearConductionable.add(conductionable);
            }
        }
        for (Conductionable conductionable:nearConductionable) {
            if ((conductionable.getEnergyExportType()==EnergyExportType.CABLE&&conductionable.getEnergyStorage().getEnergyStored()<blockEntity.energyStorage.getEnergyStored())||conductionable.getEnergyExportType()==EnergyExportType.MACHINE)
                TypeEnergyStorage.transportEnergy(blockEntity.energyStorage,conductionable.getEnergyStorage());
        }
    }
}
