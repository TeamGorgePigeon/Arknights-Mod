package arknights.registry;

import net.minecraft.entity.Entity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import arknights.renderer.entity.

public class RendererHandler {
    @SubscribeEvent
    public static void register(){
        RenderingRegistry.registerEntityRenderingHandler(EntityHandler.ORIGINIUMSLUG, OriginiumSlugRenderer);
    }
}
