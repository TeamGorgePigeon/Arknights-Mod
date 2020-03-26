package arknights.entity.model;

import arknights.entity.notLiving.AmiyaMagic;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class AmiyaMagicModel extends EntityModel<AmiyaMagic> {
    private final ModelRenderer bb_main;

    public AmiyaMagicModel(float modelsize){
        this.bb_main = new ModelRenderer(this,0,0);
        bb_main.func_228300_a_(-0.5F, -0.5F, -0.5F, 2.0F, 2.0F, 2.0F);
        bb_main.setRotationPoint(0.0F, 23.0F, -3.5F);
        //bb_main.cubeList.add(new ModelRenderer.ModelBox(bb_main, 0, 0, -1.0F, -9.0F, -1.0F, 2, 2, 2, 0.0F, false));

        bb_main.addChild(bb_main);
    }

    @Override
    public void func_225597_a_(AmiyaMagic amiyaMagic, float v, float v1, float v2, float v3, float v4) {

    }

    @Override
    public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {

    }
}
