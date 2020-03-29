package arknights.renderer.entity;

import arknights.entity.EnemyBase;
import arknights.entity.OperatorBase;
import arknights.entity.model.HumanModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class EnemyRenderer<T extends EnemyBase> extends MobRenderer<EnemyBase, HumanModel<EnemyBase>> {
    public ResourceLocation TEXTURES = null;

    public EnemyRenderer(EntityRendererManager renderManagerIn, ResourceLocation textures) {
        super(renderManagerIn, new HumanModel<>(0.0F), 0.3F);
        this.TEXTURES = textures;
    }

    public EnemyRenderer(EntityRendererManager renderManagerIn, HumanModel humanModel, float v, ResourceLocation textures) {
        super(renderManagerIn, humanModel, v);
        this.TEXTURES = textures;
    }

    protected float getDeathMaxRotation(EnemyBase entityLivingBaseIn) {
        return 180.0F;
    }

    public ResourceLocation getEntityTexture(EnemyBase entity) {
        return this.TEXTURES;
    }
}
