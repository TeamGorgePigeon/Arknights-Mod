package arknights.entity.model;

import arknights.entity.enemy.CrossBowEnemy;
import arknights.entity.enemy.FaustEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class CrossBowEnemyModel extends HumanModel {
    /*ModelRenderer ear = new ModelRenderer(this, 0, 0);
    ModelRenderer ear2 = new ModelRenderer(this, 0, 0);
    ModelRenderer trueEar = new ModelRenderer(this, 92, 54);*/
    private float field_217145_m;
    public CrossBowEnemyModel(float modelSize) {
        super(modelSize);
        this.rightArmPose = ArmPose.CROSSBOW_HOLD;
    }

    public void func_225597_a_(FaustEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        this.bipedHead.rotateAngleY = p_225597_5_ * ((float)Math.PI / 180F);
        this.bipedHead.rotateAngleX = p_225597_6_ * ((float)Math.PI / 180F);
        if (this.isSitting) {
            this.bipedRightArm.rotateAngleX = (-(float)Math.PI / 5F);
            this.bipedRightArm.rotateAngleY = 0.0F;
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleX = (-(float)Math.PI / 5F);
            this.bipedLeftArm.rotateAngleY = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
        } else {
            this.bipedRightArm.rotateAngleX = MathHelper.cos(p_225597_2_ * 0.6662F + (float)Math.PI) * 2.0F * p_225597_3_ * 0.5F;
            this.bipedRightArm.rotateAngleY = 0.0F;
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleX = MathHelper.cos(p_225597_2_ * 0.6662F) * 2.0F * p_225597_3_ * 0.5F;
            this.bipedLeftArm.rotateAngleY = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
        }

        CrossBowEnemy.ArmPose crossBowEnemy$armPose = p_225597_1_.getArmPose();
        if (crossBowEnemy$armPose == CrossBowEnemy.ArmPose.ATTACKING) {
            float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
            float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
            this.bipedRightArm.rotateAngleY = 0.15707964F;
            this.bipedLeftArm.rotateAngleY = -0.15707964F;
            if (p_225597_1_.getPrimaryHand() == HandSide.RIGHT) {
                this.bipedRightArm.rotateAngleX = -1.8849558F + MathHelper.cos(p_225597_4_ * 0.09F) * 0.15F;
                this.bipedLeftArm.rotateAngleX = -0.0F + MathHelper.cos(p_225597_4_ * 0.19F) * 0.5F;
                this.bipedRightArm.rotateAngleX += f * 2.2F - f1 * 0.4F;
                this.bipedLeftArm.rotateAngleX += f * 1.2F - f1 * 0.4F;
            } else {
                this.bipedRightArm.rotateAngleX = -0.0F + MathHelper.cos(p_225597_4_ * 0.19F) * 0.5F;
                this.bipedLeftArm.rotateAngleX = -1.8849558F + MathHelper.cos(p_225597_4_ * 0.09F) * 0.15F;
                this.bipedRightArm.rotateAngleX += f * 1.2F - f1 * 0.4F;
                this.bipedLeftArm.rotateAngleX += f * 2.2F - f1 * 0.4F;
            }

            this.bipedRightArm.rotateAngleZ += MathHelper.cos(p_225597_4_ * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(p_225597_4_ * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArm.rotateAngleX += MathHelper.sin(p_225597_4_ * 0.067F) * 0.05F;
            this.bipedLeftArm.rotateAngleX -= MathHelper.sin(p_225597_4_ * 0.067F) * 0.05F;
        } else if (crossBowEnemy$armPose == CrossBowEnemy.ArmPose.SPELLCASTING) {
            this.bipedRightArm.rotationPointZ = 0.0F;
            this.bipedRightArm.rotationPointX = -5.0F;
            this.bipedLeftArm.rotationPointZ = 0.0F;
            this.bipedLeftArm.rotationPointX = 5.0F;
            this.bipedRightArm.rotateAngleX = MathHelper.cos(p_225597_4_ * 0.6662F) * 0.25F;
            this.bipedLeftArm.rotateAngleX = MathHelper.cos(p_225597_4_ * 0.6662F) * 0.25F;
            this.bipedRightArm.rotateAngleZ = 2.3561945F;
            this.bipedLeftArm.rotateAngleZ = -2.3561945F;
            this.bipedRightArm.rotateAngleY = 0.0F;
            this.bipedLeftArm.rotateAngleY = 0.0F;
        } else if (crossBowEnemy$armPose == CrossBowEnemy.ArmPose.BOW_AND_ARROW) {
            this.bipedRightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY;
            this.bipedRightArm.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = -0.9424779F + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleY = this.bipedHead.rotateAngleY - 0.4F;
            this.bipedLeftArm.rotateAngleZ = ((float)Math.PI / 2F);
        } else if (crossBowEnemy$armPose == CrossBowEnemy.ArmPose.CROSSBOW_HOLD) {
            this.bipedRightArm.rotateAngleY = -0.3F + this.bipedHead.rotateAngleY;
            this.bipedLeftArm.rotateAngleY = 0.6F + this.bipedHead.rotateAngleY;
            this.bipedRightArm.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX + 0.1F;
            this.bipedLeftArm.rotateAngleX = -1.5F + this.bipedHead.rotateAngleX;
        } else if (crossBowEnemy$armPose == CrossBowEnemy.ArmPose.CROSSBOW_CHARGE) {
            this.bipedRightArm.rotateAngleY = -0.8F;
            this.bipedRightArm.rotateAngleX = -0.97079635F;
            this.bipedLeftArm.rotateAngleX = -0.97079635F;
            float f2 = MathHelper.clamp(this.field_217145_m, 0.0F, 25.0F);
            this.bipedLeftArm.rotateAngleY = MathHelper.lerp(f2 / 25.0F, 0.4F, 0.85F);
            this.bipedLeftArm.rotateAngleX = MathHelper.lerp(f2 / 25.0F, this.bipedLeftArm.rotateAngleX, (-(float)Math.PI / 2F));
        } else if (crossBowEnemy$armPose == CrossBowEnemy.ArmPose.CELEBRATING) {
            this.bipedRightArm.rotationPointZ = 0.0F;
            this.bipedRightArm.rotationPointX = -5.0F;
            this.bipedRightArm.rotateAngleX = MathHelper.cos(p_225597_4_ * 0.6662F) * 0.05F;
            this.bipedRightArm.rotateAngleZ = 2.670354F;
            this.bipedRightArm.rotateAngleY = 0.0F;
            this.bipedLeftArm.rotationPointZ = 0.0F;
            this.bipedLeftArm.rotationPointX = 5.0F;
            this.bipedLeftArm.rotateAngleX = MathHelper.cos(p_225597_4_ * 0.6662F) * 0.05F;
            this.bipedLeftArm.rotateAngleZ = -2.3561945F;
            this.bipedLeftArm.rotateAngleY = 0.0F;
        }

        boolean flag = crossBowEnemy$armPose == CrossBowEnemy.ArmPose.CROSSED;
        this.bipedLeftArm.showModel = !flag;
        this.bipedRightArm.showModel = !flag;
    }

    public void setLivingAnimations(FaustEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.field_217145_m = (float)entityIn.getItemInUseMaxCount();
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    /*public void func_225599_a_(HandSide p_225599_1_, MatrixStack p_225599_2_) {
        this.trueEar.copyModelAngles(this.bipedHeadwear);
        //this.ear.copyModelAngles(this.bipedHeadwear);
        //this.ear2.copyModelAngles(this.bipedHeadwear);
    }*/
}
