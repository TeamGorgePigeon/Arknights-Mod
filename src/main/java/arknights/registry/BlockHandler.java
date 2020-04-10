package arknights.registry;

import arknights.block.OriginiumPrimeBlock;
import arknights.block.TradingHome;
import arknights.block.UpgradeBlock;
import arknights.block.Workshop;
import net.minecraft.block.Block;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static arknights.Arknights.MODID;

public class BlockHandler {
    public static final Block ORIGINIUMS_ORE = createBlocks("originiums_ore", Material.GLASS, 3.0F, 3.0F);//new Block(Block.Properties.create(Material.GLASS).hardnessAndResistance(3.0F, 3.0F)).setRegistryName(MODID+":"+"originiums_ore");
    public static final Block ORIROCKCUBE = createBlocks("orirock_cube", Material.ROCK, 1.5F, 6.0F);//new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)).setRegistryName(MODID+":"+"solid_originrock");
    public static final Block TRADINGHOME = new TradingHome(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)).setRegistryName(MODID+":"+"trading_home");
    public static final Block ORIGINITEPRIME_BLOCK = new OriginiumPrimeBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).func_226896_b_()).setRegistryName(MODID+":"+"originite_prime_block");
    public static final Block WORKSHOP = new Workshop(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F,6.0F)).setRegistryName(MODID + ":workshop");
    public static final Block ORUNDUM = new GlassBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).func_226896_b_()).setRegistryName(MODID + ":orundum");
    public static final Block UPGRADE = new UpgradeBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F,6.0F)).setRegistryName(MODID + ":upgrade");

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Block> evt){
        IForgeRegistry<Block> r = evt.getRegistry();
        r.register(ORIGINIUMS_ORE);
        r.register(ORIROCKCUBE);
        r.register(TRADINGHOME);
        r.register(ORIGINITEPRIME_BLOCK);
        r.register(WORKSHOP);
        r.register(ORUNDUM);
        r.register(UPGRADE);
    }

    private static Block createBlocks(String name, Material material, float hardness, float resistance){
        return new Block(Block.Properties.create(material).hardnessAndResistance(hardness,resistance)).setRegistryName(MODID+":"+name);
    }
}
