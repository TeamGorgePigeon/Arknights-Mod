package arknights.registry;

import arknights.item.*;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static arknights.Arknights.ARKNIGHTS;
import static arknights.Arknights.MODID;
import static net.minecraft.util.text.TextFormatting.*;

public class ItemHandler {

    static TextFormatting COMMON = WHITE;
    static TextFormatting UNCOMMON = YELLOW;
    static TextFormatting RARE = AQUA;
    static TextFormatting EPIC = LIGHT_PURPLE;
    static TextFormatting LEGEND = GOLD;

    public static List<Item> _3Star = new ArrayList<>();
    public static List<Item> _4Star = new ArrayList<>();
    public static List<Item> _5Star = new ArrayList<>();
    public static List<Item> _6Star = new ArrayList<>();

    public static List<Item> _whiteMaterial = new ArrayList<>();
    public static List<Item> _greenMaterial = new ArrayList<>();
    public static List<Item> _blueMaterial = new ArrayList<>();
    public static List<Item> _purpleMaterial = new ArrayList<>();

    public static Map<Integer, Item> mapOfId = new HashMap<>();

    public static final Item CROWNSLAYER = new SpawnEggItem(EntityHandler.CROWNSLAYER, 0 , 12326679, new Item.Properties().group(ARKNIGHTS)).setRegistryName(EntityHandler.CROWNSLAYER.getRegistryName());
    public static final Item ORIGINIUMSLUG = new SpawnEggItem(EntityHandler.ORIGINIUMSLUG, 0 , 16776960, new Item.Properties().group(ARKNIGHTS)).setRegistryName(EntityHandler.ORIGINIUMSLUG.getRegistryName());

    public static final Item ANSEL = new Ansel(new Item.Properties().group(ARKNIGHTS)).setRegistryName(MODID + ":ansel");
    public static final Item EXUSIAI = new Exusiai(new Item.Properties().group(ARKNIGHTS)).setRegistryName(MODID + ":exusiai");
    public static final Item AMIYA = new Amiya(new Item.Properties().group(ARKNIGHTS)).setRegistryName(MODID + ":amiya");

    public static final Item ORIGINITEPRIME = new OriginitePrime(new Item.Properties().group(ARKNIGHTS)).setRegistryName(MODID+":originite_prime");
    public static final Item ORIGINIUMSHARD = createItem("originium_shard", 1001, LEGEND);
    public static final Item PUREGOLD = createItem("pure_gold", 1000, EPIC);
    //public static final Item ORUNDUM= createItem("orundum", 44, COMMON);
    public static final BlockItem ORUNDUM = createBlockItem(BlockHandler.ORUNDUM);

    public static final Item TRUESILVER_SWORD = new TruesilverSword(new Item.Properties().group(ARKNIGHTS)).setRegistryName(MODID + ":" + "truesilver_sword");
    public static final Item PROJEKTRED_KNIFE= new ProjektRedKnife(new Item.Properties().group(ARKNIGHTS)).setRegistryName(MODID + ":" + "projektred_knife");
    public static final Item CROWNSLAYER_KNIFE = new CrownslayerKnife(new Item.Properties().group(ARKNIGHTS)).setRegistryName(MODID + ":" + "crownslayer_knife");
    public static final Item APULUPAI = new Apulupai(new Item.Properties().group(ARKNIGHTS).maxDamage(512)).setRegistryName(MODID + ":" + "jb");
    public static final Item SHAW_PUMP = new ShawPump(new Item.Properties().group(ARKNIGHTS).maxDamage(600)).setRegistryName(MODID + ":" + "shaw_pump");
    public static final Item SHAW_AXE = new ShawAxe(new Item.Properties().group(ARKNIGHTS)).setRegistryName(MODID + ":" + "shaw_axe");
    public static final Item FAUST_CROSSBOW = new FaustCrossBow(new Item.Properties().maxStackSize(1).group(ARKNIGHTS).maxDamage(1024)).setRegistryName(MODID + ":" + "faust_crossbow");

    public static final Item ORIROCK = createItem("orirock", 1, COMMON);
    public static final OrirockCube ORIROCKCUBE = (OrirockCube) new OrirockCube(BlockHandler.ORIROCKCUBE, new Item.Properties().group(ARKNIGHTS)).setRegistryName(BlockHandler.ORIROCKCUBE.getRegistryName());
    public static final Item ORIROCKCLUSTER = createItem("orirock_cluster", 3, RARE, 2, 5);
    public static final Item ORIROCKCONCENTRATION = createItem("orirock_concentration", 4, EPIC, 3, 4);

