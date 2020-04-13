package arknights.renderer.entity;

import arknights.entity.enemy.FlyingEnemy;
import arknights.entity.model.MonsterUAVModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterUAVRenderer extends MobRenderer<FlyingEnemy, MonsterUAVModel<FlyingEnemy>> {
    private static final ResourceLocation MONSTERUAV_TEXTURES = new ResourceLocation("arknights:textures/entity/monster_uav.png");

    public MonsterUAVRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MonsterUAVModel<>(), 0.5F);
    }

    protected float getDeathMaxRotation(FlyingEnemy entityLivingBaseIn) {
        return 180.0F;
    }

    public ResourceLocation getEntityTexture(FlyingEnemy entity) {
        return MONSTERUAV_TEXTURES;
    }
}