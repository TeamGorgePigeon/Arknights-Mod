package arknights.renderer.entity;

import arknights.entity.model.RopeModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class RopeRenderer extends OperatorRenderer {
    public RopeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RopeModel(0.0F), 0.3F, new ResourceLocation("arknights:textures/entity/rope.png"));
        this.addLayer(new HeldItemLayer<>(this));
    }
}
