package arknights.entity.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import java.util.Arrays;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OriginiumSlugModel<T extends Entity> extends SegmentedModel<T> {
   private final ModelRenderer[] bodyBoxes;
   private final ModelRenderer[] hornBoxes;
   private final ImmutableList<ModelRenderer> field_228295_f_;
   private final float[] zPlacement = new float[1];
   //private static final int[][] ORIGINIUMSlUG_BOX_LENGTH = new int[][]{{3, 2, 2}, {4, 3, 2}, {6, 4, 3}, {3, 3, 3}, {2, 2, 3}, {2, 1, 2}, {1, 1, 2}};
   //private static final int[][] ORIGINIUMSlUG_TEXTURE_POSITIONS = new int[][]{{0, 0}, {0, 4}, {0, 9}, {0, 16}, {0, 22}, {11, 0}, {13, 4}};

   public OriginiumSlugModel() {
      this.bodyBoxes = new ModelRenderer[1];
      this.textureWidth = 64;
      this.textureHeight = 64;
      float f = 0.0F;
      this.bodyBoxes[0] = new ModelRenderer(this, 0, 0);
      this.bodyBoxes[0].addBox(-4.0F, 0.0F, -5.0F, 8.0F, 6.0F, 10.0F);//4,3,5
      this.bodyBoxes[0].setRotationPoint(0.0F, 18.0F, f);
      this.zPlacement[0] = f;

      this.hornBoxes = new ModelRenderer[2];
      this.hornBoxes[0] = new ModelRenderer(this, 0, 16);
      this.hornBoxes[0].addBox(-8.0F, 0.0F, -1F, 16.0F, 10.0F, 2.0F);
      this.hornBoxes[0].setRotationPoint(0.0F, 14.0F, f);
      this.hornBoxes[1] = new ModelRenderer(this, 0, 32);
      this.hornBoxes[1].addBox(0.0F, 0.0F, -8.0F, 2.0F, 12.0F, 16.0F);
      this.hornBoxes[1].setRotationPoint(0.0F, 12.0F, f);
      Builder<ModelRenderer> builder = ImmutableList.builder();
      builder.addAll(Arrays.asList(this.bodyBoxes));
      builder.addAll(Arrays.asList(this.hornBoxes));
      this.field_228295_f_ = builder.build();
      /*
      for(int i = 0; i < this.bodyBoxes.length; ++i) {
         this.bodyBoxes[i] = new ModelRenderer(this, ORIGINIUMSlUG_TEXTURE_POSITIONS[i][0], ORIGINIUMSlUG_TEXTURE_POSITIONS[i][1]);
         this.bodyBoxes[i].func_228300_a_((float)ORIGINIUMSlUG_BOX_LENGTH[i][0] * -0.5F, 0.0F, (float)ORIGINIUMSlUG_BOX_LENGTH[i][2] * -0.5F, (float)ORIGINIUMSlUG_BOX_LENGTH[i][0], (float)ORIGINIUMSlUG_BOX_LENGTH[i][1], (float)ORIGINIUMSlUG_BOX_LENGTH[i][2]);
         this.bodyBoxes[i].setRotationPoint(0.0F, (float)(24 - ORIGINIUMSlUG_BOX_LENGTH[i][1]), f);
         this.zPlacement[i] = f;
         if (i < this.bodyBoxes.length - 1) {
            f += (float)(ORIGINIUMSlUG_BOX_LENGTH[i][2] + ORIGINIUMSlUG_BOX_LENGTH[i + 1][2]) * 0.5F;
         }
      }

      this.hornBoxes = new ModelRenderer[3];
      this.hornBoxes[0] = new ModelRenderer(this, 20, 0);
      this.hornBoxes[0].func_228300_a_(-5.0F, 0.0F, (float)ORIGINIUMSlUG_BOX_LENGTH[2][2] * -0.5F, 10.0F, 8.0F, (float)ORIGINIUMSlUG_BOX_LENGTH[2][2]);
      this.hornBoxes[0].setRotationPoint(0.0F, 16.0F, this.zPlacement[2]);
      this.hornBoxes[1] = new ModelRenderer(this, 20, 11);
      this.hornBoxes[1].func_228300_a_(-3.0F, 0.0F, (float)ORIGINIUMSlUG_BOX_LENGTH[4][2] * -0.5F, 6.0F, 4.0F, (float)ORIGINIUMSlUG_BOX_LENGTH[4][2]);
      this.hornBoxes[1].setRotationPoint(0.0F, 20.0F, this.zPlacement[4]);
      this.hornBoxes[2] = new ModelRenderer(this, 20, 18);
      this.hornBoxes[2].func_228300_a_(-3.0F, 0.0F, (float)ORIGINIUMSlUG_BOX_LENGTH[4][2] * -0.5F, 6.0F, 5.0F, (float)ORIGINIUMSlUG_BOX_LENGTH[1][2]);
      this.hornBoxes[2].setRotationPoint(0.0F, 19.0F, this.zPlacement[1]);
      Builder<ModelRenderer> builder = ImmutableList.builder();
      builder.addAll(Arrays.asList(this.bodyBoxes));
      builder.addAll(Arrays.asList(this.hornBoxes));
      this.field_228295_f_ = builder.build();*/
   }

   @Override
   public void setRotationAngles(T t, float v, float v1, float v2, float v3, float v4) {

   }

   @Override
   public Iterable<ModelRenderer> getParts() {
      return this.field_228295_f_;
   }


   public void func_225597_a_(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
      /*
      for(int i = 0; i < this.bodyBoxes.length; ++i) {
         this.bodyBoxes[i].rotateAngleY = MathHelper.cos(p_225597_4_ * 0.9F + (float)i * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(i - 2));
         this.bodyBoxes[i].rotationPointX = MathHelper.sin(p_225597_4_ * 0.9F + (float)i * 0.15F * (float)Math.PI) * (float)Math.PI * 0.2F * (float)Math.abs(i - 2);
      }

      this.hornBoxes[0].rotateAngleY = this.bodyBoxes[0].rotateAngleY;
      this.hornBoxes[1].rotateAngleY = this.bodyBoxes[0].rotateAngleY;

       */
      //this.hornBoxes[0].rotateAngleY = this.bodyBoxes[2].rotateAngleY;
      //this.hornBoxes[1].rotateAngleY = this.bodyBoxes[4].rotateAngleY;
      //this.hornBoxes[1].rotationPointX = this.bodyBoxes[4].rotationPointX;
      //this.hornBoxes[2].rotateAngleY = this.bodyBoxes[1].rotateAngleY;
      //this.hornBoxes[2].rotationPointX = this.bodyBoxes[1].rotationPointX;
   }
}