package arknights.registry;

import arknights.renderer.entity.OriginiumSlugRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RendererHandler {
    @SubscribeEvent
    public static void register(){
        RenderingRegistry.registerEntityRenderingHandler(EntityHandler.ORIGINIUMSLUG, OriginiumSlugRenderer::new);
    }
}
