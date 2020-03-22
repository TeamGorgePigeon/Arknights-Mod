package arknights.renderer.entity;

import arknights.entity.model.ExusiaiModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import arknights.entity.ExusiaiEntity;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExusiaiRenderer extends OperatorRenderer {
   public ExusiaiRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new ExusiaiModel(0.0F), 0.3F, new ResourceLocation("arknights:textures/entity/exusiai.png"));
      this.addLayer(new HeldItemLayer<>(this));
   }
   //public ResourceLocation getEntityTexture(ExusiaiEntity entity) {
      //return TEXTURES;
   //}
}