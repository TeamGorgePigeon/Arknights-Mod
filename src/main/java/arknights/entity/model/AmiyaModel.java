package arknights.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;

public class AmiyaModel extends HumanModel {
    ModelRenderer ear = new ModelRenderer(this, 0, 0);
    ModelRenderer ear2 = new ModelRenderer(this, 0, 0);
    ModelRenderer trueEar = new ModelRenderer(this, 92, 54);

    public AmiyaModel(float modelSize) {
        super(modelSize);
        this.trueEar.addBox(-5.0F,-16F,-0.5F, 10.0F, 9.0F, 1.0F, modelSize);
        this.trueEar.setRotationPoint(this.bipedHeadwear.rotationPointX, this.bipedHeadwear.rotationPointY, this.bipedHeadwear.rotationPointZ);
        this.bipedHead.addChild(this.trueEar);
        //this.ear.func_228301_a_(1.0F, -14.0F, -0.5F, 2.0F, 7.0F, 1.0F, modelSize);
        //this.ear2.func_228301_a_(-3.0F, -14.0F, -0.5F, 2.0F, 7.0F, 1.0F, modelSize);
        //this.ear.setRotationPoint(this.bipedHeadwear.rotationPointX, this.bipedHeadwear.rotationPointY, this.bipedHeadwear.rotationPointZ);
        //this.ear2.setRotationPoint(this.bipedHeadwear.rotationPointX, this.bipedHeadwear.rotationPointY, this.bipedHeadwear.rotationPointZ);
        //this.bipedHead.addChild(ear);
        //this.bipedHead.addChild(ear2);
    }

    public void func_225599_a_(HandSide p_225599_1_, MatrixStack p_225599_2_) {
        this.trueEar.copyModelAngles(this.bipedHeadwear);
        //this.ear.copyModelAngles(this.bipedHeadwear);
        //this.ear2.copyModelAngles(this.bipedHeadwear);
    }
}
