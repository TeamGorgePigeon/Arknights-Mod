package arknights.entity.model;
//Made with Blockbench
//Paste this code into your mod.

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.lwjgl.opengl.GL11;
import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class MonsterUAVModel<T extends Entity> extends EntityModel<T> {
    private final ModelRenderer[] body = new ModelRenderer[6];
    private final ModelRenderer[] gun = new ModelRenderer[2];
    private final ModelRenderer leftwing;
    private final ModelRenderer leftwing1;
    private final ModelRenderer leftwing2;
    private final ModelRenderer rightwing;
    private final ModelRenderer leftwing4;
    private final ModelRenderer leftwing5;
    private final ModelRenderer[] wingfan = new ModelRenderer[2];
    private final ModelRenderer[] wingfan2 = new ModelRenderer[2];

    public MonsterUAVModel() {
        textureWidth = 64;
        textureHeight = 64;

        //body = new ModelRenderer(this);
        for(ModelRenderer modelRenderer : this.body){
            modelRenderer = new ModelRenderer(this, 0, 0);
            modelRenderer.setRotationPoint(0.0F, 24.0F, 0.0F);
        }
        this.body[0].addBox(-5.0F, -5.0F, -2.5F, 10, 4, 10);
        this.body[1].addBox(-5.0F, -7.0F, -0.5F, 10, 2, 8);
        this.body[2].addBox(-2.75F, -5.0F, -4.0F, 5, 4, 1);
        this.body[3].addBox(-7.0F, -6.0F, -1.5F, 2, 7, 7);
        this.body[4].addBox(5.25F, -6.0F, 0.5F, 2, 5, 4);
        this.body[5].addBox(-4.0F, -3.0F, 8.0F, 8, 2, 1);

        //body.setRotationPoint(0.0F, 24.0F, 0.0F);
        //body.cubeList.add(new ModelBox(body, 0, 0, -5.0F, -5.0F, -2.5F, 10, 4, 10, 0.0F, false));
        //body.cubeList.add(new ModelBox(body, 0, 0, -5.0F, -7.0F, -0.5F, 10, 2, 8, 0.0F, false));
        //body.cubeList.add(new ModelBox(body, 0, 0, -2.75F, -5.0F, -4.0F, 5, 4, 1, 0.0F, false));
        //body.cubeList.add(new ModelBox(body, 0, 0, -7.0F, -6.0F, -1.5F, 2, 7, 7, 0.0F, false));
        //body.cubeList.add(new ModelBox(body, 0, 0, 5.25F, -6.0F, 0.5F, 2, 5, 4, 0.0F, false));
        //body.cubeList.add(new ModelBox(body, 0, 0, -4.0F, -3.0F, 8.0F, 8, 2, 1, 0.0F, false));


        //gun = new ModelRenderer(this);
        for(ModelRenderer modelRenderer : this.gun){
            //modelRenderer = new ModelRenderer(this, 0, 0);
            modelRenderer.setRotationPoint(0.0F, 23.0F, 0.0F);
            setRotationAngle(modelRenderer, 0.5236F, 0.0F, 0.0F);
        }
        //gun.setRotationPoint(0.0F, 23.0F, 0.0F);
        //setRotationAngle(gun, 0.5236F, 0.0F, 0.0F);
        this.gun[0] = new ModelRenderer(this, 0, 5);
        this.gun[0].addBox(-0.5F, -1.8264F, -11.5152F, 1, 1, 10);
        this.gun[1] = new ModelRenderer(this, 0, 0);
        this.gun[1].addBox(-2.625F, -5.2F, -0.7546F, 5, 2, 3);

        //gun.cubeList.add(new ModelBox(gun, 0, 5, -0.5F, -1.8264F, -11.5152F, 1, 1, 10, 0.0F, false));
        //gun.cubeList.add(new ModelBox(gun, 0, 0, -2.625F, -5.2F, -0.7546F, 5, 2, 3, 0.0F, false));

        this.leftwing = new ModelRenderer(this);
        this.leftwing.setRotationPoint(0.0F, 24.0F, 0.0F);

        this.leftwing1 = new ModelRenderer(this);
        this.leftwing1.setRotationPoint(0.0F, -1.0F, 0.0F);
        setRotationAngle(this.leftwing1, -1.2217F, 0.5236F, 0.7854F);
        this.leftwing.addChild(leftwing1);
        this.leftwing1.addBox(-15.7753F, -1.6756F, -0.4291F, 10, 1, 1);

        this.leftwing2 = new ModelRenderer(this);
        this.leftwing2.setRotationPoint(-1.0F, -2.0F, 5.0F);
        setRotationAngle(this.leftwing2, -0.6981F, -0.3491F, 0.8727F);
        this.leftwing.addChild(leftwing2);
        this.leftwing2.addBox(-13.6826F, 1.3453F, -1.3256F, 10, 1, 1);

        this.rightwing = new ModelRenderer(this);
        this.rightwing.setRotationPoint(0.0F, 24.0F, 5.0F);
        setRotationAngle(this.rightwing, 0.0175F, 2.9671F, 0.0F);

        this.leftwing4 = new ModelRenderer(this);
        this.leftwing4.setRotationPoint(0.0F, -1.0F, 0.0F);
        setRotationAngle(this.leftwing4, -1.4835F, 0.5236F, 0.7854F);
        this.rightwing.addChild(leftwing4);
        this.leftwing4.addBox(-15.7753F, -1.6756F, -0.4291F, 10, 1, 1);

        this.leftwing5 = new ModelRenderer(this);
        this.leftwing5.setRotationPoint(-1.0F, -2.0F, 5.0F);
        setRotationAngle(this.leftwing5, -0.6981F, -0.3491F, 0.8727F);
        this.rightwing.addChild(leftwing5);
        this.leftwing5.addBox( -13.6826F, 1.3453F, -1.3256F, 10, 1, 1);

        //wingfan = new ModelRenderer(this);
        for(ModelRenderer modelRenderer : this.wingfan){
            modelRenderer = new ModelRenderer(this, 0, 0);
            modelRenderer.setRotationPoint(0.0F, 19.0F, 0.0F);
        }
        this.wingfan[0].addBox(-12.0F, -6.2F, -4.5F, 5, 1, 5);
        this.wingfan[1].addBox(-12.0F, -6.2F, 5.5F, 5, 1, 5);
        //wingfan.setRotationPoint(0.0F, 19.0F, 0.0F);
        //wingfan.cubeList.add(new ModelBox(wingfan, 0, 0, -12.0F, -6.2F, -4.5F, 5, 1, 5, 0.0F, false));
        //wingfan.cubeList.add(new ModelBox(wingfan, 0, 0, -12.0F, -6.2F, 5.5F, 5, 1, 5, 0.0F, false));

        for(ModelRenderer modelRenderer : this.wingfan2){
            modelRenderer = new ModelRenderer(this, 0, 0);
            modelRenderer.setRotationPoint(19.0F, 19.0F, 0.0F);
        }
        this.wingfan2[0].addBox(-12.0F, -6.2F, -4.5F, 5, 1, 5);
        this.wingfan2[1].addBox(-12.0F, -6.2F, 5.5F, 5, 1, 5);

        //wingfan2 = new ModelRenderer(this);
        //wingfan2.setRotationPoint(19.0F, 19.0F, 0.0F);
        //wingfan2.cubeList.add(new ModelBox(wingfan2, 0, 0, -12.0F, -6.2F, -4.5F, 5, 1, 5, 0.0F, false));
        //wingfan2.cubeList.add(new ModelBox(wingfan2, 0, 0, -12.0F, -6.2F, 5.5F, 5, 1, 5, 0.0F, false));
    }

    @Override
    public void setRotationAngles(T t, float v, float v1, float v2, float v3, float v4) {

    }


    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {

    }
}