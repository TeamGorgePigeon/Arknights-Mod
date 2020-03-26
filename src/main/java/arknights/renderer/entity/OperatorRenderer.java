package arknights.renderer.entity;

import arknights.entity.OperatorBase;
import arknights.entity.model.OperatorModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class OperatorRenderer<T extends OperatorBase> extends MobRenderer<OperatorBase, OperatorModel<OperatorBase>> {
    public ResourceLocation TEXTURES = null;

    public OperatorRenderer(EntityRendererManager renderManagerIn, ResourceLocation textures) {
        super(renderManagerIn, new OperatorModel<>(0.0F), 0.3F);
        this.TEXTURES = textures;
    }

    public OperatorRenderer(EntityRendererManager renderManagerIn, OperatorModel operatorModel, float v, ResourceLocation textures) {
        super(renderManagerIn, operatorModel, v);
        this.TEXTURES = textures;
    }

    protected float getDeathMaxRotation(OperatorBase entityLivingBaseIn) {
        return 180.0F;
    }

    public ResourceLocation getEntityTexture(OperatorBase entity) {
        return this.TEXTURES;
    }
}
