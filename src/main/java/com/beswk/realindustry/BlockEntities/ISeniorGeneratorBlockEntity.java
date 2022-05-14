package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.Menu.SeniorGeneratorMenu;
import com.beswk.realindustry.util.Class.ConductionableChannel;
import com.beswk.realindustry.util.Class.EnergyType;
import com.beswk.realindustry.util.Items;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class ISeniorGeneratorBlockEntity extends IGeneratorBlockEntity{
    public ISeniorGeneratorBlockEntity(EnergyType type, String displayName, int capacity, int maxReceive, int maxExtract, int energy, int generateAmountPerTick, BlockEntityType<?> entity, BlockPos position, BlockState state) {
        super(type, displayName, capacity, maxReceive, maxExtract, energy, generateAmountPerTick, entity, position, state);
        stacks = NonNullList.withSize(4, ItemStack.EMPTY);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == 0)
            return false;
        if (index == 1)
            return false;
        if (index == 3)
            return false;
        return true;
    }


    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index == 2)
            return false;
        return true;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new SeniorGeneratorMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        if (level!=null&&blockPos!=null) {
            IGeneratorBlockEntity blockEntity = (IGeneratorBlockEntity) level.getBlockEntity(blockPos);
            blockEntity.conductionables = new ConductionableChannel(level, blockPos);
        }
        BlockEntity entity = level.getBlockEntity(blockPos);
        if (entity instanceof ISeniorGeneratorBlockEntity iGeneratorBlockEntity) {
            if (iGeneratorBlockEntity.getBurnTimeOdd()==0) {
                if (iGeneratorBlockEntity.fuelEfficient(iGeneratorBlockEntity.stacks.get(0).getItem())!=-1&&iGeneratorBlockEntity.fuelBurnEndProduce(iGeneratorBlockEntity.stacks.get(0).getItem())!=null) {
                    iGeneratorBlockEntity.addBurnTimeOdd(1000);
                    iGeneratorBlockEntity.efficient = iGeneratorBlockEntity.fuelEfficient(iGeneratorBlockEntity.stacks.get(0).getItem());
                    iGeneratorBlockEntity.stacks.set(2,new ItemStack(iGeneratorBlockEntity.fuelBurnEndProduce(iGeneratorBlockEntity.stacks.get(0).getItem()),1));
                    iGeneratorBlockEntity.stacks.get(0).shrink(1);
                }
            } else {
                if (iGeneratorBlockEntity.efficient!=-1) {
                    iGeneratorBlockEntity.reduceBurnTimeOdd(1000 / iGeneratorBlockEntity.efficient);
                    iGeneratorBlockEntity.energyStorage.generatorAddEnergy(iGeneratorBlockEntity, iGeneratorBlockEntity.generateAmountPerTick);
                }
            }
        }
    }

    abstract Item fuelBurnEndProduce(Item item);
}