    public static final Item SUGARSUBSTITUTE = createItem("sugar_substitute", 5, COMMON);
    public static final Item SUGAR = createItem("sugar", 6, UNCOMMON, 5, 3);
    public static final Item SUGARPACK = createItem("sugar_pack", 7, RARE, 6, 4);
    public static final Item SUGARLUMP = createItem("sugar_lump", 48, EPIC, 7, 2, 15, 1, 26, 1);

    public static final Item ESTER = createItem("ester", 9, COMMON);
    public static final Item POLYESTER = createItem("polyester", 10, UNCOMMON, 9, 3);
    public static final Item POLYESTERPACK = createItem("polyester_pack", 11, RARE, 10, 4);
    public static final Item POLYESTERLUMP = createItem("polyester_lump", 55, EPIC, 11, 2, 19, 1, 25, 1);

    public static final Item ORIRONSHARD= createItem("oriron_shard", 13, COMMON);
    public static final Item ORIRON = createItem("oriron", 14, UNCOMMON, 13, 3);
    public static final Item ORIRONCLUSTER = createItem("oriron_cluster", 15, RARE, 14, 4);
    public static final Item ORIRONBLOCK = createItem("oriron_block", 49, EPIC, 15, 2, 23, 1, 11, 1);

    public static final Item DIKETON = createItem("diketon", 17, COMMON);
    public static final Item POLYKETON = createItem("polyketon", 18, UNCOMMON, 17, 3);
    public static final Item AKETON = createItem("aketon", 19, RARE, 18, 4);
    public static final Item KETONCOLLOID = createItem("keton_colloid", 52, EPIC, 19, 2, 7, 1, 26, 1);

    public static final Item DAMAGEDDEVICE = createItem("damaged_device", 21, COMMON);
    public static final Item DEVICE = createItem("device", 22, UNCOMMON, 21, 3);
    public static final Item INTEGRATEDDEVICE = createItem("integrated_device", 23, RARE, 22, 4);
    public static final Item OPTMIZEDDEVICE = createItem("optimized_device", 56, EPIC, 23, 1, 3, 2, 30, 1);

    public static final Item LOXICKOHL = createItem("loxic_kohl", 25, RARE);
    public static final Item WHITEHORSEKOHL = createItem("white_horse_kohl", 59, EPIC, 25, 1, 7, 1, 27, 1);

    public static final Item MANGANESEORE = createItem("manganese_ore", 26, RARE);
    public static final Item MANGANESETRIHYDRATE = createItem("manganese_trihydrate", 62, EPIC, 26, 2, 11, 1, 25, 1);

    public static final Item RMA7012 = createItem("rma70_12", 27, RARE);
    public static final Item RMA7024 = createItem("rma70_24", 50, EPIC, 27, 1, 3, 2, 19, 1);

    //凝胶、炽合金

    public static final Item GRINDSTONE = createItem("grindstone", 30, RARE);
    public static final Item GRINDSTONEPENTAHYDRATE = createItem("grindstone_pentahydrate", 68, EPIC, 30, 1, 15, 1, 23, 1);

    public static final Item D32STEEL = createItem("d32_steel", 179, LEGEND, 62, 1, 68, 1, 49, 1);
    public static final Item BIPOLARNANOFLAKE = createItem("bipolar_nanoflake", 105, LEGEND, 4, 1, 49, 1, 52, 1);
    public static final Item POLYMERIZATIONPREPERATION = createItem("polymerization_preparation", 115, LEGEND, 56, 1, 59, 2, 0, 0);

