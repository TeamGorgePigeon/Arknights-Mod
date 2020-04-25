package arknights.registry;

import arknights.Arknights;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class SoundHandler {
    //enemy
    public static final SoundEvent ENEMY_DEAD = sound("enemy_dead");
    //operator
    public static final SoundEvent OPERATOR_DEAD = sound("operator_dead");

    public static final SoundEvent EXUSIAI_ATTACK = sound("exusiai_attack");
    public static final SoundEvent EXUSIAI_SKILLATTACK = sound("exusiai_skill_attack");
    public static final SoundEvent EXUSIAI_COMBATING1 = sound("exusiai_combating1");
    public static final SoundEvent EXUSIAI_COMBATING2 = sound("exusiai_combating2");
    public static final SoundEvent EXUSIAI_COMBATING3 = sound("exusiai_combating3");
    public static final SoundEvent EXUSIAI_COMBATING4 = sound("exusiai_combating4");

    public static final SoundEvent SHAW_COMBATING1 = sound("shaw_combating1");
    public static final SoundEvent SHAW_COMBATING2 = sound("shaw_combating2");
    public static final SoundEvent SHAW_COMBATING3 = sound("shaw_combating3");
    public static final SoundEvent SHAW_COMBATING4 = sound("shaw_combating4");

    public static final SoundEvent PROJEKTRED_COMBATING1 = sound("projektred_combating1");
    public static final SoundEvent PROJEKTRED_COMBATING2 = sound("projektred_combating2");
    public static final SoundEvent PROJEKTRED_COMBATING3 = sound("projektred_combating3");
    public static final SoundEvent PROJEKTRED_COMBATING4 = sound("projektredw_combating4");

    public static final SoundEvent SKILL_HYDRAULIC = sound("skill_hydraulic");
    public static final SoundEvent SKILL_WOLFROAR = sound("skill_skill_wolfroar");
    public static final SoundEvent SKILL_TRUESILVER_SLASH = sound("skill_truesilver_slash");


    private static SoundEvent sound(String soundName){
        ResourceLocation name = new ResourceLocation(Arknights.MODID,soundName);
        return new SoundEvent(name).setRegistryName(name);
    }

    public static void register(RegistryEvent.Register<SoundEvent> evt) {
        IForgeRegistry r = evt.getRegistry();
        r.registerAll(EXUSIAI_ATTACK,EXUSIAI_SKILLATTACK,EXUSIAI_COMBATING1,EXUSIAI_COMBATING2,EXUSIAI_COMBATING3,EXUSIAI_COMBATING4,SHAW_COMBATING1,SHAW_COMBATING2,SHAW_COMBATING3,SHAW_COMBATING4,PROJEKTRED_COMBATING1,PROJEKTRED_COMBATING2,PROJEKTRED_COMBATING3,PROJEKTRED_COMBATING4,SKILL_HYDRAULIC,SKILL_WOLFROAR,SKILL_TRUESILVER_SLASH,ENEMY_DEAD,OPERATOR_DEAD);
    }
}
