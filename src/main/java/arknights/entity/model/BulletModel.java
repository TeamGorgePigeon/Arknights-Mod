package arknights.entity.model;

import arknights.entity.BulletEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class BulletModel extends EntityModel<BulletEntity> {
    private final ModelRenderer bb_main;

    public BulletModel() {
        textureWidth = 1;
        textureHeight = 1;

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
        //bb_main.cubeList.add(new ModelRenderer.ModelBox(bb_main, 0, 0, -1.0F, -9.0F, -1.0F, 2, 2, 2, 0.0F, false));

        bb_main.addChild(bb_main);
    }

    @Override
    public void func_225597_a_(BulletEntity bulletEntity, float v, float v1, float v2, float v3, float v4) {

    }
    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {

    }
}
