package arknights.entity.model;

import arknights.entity.operator.ExusiaiEntity;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;

import java.util.List;
import java.util.Random;

public class HumanModel<T extends LivingEntity> extends BipedModel<T> {
    private List<ModelRenderer> field_228286_w_ = Lists.newArrayList();
    public final ModelRenderer bipedLeftArmwear;
    public final ModelRenderer bipedRightArmwear;
    public final ModelRenderer bipedLeftLegwear;
    public final ModelRenderer bipedRightLegwear;
    public final ModelRenderer bipedBodyWear;
    public final ModelRenderer bipedDeadmau5Head;
    //private final ModelRenderer bipedHalo;


    public HumanModel(float modelSize) {
        super(RenderType::getEntityTranslucent, modelSize, 0.0F, 128, 64);
        this.bipedDeadmau5Head = new ModelRenderer(this, 24, 0);
        this.bipedDeadmau5Head.addBox(-3.0F, -6.0F, -1.0F, 6.0F, 6.0F, 1.0F, modelSize-0.1F);

        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-4.0F, -6.7055F, -3.8955F, 8, 8, 8, modelSize-0.6F);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.bipedHeadwear = new ModelRenderer(this, 32, 0);
        this.bipedHeadwear.addBox( -4.0F, -6.7055F, -3.8955F, 8, 8, 8, modelSize-0.1F);
        this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.bipedLeftArm = new ModelRenderer(this, 32, 48);
        this.bipedLeftArm.addBox(-1.5F, -1.8F, -2.0F, 3, 12, 4, modelSize-0.25F);
        this.bipedLeftArm.setRotationPoint(5.0F, 3.0F, 0.0F);

        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.addBox(-1.5F, -1.2F, -2.0F, 3, 12, 4, modelSize-0.25F);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);

        this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
        this.bipedLeftArmwear.addBox(-1.5F, -1.8F, -2.0F, 3, 12, 4, modelSize);
        this.bipedLeftArmwear.setRotationPoint(5.0F, 3.0F, 0.0F);

        this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
        this.bipedRightArmwear.addBox(-1.5F, -1.2F, -2.0F, 3, 12, 4, modelSize);
        this.bipedRightArmwear.setRotationPoint(-5.0F, 2.0F, 0.0F);

        this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, modelSize-0.5F);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);

        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, modelSize-0.5F);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);

        this.bipedLeftLegwear = new ModelRenderer(this, 0, 48);
        this.bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, modelSize-0.25F);
        this.bipedLeftLegwear.setRotationPoint(1.9F, 12.0F, 0.0F);

        this.bipedRightLegwear = new ModelRenderer(this, 0, 32);
        this.bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, modelSize-0.25F);
        this.bipedRightLegwear.setRotationPoint(-1.9F, 12.0F, 0.0F);

        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.addBox(-4.0F, 0.8F, -2.0F, 8, 12, 4, modelSize-0.3F);
        this.bipedBody.setRotationPoint(0.0F, 1.0F, 0.0F);

        this.bipedBodyWear = new ModelRenderer(this, 16, 32);
        this.bipedBodyWear.addBox( -4.0F, 1.3F, -2.0F, 8, 10, 4, modelSize-0.2F);
        this.bipedBodyWear.setRotationPoint(0.0F, 1.0F, 0.0F);
        //this.bipedHalo = new ModelRenderer(this, 65, 0);
        //this.bipedHalo.setRotationPoint(0.0F, 24.0F, 0.0F);
        //this.bipedHalo.func_228301_a_(-4.0F, -33.0F, -4.0F, 8.0F, 8.0F, 0.0F, modelSize );
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

    protected Iterable<ModelRenderer> getBodyParts() {
        return Iterables.concat(super.getBodyParts(), ImmutableList.of(this.bipedLeftLegwear, this.bipedRightLegwear, this.bipedLeftArmwear, this.bipedRightArmwear, this.bipedBodyWear));
    }

    public void func_228287_a_(MatrixStack p_228287_1_, IVertexBuilder p_228287_2_, int p_228287_3_, int p_228287_4_) {
        this.bipedDeadmau5Head.copyModelAngles(this.bipedHead);
        this.bipedDeadmau5Head.rotationPointX = 0.0F;
        this.bipedDeadmau5Head.rotationPointY = 0.0F;
        this.bipedDeadmau5Head.render(p_228287_1_, p_228287_2_, p_228287_3_, p_228287_4_);
    }


    public void setRotationAngles(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        super.setRotationAngles(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
        this.bipedLeftLegwear.copyModelAngles(this.bipedLeftLeg);
        this.bipedRightLegwear.copyModelAngles(this.bipedRightLeg);
        this.bipedLeftArmwear.copyModelAngles(this.bipedLeftArm);
        this.bipedRightArmwear.copyModelAngles(this.bipedRightArm);
        this.bipedBodyWear.copyModelAngles(this.bipedBody);
        this.bipedHeadwear.copyModelAngles(this.bipedHead);
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        this.bipedHead.showModel = visible;
        this.bipedHeadwear.showModel = visible;
        this.bipedLeftArmwear.showModel = visible;
        this.bipedRightArmwear.showModel = visible;
        this.bipedLeftLegwear.showModel = visible;
        this.bipedRightLegwear.showModel = visible;
        this.bipedBodyWear.showModel = visible;
        this.bipedDeadmau5Head.showModel = visible;
    }

    public void translateHan(HandSide p_225599_1_, MatrixStack p_225599_2_) {
        ModelRenderer modelrenderer = this.getArmForSide(p_225599_1_);
        float f = 0.5F * (float)(p_225599_1_ == HandSide.RIGHT ? 1 : -1);
        modelrenderer.rotationPointX += f;
        modelrenderer.translateRotate(p_225599_2_);
        modelrenderer.rotationPointX -= f;

    }
    public ModelRenderer func_228288_a_(Random p_228288_1_) {
        return this.field_228286_w_.get(p_228288_1_.nextInt(this.field_228286_w_.size()));
    }

    public void accept(ModelRenderer p_accept_1_) {
        if (this.field_228286_w_ == null) {
            this.field_228286_w_ = Lists.newArrayList();
        }

        this.field_228286_w_.add(p_accept_1_);
    }

}
