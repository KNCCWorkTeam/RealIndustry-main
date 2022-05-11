package com.beswk.realindustry.BlockEntities;

import com.beswk.realindustry.Menu.InternalCombustionEngineMenu;
import com.beswk.realindustry.util.BlockEntities;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;

public class InternalCombustionEngineBlockEntity extends GeneratorBlockEntity{
    public InternalCombustionEngineBlockEntity(BlockPos position, BlockState state) {
        super("internal_combustion_engine",40000,200,200,0,10,BlockEntities.INTERNAL_COMBUSTION_ENGINE_ENTITY, position, state);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new InternalCombustionEngineMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
    }

    public int fuelEfficient(Item item) {
        HashMap<Item,Integer> itemIntegerHashMap = new HashMap<>();
        itemIntegerHashMap.put(Items.COAL,100);
        itemIntegerHashMap.put(Items.COAL_BLOCK,1000);
        return itemIntegerHashMap.get(item)==null?0:itemIntegerHashMap.get(item);
    }
}
