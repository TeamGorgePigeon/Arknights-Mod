package arknights.registry;

import arknights.entity.enemy.FaustEntity;
import arknights.entity.disaster.*;
import arknights.entity.enemy.*;
import arknights.entity.notLiving.*;
import arknights.entity.operator.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;


import static arknights.Arknights.MODID;

public class EntityHandler {
    public static final EntityType<DisasterZero> DISASTERZERO = (EntityType<DisasterZero>) EntityType.Builder.<DisasterZero>create(DisasterZero::new, EntityClassification.MISC).build("disaster_zero").setRegistryName(MODID + ":disaster_zero");
    public static final EntityType<SnowStorm> SNOWSTORM = (EntityType<SnowStorm>) EntityType.Builder.<SnowStorm>create(SnowStorm::new, EntityClassification.MISC).build("snowstorm").setRegistryName(MODID + ":snowstorm");

    public static final EntityType<Meteorite> METEORITE = (EntityType<Meteorite>)EntityType.Builder.<Meteorite>create(Meteorite::new, EntityClassification.MISC).build("meteorite").setRegistryName(MODID + ":" + "meteorite");
    public static final EntityType<BulletEntity> BULLET = (EntityType<BulletEntity>)EntityType.Builder.<BulletEntity>create(BulletEntity::new, EntityClassification.MISC).build("bullet").setRegistryName(MODID+ ":bullet");
    public static final EntityType<AmiyaMagic> AMIYAMAGIC = (EntityType<AmiyaMagic>)EntityType.Builder.<AmiyaMagic>create(AmiyaMagic::new, EntityClassification.MISC).build("amiya_magic").setRegistryName(MODID+ ":amiya_magic");

    //public static final EntityType<OriginiumSlugEntity> ORIGINIUMSLUG = (EntityType<OriginiumSlugEntity>)EntityType.Builder.<OriginiumSlugEntity>create(OriginiumSlugEntity::new, EntityClassification.MONSTER).size(0.8F, 0.5F).build("originium_slug").setRegistryName(MODID + ":" + "originium_slug");
    public static final EntityType<OriginiumSlugEntity> ORIGINIUMSLUG = registerEntity(MODID + ":" + "originium_slug", EntityType.Builder.create(OriginiumSlugEntity::new, EntityClassification.MONSTER).size(0.8F, 0.5F));
    public static final EntityType<CrownslayerEntity> CROWNSLAYER = (EntityType<CrownslayerEntity>)EntityType.Builder.<CrownslayerEntity>create(CrownslayerEntity::new, EntityClassification.CREATURE).build("crownslayer").setRegistryName(MODID + ":" + "crownslayer");
    public static final EntityType<FaustEntity> FAUST = (EntityType<FaustEntity>)EntityType.Builder.<FaustEntity>create(FaustEntity::new, EntityClassification.CREATURE).build("faust").setRegistryName(MODID + ":" + "faust");

    public static final EntityType<ProjektRedEntity> PROJEKTRED=(EntityType<ProjektRedEntity>)EntityType.Builder.<ProjektRedEntity>create(ProjektRedEntity::new, EntityClassification.CREATURE).build("projektred").setRegistryName(MODID + ":" + "projektred");
    public static final EntityType<ExusiaiEntity> EXUSIAI = (EntityType<ExusiaiEntity>)EntityType.Builder.<ExusiaiEntity>create(ExusiaiEntity::new, EntityClassification.CREATURE).build("exusiai").setRegistryName(MODID + ":" + "exusiai");
    public static final EntityType<AmiyaEntity> AMIYA = (EntityType<AmiyaEntity>)EntityType.Builder.<AmiyaEntity>create(AmiyaEntity::new, EntityClassification.CREATURE).build("amiya").setRegistryName(MODID + ":" + "amiya");
    public static final EntityType<ShawEntity> SHAW = (EntityType<ShawEntity>)EntityType.Builder.<ShawEntity>create(ShawEntity::new, EntityClassification.CREATURE).build("shaw").setRegistryName(MODID + ":" + "shaw");
    private static <T extends Entity> EntityType<T> registerEntity(String key, EntityType.Builder<T> builder) {
        return Registry.register(Registry.ENTITY_TYPE, key, builder.build(key));
    }

