package arknights.renderer.entity;

import arknights.entity.operator.OperatorBase;
import arknights.entity.model.HumanModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class OperatorRenderer<T extends OperatorBase> extends MobRenderer<OperatorBase, HumanModel<OperatorBase>> {
    public ResourceLocation TEXTURES = null;

    public OperatorRenderer(EntityRendererManager renderManagerIn, ResourceLocation textures) {
        super(renderManagerIn, new HumanModel<>(0.0F), 0.3F);
        this.TEXTURES = textures;
    }

    public OperatorRenderer(EntityRendererManager renderManagerIn, HumanModel humanModel, float v, ResourceLocation textures) {
        super(renderManagerIn, humanModel, v);
        this.TEXTURES = textures;
    }

    protected float getDeathMaxRotation(OperatorBase entityLivingBaseIn) {
        return 90.0F;
    }

    public ResourceLocation getEntityTexture(OperatorBase entity) {
        return this.TEXTURES;
    }
}
