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
