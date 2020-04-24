package arknights.renderer.entity;

import arknights.entity.model.ShawModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class ShawRenderer extends OperatorRenderer {
    public ShawRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ShawModel(-0.5F), 0.3F, new ResourceLocation("arknights:textures/entity/shaw.png"));
        this.addLayer(new HeldItemLayer<>(this));
    }
}
