package arknights.renderer.entity;

import arknights.entity.model.AmiyaModel;
import arknights.entity.model.AnselModel;
import arknights.entity.model.HumanModel;
import arknights.entity.operator.MedicSingle;
import arknights.entity.operator.OperatorBase;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class AnselRenderer<T extends MedicSingle> extends MobRenderer<MedicSingle, HumanModel<MedicSingle>> {
    public AnselRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HumanModel<>(0.0F), 0.3F);
    }

    protected float getDeathMaxRotation(MedicSingle entityLivingBaseIn) {
        return 90.0F;
    }

    public ResourceLocation getEntityTexture(MedicSingle entity) {
        return new ResourceLocation("arknights:textures/entity/ansel.png");
    }
}
