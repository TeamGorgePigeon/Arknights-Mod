package arknights.renderer.entity;

import arknights.entity.model.OriginiumSlugModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import arknights.entity.living.OriginiumSlugEntity;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OriginiumSlugRenderer extends MobRenderer<OriginiumSlugEntity, OriginiumSlugModel<OriginiumSlugEntity>> {
   private static final ResourceLocation ORIGINIUMSLUG_TEXTURES = new ResourceLocation("arknights:textures/entity/originium_slug.png");

   public OriginiumSlugRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new OriginiumSlugModel<>(), 0.5F);
   }

   protected float getDeathMaxRotation(OriginiumSlugEntity entityLivingBaseIn) {
      return 180.0F;
   }

   public ResourceLocation getEntityTexture(OriginiumSlugEntity entity) {
      return ORIGINIUMSLUG_TEXTURES;
   }
}