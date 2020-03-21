package arknights.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class DisasterRenderer extends EntityRenderer {
    public DisasterRenderer(EntityRendererManager p_i46179_1_) {
        super(p_i46179_1_);
        Minecraft.getInstance().gameSettings.gamma = 0;
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return null;
    }

}
