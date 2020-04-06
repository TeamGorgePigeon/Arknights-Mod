package arknights.renderer.entity;

import arknights.entity.model.CrownslayerModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class AnselRenderer extends OperatorRenderer {
    public AnselRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CrownslayerModel(0.0F), 0.3F, new ResourceLocation("arknights:textures/entity/ansel.png"));
        this.addLayer(new HeldItemLayer<>(this));
    }
}
