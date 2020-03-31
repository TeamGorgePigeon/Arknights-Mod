package arknights.renderer.entity;

import arknights.entity.model.ProjektRedModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class ProjektRedRenderer extends OperatorRenderer {
    public ProjektRedRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ProjektRedModel(0.0F), 0.3F, new ResourceLocation("arknights:textures/entity/projektred.png"));
        this.addLayer(new HeldItemLayer<>(this));
    }
}
