package arknights;

import arknights.event.EventHandler;
import arknights.network.PacketHandler;
import arknights.registry.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Arknights.MODID)

public class Arknights
{
    public static final String MODID = "arknights";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static final ItemGroup ARKNIGHTS = new ItemGroup("arknights") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemHandler.ORIGINITEPRIME);
        }
    };

    //public static final Block ORIGINIUMS_ORE = new Block(Block.Properties.create(Material.GLASS).hardnessAndResistance(3.0F, 3.0F)).setRegistryName(MODID+":originiums_ore");
    //public static final BlockItem ORIGINIUMS_ORE_ITEM = (BlockItem) new BlockItem(ORIGINIUMS_ORE, new Item.Properties().group(ARKNIGHTS)).setRegistryName(MODID+":originiums_ore");

    public Arknights() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(PacketHandler::register);
        MinecraftForge.EVENT_BUS.addListener(EventHandler::emptyLeftClick);
        MinecraftForge.EVENT_BUS.addListener(EventHandler::leftClickRelease);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        //PacketHandler.HANDLER.registerMessage(PacketHandler.index++, LeftClickPacket.class, LeftClickPacket::encode, LeftClickPacket::decode, LeftClickPacket.Handler::handle);
        // some preinit code
        PacketHandler.register();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        RendererHandler.register();
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(modid = Arknights.MODID, bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> evt) {
            BlockHandler.register(evt);
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> evt) {
            ItemHandler.register(evt);
        }

        @SubscribeEvent
        public static void onEntitiesRegistry(final RegistryEvent.Register<EntityType<?>> evt){
            EntityHandler.register(evt);
        }

        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> evt) {
            TileEntityHandler.register(evt);
        }

        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> evt) {
            ContainerHandler.register(evt);
        }

        @SubscribeEvent
        public static void onSoundRegistry(final RegistryEvent.Register<SoundEvent> evt){
            SoundHandler.register(evt);
        }

        @SubscribeEvent
        public static void onRecipeRegistry(final RegistryEvent.Register<IRecipeSerializer<?>> evt) {RecipeHandler.register(evt);}
    }


}