    @SubscribeEvent
    public static void register(RegistryEvent.Register<EntityType<?>> evt){
        IForgeRegistry<EntityType<?>> r = evt.getRegistry();
        r.register(DISASTERZERO);
        r.register(SNOWSTORM);

        r.register(METEORITE);
        r.register(BULLET);
        //r.register(AMIYAMAGIC);

        //r.register(ORIGINIUMSLUG.setRegistryName(MODID + ":" + "originium_slug"));

		r.register(EXUSIAI);
		r.register(AMIYA);
        r.register(PROJEKTRED);
        r.register(SHAW);

        r.register(CROWNSLAYER);
        r.register(FAUST);


        /*registerEntityWorldSpawn(ORIGINIUMSLUG, 1, Biomes.SNOWY_TAIGA, Biomes.ICE_SPIKES, Biomes.SNOWY_TUNDRA,
                Biomes.SNOWY_BEACH, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS,
                Biomes.MOUNTAINS, Biomes.GRAVELLY_MOUNTAINS, Biomes.MOUNTAIN_EDGE, Biomes.MODIFIED_GRAVELLY_MOUNTAINS, Biomes.TAIGA_MOUNTAINS, Biomes.WOODED_MOUNTAINS,
                Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA_HILLS,
                Biomes.STONE_SHORE, Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.FOREST, Biomes.FLOWER_FOREST, Biomes.BIRCH_FOREST, Biomes.TALL_BIRCH_FOREST, Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS,
                Biomes.SWAMP, Biomes.SWAMP_HILLS, Biomes.JUNGLE);
        registerEntityWorldSpawn(DISASTERZERO, 1, PLAINS, DESERT, MOUNTAINS, FOREST, TAIGA, SWAMP, BEACH, DESERT_HILLS, WOODED_HILLS,
                TAIGA_HILLS, MOUNTAIN_EDGE, JUNGLE, JUNGLE_HILLS, JUNGLE_EDGE, STONE_SHORE, BIRCH_FOREST,
                BIRCH_FOREST_HILLS, DARK_FOREST, GIANT_TREE_TAIGA, GIANT_TREE_TAIGA_HILLS, WOODED_MOUNTAINS, SAVANNA, SAVANNA_PLATEAU, BADLANDS, WOODED_BADLANDS_PLATEAU, BADLANDS_PLATEAU, GRAVELLY_MOUNTAINS,
                TAIGA_MOUNTAINS, SWAMP_HILLS, MODIFIED_JUNGLE, MODIFIED_JUNGLE_EDGE, TALL_BIRCH_FOREST, TALL_BIRCH_HILLS,
                DARK_FOREST_HILLS, GIANT_SPRUCE_TAIGA, GIANT_TREE_TAIGA_HILLS, MODIFIED_GRAVELLY_MOUNTAINS, SHATTERED_SAVANNA, SHATTERED_SAVANNA_PLATEAU,
                ERODED_BADLANDS, MODIFIED_WOODED_BADLANDS_PLATEAU, MODIFIED_BADLANDS_PLATEAU, FROZEN_OCEAN, FROZEN_RIVER, SNOWY_TUNDRA, SNOWY_MOUNTAINS, MUSHROOM_FIELD_SHORE, MUSHROOM_FIELDS,
                SNOWY_BEACH, SNOWY_TAIGA, SNOWY_TAIGA_HILLS, ICE_SPIKES, SNOWY_TAIGA_MOUNTAINS);


        registerEntityWorldSpawn(ORIGINIUMSLUG, 4, PLAINS, DESERT, MOUNTAINS, FOREST, TAIGA, SWAMP, NETHER, BEACH, DESERT_HILLS, WOODED_HILLS,
                TAIGA_HILLS, MOUNTAIN_EDGE, JUNGLE, JUNGLE_HILLS, JUNGLE_EDGE, STONE_SHORE, BIRCH_FOREST,
                BIRCH_FOREST_HILLS, DARK_FOREST, GIANT_TREE_TAIGA, GIANT_TREE_TAIGA_HILLS, WOODED_MOUNTAINS, SAVANNA, SAVANNA_PLATEAU, BADLANDS, WOODED_BADLANDS_PLATEAU, BADLANDS_PLATEAU, GRAVELLY_MOUNTAINS,
                TAIGA_MOUNTAINS, SWAMP_HILLS, MODIFIED_JUNGLE, MODIFIED_JUNGLE_EDGE, TALL_BIRCH_FOREST, TALL_BIRCH_HILLS,
                DARK_FOREST_HILLS, GIANT_SPRUCE_TAIGA, GIANT_TREE_TAIGA_HILLS, MODIFIED_GRAVELLY_MOUNTAINS, SHATTERED_SAVANNA, SHATTERED_SAVANNA_PLATEAU,
                ERODED_BADLANDS, MODIFIED_WOODED_BADLANDS_PLATEAU, MODIFIED_BADLANDS_PLATEAU);
    */
    }

    public static void registerEntityWorldSpawn(EntityType<?> entity, int weight, Biome... biomes) {
        for (Biome biome : biomes) {
            if (biome != null) {
                biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, weight, 1, 100));
            }
        }
    }
}
