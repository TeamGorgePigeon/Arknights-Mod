package arknights.renderer.entity;

import arknights.entity.model.CrownslayerModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class CrownslayerRenderer extends EnemyRenderer {
    public CrownslayerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CrownslayerModel(0.0F), 0.3F, new ResourceLocation("arknights:textures/entity/crownslayer.png"));
        this.addLayer(new HeldItemLayer<>(this));
    }
}
