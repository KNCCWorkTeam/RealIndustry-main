package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.Menu.GrinderMenu;
import com.beswk.realindustry.util.BlockEntities;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.EnergyStorage;

import java.util.HashMap;

public class GrinderBlockEntities extends MachineBlockEntity{
    public GrinderBlockEntities(BlockPos position, BlockState state) {
        super("grinder", 1000, 200, 200, 0, BlockEntities.GRINDER, position, state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        BlockEntity nearGenerator = null;
        for (int i = 0;i<6;i++) {
            if (nearGenerator instanceof GeneratorBlockEntity) {
                break;
            }
            switch (i) {
                case 0:
                    nearGenerator = level.getBlockEntity(blockPos.above());
                    break;
                case 1:
                    nearGenerator = level.getBlockEntity(blockPos.below());
                    break;
                case 2:
                    nearGenerator = level.getBlockEntity(blockPos.east());
                    break;
                case 3:
                    nearGenerator = level.getBlockEntity(blockPos.west());
                    break;
                case 4:
                    nearGenerator = level.getBlockEntity(blockPos.south());
                    break;
                case 5:
                    nearGenerator = level.getBlockEntity(blockPos.north());
                    break;
            }
        }
        if (nearGenerator instanceof GeneratorBlockEntity generatorBlockEntity) {
            if (generatorBlockEntity.energyStorage.getEnergyStored()>=5) {
                BlockEntity entity = level.getBlockEntity(blockPos);
                if (entity instanceof GrinderBlockEntities grinderBlockEntities) {
                    if (grinderBlockEntities.completeMap(grinderBlockEntities.getInputItem()) != null) {
                        EnergyStorage before = ((GeneratorBlockEntity) nearGenerator).energyStorage;
                        before = new EnergyStorage(before.getMaxEnergyStored(), 200, 200, before.getEnergyStored() - 5);
                        grinderBlockEntities.addProcess(10);
                    }
                }
            }
        }
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new GrinderMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
    }


    @Override
    public ItemStack completeMap(Item item) {
        HashMap<Item,ItemStack> itemItemStackHashMap = new HashMap<>();
        itemItemStackHashMap.put(Items.IRON_ORE,new ItemStack(Items.IRON_INGOT,2));
        return itemItemStackHashMap.get(item);
    }
}
