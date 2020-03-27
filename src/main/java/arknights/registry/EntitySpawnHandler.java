package arknights.registry;

import arknights.entity.living.OriginiumSlugEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;

import static net.minecraft.world.biome.Biomes.*;

public class EntitySpawnHandler {
    public static void register(){
        EntitySpawnPlacementRegistry.register(EntityHandler.ORIGINIUMSLUG, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, OriginiumSlugEntity::spawnCondition);
        PLAINS.getSpawns(EntityClassification.MONSTER).clear();
        //PLAINS.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(EntityHandler.ORIGINIUMSLUG, 100, 1, 10));

        /*
        registerEntityWorldSpawn(EntityHandler.DISASTERZERO, 1, PLAINS, DESERT, MOUNTAINS, FOREST, TAIGA, SWAMP, BEACH, DESERT_HILLS, WOODED_HILLS,
                TAIGA_HILLS, MOUNTAIN_EDGE, JUNGLE, JUNGLE_HILLS, JUNGLE_EDGE, STONE_SHORE, BIRCH_FOREST,
                BIRCH_FOREST_HILLS, DARK_FOREST, GIANT_TREE_TAIGA, GIANT_TREE_TAIGA_HILLS, WOODED_MOUNTAINS, SAVANNA, SAVANNA_PLATEAU, BADLANDS, WOODED_BADLANDS_PLATEAU, BADLANDS_PLATEAU, GRAVELLY_MOUNTAINS,
                TAIGA_MOUNTAINS, SWAMP_HILLS, MODIFIED_JUNGLE, MODIFIED_JUNGLE_EDGE, TALL_BIRCH_FOREST, TALL_BIRCH_HILLS,
                DARK_FOREST_HILLS, GIANT_SPRUCE_TAIGA, GIANT_TREE_TAIGA_HILLS, MODIFIED_GRAVELLY_MOUNTAINS, SHATTERED_SAVANNA, SHATTERED_SAVANNA_PLATEAU,
                ERODED_BADLANDS, MODIFIED_WOODED_BADLANDS_PLATEAU, MODIFIED_BADLANDS_PLATEAU, FROZEN_OCEAN, FROZEN_RIVER, SNOWY_TUNDRA, SNOWY_MOUNTAINS, MUSHROOM_FIELD_SHORE, MUSHROOM_FIELDS,
                SNOWY_BEACH, SNOWY_TAIGA, SNOWY_TAIGA_HILLS, ICE_SPIKES, SNOWY_TAIGA_MOUNTAINS);*/


        registerEntityWorldSpawn(EntityHandler.ORIGINIUMSLUG, 100, PLAINS, DESERT, MOUNTAINS, FOREST, TAIGA, SWAMP, NETHER, BEACH, DESERT_HILLS, WOODED_HILLS,
                TAIGA_HILLS, MOUNTAIN_EDGE, JUNGLE, JUNGLE_HILLS, JUNGLE_EDGE, STONE_SHORE, BIRCH_FOREST,
                BIRCH_FOREST_HILLS, DARK_FOREST, GIANT_TREE_TAIGA, GIANT_TREE_TAIGA_HILLS, WOODED_MOUNTAINS, SAVANNA, SAVANNA_PLATEAU, BADLANDS, WOODED_BADLANDS_PLATEAU, BADLANDS_PLATEAU, GRAVELLY_MOUNTAINS,
                TAIGA_MOUNTAINS, SWAMP_HILLS, MODIFIED_JUNGLE, MODIFIED_JUNGLE_EDGE, TALL_BIRCH_FOREST, TALL_BIRCH_HILLS,
                DARK_FOREST_HILLS, GIANT_SPRUCE_TAIGA, GIANT_TREE_TAIGA_HILLS, MODIFIED_GRAVELLY_MOUNTAINS, SHATTERED_SAVANNA, SHATTERED_SAVANNA_PLATEAU,
                ERODED_BADLANDS, MODIFIED_WOODED_BADLANDS_PLATEAU, MODIFIED_BADLANDS_PLATEAU);
    }

    public static void registerEntityWorldSpawn(EntityType<?> entity, int weight, Biome... biomes) {
        for (Biome biome : biomes) {
            if (biome != null) {
                biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, weight, 1, 100));
            }
        }
    }
}