    public static final BlockItem TRADINGHOME_ITEM = createBlockItem(BlockHandler.TRADINGHOME);
    public static final BlockItem ORIGINIUMS = createBlockItem(BlockHandler.ORIGINITEPRIME_BLOCK);
    public static final BlockItem WORKSHOP = createBlockItem(BlockHandler.WORKSHOP);

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> evt){
        IForgeRegistry<Item> r = evt.getRegistry();
        r.register(ORIGINITEPRIME);
        r.register(ORIGINIUMSHARD);
        r.register(PUREGOLD);
        r.register(ORUNDUM);

        registerWhiteMaterial(r,ORIROCK);
        registerGreenMaterial(r,ORIROCKCUBE);
        registerBlueMaterial(r,ORIROCKCLUSTER);
        registerPurpleMaterial(r,ORIROCKCONCENTRATION);
        registerWhiteMaterial(r,SUGARSUBSTITUTE);
        registerGreenMaterial(r,SUGAR);
        registerBlueMaterial(r,SUGARPACK);
        registerPurpleMaterial(r,SUGARLUMP);
        registerWhiteMaterial(r,ESTER);
        registerGreenMaterial(r,POLYESTER);
        registerBlueMaterial(r,POLYESTERPACK);
        registerPurpleMaterial(r,POLYESTERLUMP);
        registerWhiteMaterial(r,ORIRONSHARD);
        registerGreenMaterial(r,ORIRON);
        registerBlueMaterial(r,ORIRONCLUSTER);
        registerPurpleMaterial(r,ORIRONBLOCK);
        registerWhiteMaterial(r,DIKETON);
        registerGreenMaterial(r,POLYKETON);
        registerBlueMaterial(r,AKETON);
        registerPurpleMaterial(r,KETONCOLLOID);
        registerWhiteMaterial(r,DAMAGEDDEVICE);
        registerGreenMaterial(r,DEVICE);
        registerBlueMaterial(r,INTEGRATEDDEVICE);
        registerPurpleMaterial(r,OPTMIZEDDEVICE);
        registerBlueMaterial(r,LOXICKOHL);
        registerPurpleMaterial(r,WHITEHORSEKOHL);
        registerBlueMaterial(r,MANGANESEORE);
        registerPurpleMaterial(r,MANGANESETRIHYDRATE);
        registerBlueMaterial(r,RMA7012);
        registerPurpleMaterial(r,RMA7024);
        registerBlueMaterial(r,GRINDSTONE);
        registerPurpleMaterial(r,GRINDSTONEPENTAHYDRATE);
        r.register(D32STEEL);
        r.register(BIPOLARNANOFLAKE);
        r.register(POLYMERIZATIONPREPERATION);

        r.register(APULUPAI);
        r.register(TRUESILVER_SWORD);
        r.register(CROWNSLAYER_KNIFE);
        r.register(PROJEKTRED_KNIFE);
        r.register(SHAW_PUMP);
        r.register(SHAW_AXE);
        r.register(FAUST_CROSSBOW);

        r.register(TRADINGHOME_ITEM);
        r.register(ORIGINIUMS);
        r.register(WORKSHOP);

        //r.register(EXUSIAI);
        //r.register(AMIYA);
        register3Operator(r, ANSEL);
        register5Operator(r, AMIYA);
        register6Operator(r, EXUSIAI);

        r.register(CROWNSLAYER);
        r.register(ORIGINIUMSLUG);

    }
    private static void registerWhiteMaterial(IForgeRegistry<Item> r, Item item){
        r.register(item);
        _whiteMaterial.add(item);
    }
    private static void registerGreenMaterial(IForgeRegistry<Item> r, Item item){
        r.register(item);
        _greenMaterial.add(item);
    }
    private static void registerBlueMaterial(IForgeRegistry<Item> r, Item item){
        r.register(item);
        _blueMaterial.add(item);
    }
    private static void registerPurpleMaterial(IForgeRegistry<Item> r, Item item){
        r.register(item);
        _purpleMaterial.add(item);
    }

    private static void register3Operator(IForgeRegistry<Item> r, Item item){
        r.register(item);
        _3Star.add(item);
    }
    private static void register4Operator(IForgeRegistry<Item> r, Item item){
        r.register(item);
        _4Star.add(item);
    }
    private static void register5Operator(IForgeRegistry<Item> r, Item item){
        r.register(item);
        _5Star.add(item);
    }
    private static void register6Operator(IForgeRegistry<Item> r, Item item){
        r.register(item);
        _6Star.add(item);
    }
    private static Item createItem(String name, int id, TextFormatting color, int item1, int num1, int item2, int num2, int item3, int num3){
        MaterialItem item = (MaterialItem) new MaterialItem(new Item.Properties().group(ARKNIGHTS), id, color, item1, num1, item2, num2, item3, num3).setRegistryName(MODID + ":" + name);
        mapOfId.put(id, item);
        return item;
    }

    private static Item createItem(String name, int id, TextFormatting color){
        MaterialItem item = (MaterialItem) new MaterialItem(new Item.Properties().group(ARKNIGHTS), id, color, 0, 0, 0, 0, 0, 0).setRegistryName(MODID + ":" + name);
        mapOfId.put(id, item);
        return item;
    }

    private static Item createItem(String name, int id, TextFormatting color, int id1, int num1){
        MaterialItem item = (MaterialItem) new MaterialItem(new Item.Properties().group(ARKNIGHTS), id, color, id1, num1, 0, 0, 0, 0).setRegistryName(MODID + ":" + name);
        mapOfId.put(id, item);
        return item;
    }

    private static BlockItem createBlockItem(Block block){
        return (BlockItem) new BlockItem(block, new Item.Properties().group(ARKNIGHTS)).setRegistryName(block.getRegistryName());
    }
}
