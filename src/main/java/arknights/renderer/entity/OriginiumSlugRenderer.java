package arknights.renderer.entity;

import arknights.entity.model.OriginiumSlugModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import arknights.entity.OriginiumSlugEntity;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
class OriginiumSlugRenderer extends MobRenderer<OriginiumSlugEntity, OriginiumSlugModel<OriginiumSlugEntity>> {
   private static final ResourceLocation ORIGINIUMSLUG_TEXTURES = new ResourceLocation("textures/entity/originiumslug/originiumslug.png");

   public OriginiumSlugRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new OriginiumSlugModel<>(), 0.3F);
   }

   protected float getDeathMaxRotation(OriginiumSlugEntity entityLivingBaseIn) {
      return 180.0F;
   }

   public ResourceLocation getEntityTexture(OriginiumSlugEntity entity) {
      return ORIGINIUMSLUG_TEXTURES;
   }
}