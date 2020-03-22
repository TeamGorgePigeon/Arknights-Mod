package arknights.registry;

import arknights.entity.disaster.DisasterZero;
import arknights.entity.notLiving.BulletEntity;
import arknights.entity.notLiving.Meteorite;
import arknights.entity.living.OriginiumSlugEntity;
import arknights.entity.disaster.SnowStorm;
import arknights.entity.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static arknights.Arknights.MODID;

public class EntityHandler {
    public static final EntityType<DisasterZero> DISASTERZERO = (EntityType<DisasterZero>) EntityType.Builder.<DisasterZero>create(DisasterZero::new, EntityClassification.MISC).build("disaster_zero").setRegistryName(MODID + ":disaster_zero");
    public static final EntityType<SnowStorm> SNOWSTORM = (EntityType<SnowStorm>) EntityType.Builder.<SnowStorm>create(SnowStorm::new, EntityClassification.MISC).build("snowstorm").setRegistryName(MODID + ":snowstorm");

    public static final EntityType<Meteorite> METEORITE = (EntityType<Meteorite>)EntityType.Builder.<Meteorite>create(Meteorite::new, EntityClassification.MISC).build("meteorite").setRegistryName(MODID + ":" + "meteorite");
    public static final EntityType<BulletEntity> BULLET = (EntityType<BulletEntity>)EntityType.Builder.<BulletEntity>create(BulletEntity::new, EntityClassification.MISC).build("bullet").setRegistryName(MODID+ ":bullet");

    public static final EntityType<OriginiumSlugEntity> ORIGINIUMSLUG = (EntityType<OriginiumSlugEntity>)EntityType.Builder.<OriginiumSlugEntity>create(OriginiumSlugEntity::new, EntityClassification.MONSTER).size(0.8F, 0.5F).build("originium_slug").setRegistryName(MODID + ":" + "originium_slug");
    public static final EntityType<ExusiaiEntity> EXUSIAI = (EntityType<ExusiaiEntity>)EntityType.Builder.<ExusiaiEntity>create(ExusiaiEntity::new, EntityClassification.CREATURE).build("exusiai").setRegistryName(MODID + ":" + "exusiai");

    @SubscribeEvent
    public static void register(RegistryEvent.Register<EntityType<?>> evt){
        IForgeRegistry<EntityType<?>> r = evt.getRegistry();
        r.register(DISASTERZERO);
        r.register(SNOWSTORM);

        r.register(METEORITE);
        r.register(BULLET);

        r.register(ORIGINIUMSLUG);

		r.register(EXUSIAI);
    }
}
