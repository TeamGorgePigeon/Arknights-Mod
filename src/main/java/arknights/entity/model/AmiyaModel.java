package arknights.entity.model;

import net.minecraft.client.renderer.model.ModelRenderer;

public class AmiyaModel extends OperatorModel {
    ModelRenderer ear = new ModelRenderer(this, 0, 0);
    ModelRenderer ear2 = new ModelRenderer(this, 0, 0);

    public AmiyaModel(float modelSize) {
        super(modelSize);
        this.ear.func_228301_a_(-3.0F, -9.0F, -2.0F, 6.0F, 5.0F, 7.0F, modelSize);
        this.ear2.func_228301_a_(-3.0F, -9.0F, -2.0F, 6.0F, 5.0F, 7.0F, modelSize);
        this.ear.setRotationPoint(this.bipedHeadwear.rotationPointX, this.bipedHeadwear.rotationPointY, this.bipedHeadwear.rotationPointZ);
        this.ear2.setRotationPoint(this.bipedHeadwear.rotationPointX, this.bipedHeadwear.rotationPointY, this.bipedHeadwear.rotationPointZ);
    }
}
