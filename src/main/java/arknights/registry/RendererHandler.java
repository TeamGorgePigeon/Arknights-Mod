package arknights.registry;

import arknights.renderer.entity.*;
import net.minecraft.entity.Entity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RendererHandler {
    @SubscribeEvent
    public static void register(){
        RenderingRegistry.registerEntityRenderingHandler(EntityHandler.ORIGINIUMSLUG, OriginiumSlugRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityHandler.BULLET, BulletRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHandler.AMIYAMAGIC, AmiyaMagicRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityHandler.DISASTERZERO, DisasterRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHandler.SNOWSTORM, DisasterRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityHandler.EXUSIAI, ExusiaiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHandler.AMIYA, AmiyaRenderer::new);
    }
}
