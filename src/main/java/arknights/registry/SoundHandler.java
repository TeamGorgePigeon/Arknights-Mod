package arknights.registry;

import arknights.Arknights;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class SoundHandler {
    //Exusiai
    public static final SoundEvent EXUSIAI_ATTACK = sound("vehicle_n");
    public static final SoundEvent EXUSIAI_SKILLATTACK = sound("vehicle_h");
    public static final SoundEvent EXUSIAI_SKILL1 = sound("103_skill1");
    public static final SoundEvent EXUSIAI_SKILL2 = sound("103_skill2");
    public static final SoundEvent EXUSIAI_SKILL3 = sound("103_skill3");
    public static final SoundEvent EXUSIAI_SKILL4 = sound("103_skill4");


    private static SoundEvent sound(String soundName){
        ResourceLocation name = new ResourceLocation(Arknights.MODID,soundName);
        return new SoundEvent(name).setRegistryName(name);
    }

    public static void register(RegistryEvent.Register<SoundEvent> evt) {
        IForgeRegistry r = evt.getRegistry();
        r.registerAll(EXUSIAI_ATTACK,EXUSIAI_SKILLATTACK,EXUSIAI_SKILL1,EXUSIAI_SKILL2,EXUSIAI_SKILL3,EXUSIAI_SKILL4);
    }
}
