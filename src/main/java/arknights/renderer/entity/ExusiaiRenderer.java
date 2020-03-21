package arknights.renderer.entity;

import arknights.entity.model.ExusiaiModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import arknights.entity.ExusiaiEntity;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExusiaiRenderer extends MobRenderer<ExusiaiEntity, ExusiaiModel<ExusiaiEntity>> {
   private static final ResourceLocation EXUSIAI_TEXTURES = new ResourceLocation("arknights:textures/entity/exusiai.png");

   public ExusiaiRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new ExusiaiModel<>(0.0F), 0.3F);
   }

   protected float getDeathMaxRotation(ExusiaiEntity entityLivingBaseIn) {
      return 180.0F;
   }

   public ResourceLocation getEntityTexture(ExusiaiEntity entity) {
      return EXUSIAI_TEXTURES;
   }
}