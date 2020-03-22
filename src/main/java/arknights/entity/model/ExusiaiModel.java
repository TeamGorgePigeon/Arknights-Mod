package arknights.entity.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import java.util.List;
import java.util.Random;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExusiaiModel extends OperatorModel {
   private List<ModelRenderer> field_228286_w_ = Lists.newArrayList();
   private final ModelRenderer bipedHalo;


   public ExusiaiModel(float modelSize ) {
      super(modelSize);
	  
	  this.bipedHalo = new ModelRenderer(this, 65, 0);
	  this.bipedHalo.setRotationPoint(0.0F, 24.0F, 0.0F);
	  this.bipedHalo.func_228301_a_(-4.0F, -33.0F, -4.0F, 8.0F, 8.0F, 0.0F, modelSize );
	/*
		Wing = new ModelRenderer(this);
		Wing.setRotationPoint(0.3326F, 5.4407F, 0.4995F);
		setRotationAngle(Wing, -0.0873F, 0.9599F, -0.1745F);

		RightWing = new ModelRenderer(this);
		RightWing.setRotationPoint(-1.4966F, -0.5688F, -7.4456F);
		setRotationAngle(RightWing, 0.0F, -2.0071F, 0.3491F);
		Wing.addChild(RightWing);
		RightWing.cubeList.add(new ModelBox(RightWing, 81, 47, 8.1639F, -2.8719F, -4.0539F, 0, 8, 9, 0.0F, true));

		LeftWing = new ModelRenderer(this);
		LeftWing.setRotationPoint(-0.6248F, -0.1343F, 4.7271F);
		setRotationAngle(LeftWing, -0.1745F, -3.0543F, 0.2618F);
		Wing.addChild(LeftWing);
		LeftWing.cubeList.add(new ModelBox(LeftWing, 63, 47, 0.2921F, -3.3063F, -5.2265F, 0, 8, 9, 0.0F, true));
		*/
   }

    public void func_225599_a_(HandSide p_225599_1_, MatrixStack p_225599_2_) {
        float f = p_225599_1_ == HandSide.RIGHT ? 1.0F : -1.0F;
        ModelRenderer modelrenderer = this.getArmForSide(p_225599_1_);
        modelrenderer.rotationPointX += f;
        modelrenderer.func_228307_a_(p_225599_2_);
        modelrenderer.rotationPointX -= f;
    }
}