package arknights.renderer.entity;

import arknights.entity.model.AmiyaModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class AmiyaRenderer extends OperatorRenderer {
    public AmiyaRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new AmiyaModel(0.0F), 0.3F, new ResourceLocation("arknights:textures/entity/amiya.png"));
        this.addLayer(new HeldItemLayer<>(this));
    }
}
