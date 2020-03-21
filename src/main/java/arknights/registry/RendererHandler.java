package arknights.registry;

import arknights.renderer.entity.BulletRenderer;
import arknights.renderer.entity.DisasterRenderer;
import arknights.renderer.entity.OriginiumSlugRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RendererHandler {
    @SubscribeEvent
    public static void register(){
        RenderingRegistry.registerEntityRenderingHandler(EntityHandler.ORIGINIUMSLUG, OriginiumSlugRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityHandler.BULLET, BulletRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityHandler.DISASTERZERO, DisasterRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHandler.SNOWSTORM, DisasterRenderer::new);
    }
}
