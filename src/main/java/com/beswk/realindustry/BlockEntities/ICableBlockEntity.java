package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.util.Class.ConductionableChannel;
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
    ConductionableChannel conductionables;
    @Override
    public ConductionableChannel getConductionableChannel() {
        return conductionables;
    }
    public ICableBlockEntity(int capacity, int maxExport, BlockEntityType<?> entity, BlockPos p_155229_, BlockState p_155230_) {
        super(entity, p_155229_, p_155230_);
    }

    public ICableBlockEntity(int maxExtract, BlockEntityType<?> entity, BlockPos p_155229_, BlockState p_155230_) {
        this(maxExtract*6,maxExtract,entity, p_155229_, p_155230_);
    }

    @Override
    public TypeEnergyStorage getEnergyStorage() {
        return new TypeEnergyStorage(EnergyType.FE,0,0,0,0);
    }

    @Override
    public EnergyExportType getEnergyExportType() {
        return EnergyExportType.CABLE;
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        if (level!=null&&blockPos!=null) {
            ICableBlockEntity blockEntity = (ICableBlockEntity) level.getBlockEntity(blockPos);
            blockEntity.conductionables = new ConductionableChannel(level, blockPos);
        }
        /*
        ArrayList<Conductionable> nearConductionable = new ArrayList<>();
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
        boolean isFull = blockEntity.energyStorage.getEnergyStored()>=blockEntity.energyStorage.getMaxExtract()*nearConductionable.size();
        for (Conductionable conductionable:nearConductionable) {
            System.out.println(conductionable.getEnergyStorage().getEnergyStored());
            System.out.println(conductionable.getEnergyExportType()==EnergyExportType.CABLE&&isFull&&conductionable.getEnergyStorage().getEnergyStored()==0);
            if ((conductionable.getEnergyExportType()==EnergyExportType.CABLE&&isFull&&conductionable.getEnergyStorage().getEnergyStored()==0)||conductionable.getEnergyExportType()==EnergyExportType.MACHINE) {
                TypeEnergyStorage.transportEnergy(blockEntity.energyStorage, conductionable.getEnergyStorage());
            }
        }

         */
    }
}
