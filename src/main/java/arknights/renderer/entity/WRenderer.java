package arknights.renderer.entity;

import arknights.entity.model.WModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class WRenderer extends EnemyRenderer {
    public WRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WModel(0.0F), 0.3F, new ResourceLocation("arknights:textures/entity/w.png"));
        this.addLayer(new HeldItemLayer<>(this));
    }
}
