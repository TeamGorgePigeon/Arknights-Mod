package arknights.registry;

import arknights.entity.DisasterZero;
import arknights.entity.Meteorite;
import arknights.entity.SnowStorm;
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

    @SubscribeEvent
    public static void register(RegistryEvent.Register<EntityType<?>> evt){
        IForgeRegistry<EntityType<?>> r = evt.getRegistry();
        r.register(DISASTERZERO);
        r.register(SNOWSTORM);

        r.register(METEORITE);
    }
}
