package arknights.renderer.entity;

import arknights.entity.model.FaustModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class FaustRenderer extends EnemyRenderer {
    public FaustRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FaustModel(0.0F), 0.3F, new ResourceLocation("arknights:textures/entity/faust.png"));
        this.addLayer(new HeldItemLayer<>(this));
    }
}
