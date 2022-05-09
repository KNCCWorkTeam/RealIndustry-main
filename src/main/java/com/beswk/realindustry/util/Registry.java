package com.beswk.realindustry.util;

import com.beswk.realindustry.RealIndustry;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.HashMap;

public class Registry {
    public static HashMap<String, Item> itemNameHashMap = new HashMap<>();
    public static HashMap<String, Block> blockNameHashMap = new HashMap<>();

    public static BlockBehaviour.Properties getBlock(Material material, MaterialColor materialColor, float strength, float resistance, SoundType sound) {
        return BlockBehaviour.Properties.of(material,materialColor).strength(strength,resistance).sound(sound).requiresCorrectToolForDrops();
    }

    public static Block registerBlock(Block block,String name, CreativeModeTab tab, IEventBus bus) {
        DeferredRegister<Block> deferredRegisterBlock = DeferredRegister.create(ForgeRegistries.BLOCKS, RealIndustry.MOD_ID);
        deferredRegisterBlock.register(name, () -> block);
        deferredRegisterBlock.register(bus);
        System.out.println(blockNameHashMap);
        BlockItem blockItem = new BlockItem(block, new Item.Properties().tab(tab));
        DeferredRegister<Item> item = DeferredRegister.create(ForgeRegistries.ITEMS, RealIndustry.MOD_ID);
        item.register(name,() -> blockItem);
        item.register(bus);
        return block;
    }

    public static Block registerBlock(Material material, MaterialColor materialColor, float strength, float resistance, SoundType sound,String name, CreativeModeTab tab, IEventBus bus) {
        return registerBlock(new Block(BlockBehaviour.Properties.of(material,materialColor).strength(strength,resistance).sound(sound).requiresCorrectToolForDrops()),name,tab,bus);
    }

    public static DeferredRegister<Item> getItem() {
        return DeferredRegister.create(ForgeRegistries.ITEMS, RealIndustry.MOD_ID);
    }

    public static Item.Properties getItem(CreativeModeTab tab) {
        return new Item.Properties().tab(tab);
    }

    public static Item registerItem(Item item,String name, IEventBus bus) {
        DeferredRegister<Item> deferredRegisterItem = DeferredRegister.create(ForgeRegistries.ITEMS, RealIndustry.MOD_ID);
        deferredRegisterItem.register(name, () -> item);
        deferredRegisterItem.register(bus);
        return item;
    }

    public static Item registerItem(CreativeModeTab tab,String name,IEventBus bus) {
        return registerItem(new Item(new Item.Properties().tab(tab)),name,bus);
    }

    public static DeferredRegister<BlockEntityType<?>> getBlockEntity() {
        return DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, RealIndustry.MOD_ID);
    }

    public static <T extends BlockEntity> BlockEntityType<?> registerBlockEntity(String name, BlockEntityType.BlockEntitySupplier<? extends T> p_155274_, net.minecraft.world.level.block.Block... p_155275_) {
        DeferredRegister<BlockEntityType<?>> deferredRegisterBlockEntity = getBlockEntity();
        BlockEntityType<?> entity = BlockEntityType.Builder.of(p_155274_, p_155275_).build(null);
        deferredRegisterBlockEntity.register(name, () -> entity);
        deferredRegisterBlockEntity.register(RealIndustry.bus);
        return entity;
    }

    public static DeferredRegister<MenuType<?>> getMenu() {
        return DeferredRegister.create(ForgeRegistries.CONTAINERS, RealIndustry.MOD_ID);
    }

    public static MenuType<?> registerMenu(String name, MenuType.MenuSupplier<?> supplier) {
        DeferredRegister<MenuType<?>> deferredRegister = getMenu();
        MenuType<?> menuType = new MenuType<>(supplier);
        deferredRegister.register(name,()->menuType);
        return menuType;
    }

    public static <T extends AbstractContainerMenu> MenuType<T> registerMenu(String registryname, IContainerFactory<T> containerFactory) {
        MenuType<T> menuType = new MenuType<>(containerFactory);
        DeferredRegister<MenuType<?>> menuTypeDeferredRegister = getMenu();
        menuTypeDeferredRegister.register(registryname,()->menuType);
        menuTypeDeferredRegister.register(RealIndustry.bus);
        return menuType;
    }
}
