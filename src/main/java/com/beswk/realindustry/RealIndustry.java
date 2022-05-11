package com.beswk.realindustry;

import com.beswk.realindustry.CreativeTabs.IndustryMaterial;
import com.beswk.realindustry.CreativeTabs.Machine;
import com.beswk.realindustry.CreativeTabs.Material;
import com.beswk.realindustry.CreativeTabs.Tool;
import com.beswk.realindustry.Screens.GeneratorScreen;
import com.beswk.realindustry.Screens.GrinderScreen;
import com.beswk.realindustry.Screens.InternalCombustionEngineScreen;
import com.beswk.realindustry.util.Initialize;
import com.beswk.realindustry.util.Menus;
import com.beswk.realindustry.util.OreFeature;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("realindustry")
public class RealIndustry {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "realindustry";

    public static final CreativeModeTab MACHINE = new Machine();
    public static final CreativeModeTab MATERIAL = new Material();
    public static final CreativeModeTab TOOL = new Tool();
    public static final CreativeModeTab INDUSTRY_MATERIAL = new IndustryMaterial();

    public static final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

    public RealIndustry() {
        new Initialize();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(OreFeature::onBiomeLoadingEvent);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(Menus.INTERNAL_COMBUSTION_MACHINE_MENU, InternalCombustionEngineScreen::new);
            MenuScreens.register(Menus.GRINDER, GrinderScreen::new);
        });
    }


    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(OreFeature::registerOreFeatures);
        //event.enqueueWork(Ores::registerConfiguredFeatures);
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("assets/realindustry", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }

}
