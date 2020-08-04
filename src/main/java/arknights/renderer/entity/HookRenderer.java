/*package arknights.renderer.entity;

import arknights.entity.operator.RopeEntity;
import arknights.entity.special.Hook;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sun.javafx.geom.Matrix3f;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

public class HookRenderer extends EntityRenderer<Hook> {
    private static final ResourceLocation BOBBER = new ResourceLocation("textures/entity/fishing_hook.png");
    private static final RenderType field_229103_e_ = RenderType.func_228638_b_(BOBBER);

    public HookRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    public void render(Hook entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        RopeEntity angler = entityIn.getAngler();
        if (angler != null) {
            matrixStackIn.push();
            matrixStackIn.push();
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);
            matrixStackIn.rotate(this.renderManager.func_229098_b_());
            matrixStackIn.rotate(Vector3f.field_229181_d_.func_229187_a_(180.0F));
            MatrixStack.Entry matrixstack$entry = matrixStackIn.func_227866_c_();
            Matrix4f matrix4f = matrixstack$entry.func_227870_a_();
            Matrix3f matrix3f = matrixstack$entry.func_227872_b_();
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(field_229103_e_);
            func_229106_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0F, 0, 0, 1);
            func_229106_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0F, 0, 1, 1);
            func_229106_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0F, 1, 1, 0);
            func_229106_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0F, 1, 0, 0);
            matrixStackIn.pop();

            float f = angler.getSwingProgress(partialTicks);
            float f1 = MathHelper.sin(MathHelper.sqrt(f) * (float)Math.PI);
            float f2 = MathHelper.lerp(partialTicks, angler.prevRenderYawOffset, angler.renderYawOffset) * ((float)Math.PI / 180F);
            double d0 = (double)MathHelper.sin(f2);
            double d1 = (double)MathHelper.cos(f2);
            double d2 = 0.35D;
            double d3 = 0.8D;
            double d4;
            double d5;
            double d6;
            float f3;
            if ((this.renderManager.options == null || this.renderManager.options.thirdPersonView <= 0)) {
                double d7 = this.renderManager.options.fov;
                d7 = d7 / 100.0D;
                Vector3d vec3d = new Vector3d(-0.36D * d7, -0.045D * d7, 0.4D);
                vec3d = vec3d.rotatePitch(-MathHelper.lerp(partialTicks, angler.prevRotationPitch, angler.rotationPitch) * ((float)Math.PI / 180F));
                vec3d = vec3d.rotateYaw(-MathHelper.lerp(partialTicks, angler.prevRotationYaw, angler.rotationYaw) * ((float)Math.PI / 180F));
                vec3d = vec3d.rotateYaw(f1 * 0.5F);
                vec3d = vec3d.rotatePitch(-f1 * 0.7F);
                d4 = MathHelper.lerp((double)partialTicks, angler.prevPosX, angler.getPosX()) + vec3d.x;
                d5 = MathHelper.lerp((double)partialTicks, angler.prevPosY, angler.getPosY()) + vec3d.y;
                d6 = MathHelper.lerp((double)partialTicks, angler.prevPosZ, angler.getPosZ()) + vec3d.z;
                f3 = angler.getEyeHeight();
            } else {
                d4 = MathHelper.lerp((double)partialTicks, angler.prevPosX, angler.getPosX()) - d1 * d2 - d0 * 0.8D;
                d5 = angler.prevPosY + (double)angler.getEyeHeight() + (angler.getPosY() - angler.prevPosY) * (double)partialTicks - 0.45D;
                d6 = MathHelper.lerp((double)partialTicks, angler.prevPosZ, angler.getPosZ() - d0 * d2 + d1 * 0.8D;
                f3 = angler.isCrouching() ? -0.1875F : 0.0F;
            }

            double d9 = MathHelper.lerp((double)partialTicks, entityIn.prevPosX, entityIn.getPosX());
            double d10 = MathHelper.lerp((double)partialTicks, entityIn.prevPosY, entityIn.getPosY()) + 0.25D;
            double d8 = MathHelper.lerp((double)partialTicks, entityIn.prevPosZ, entityIn.getPosZ());
            float f4 = (float)(d4 - d9);
            float f5 = (float)(d5 - d10) + f3;
            float f6 = (float)(d6 - d8);
            IVertexBuilder ivertexbuilder1 = bufferIn.getBuffer(RenderType.func_228659_m_());
            Matrix4f matrix4f1 = matrixStackIn.func_227866_c_().func_227870_a_();
            int j = 16;

            for(int k = 0; k < 16; ++k) {
                func_229104_a_(f4, f5, f6, ivertexbuilder1, matrix4f1, func_229105_a_(k, 16));
                func_229104_a_(f4, f5, f6, ivertexbuilder1, matrix4f1, func_229105_a_(k + 1, 16));
            }

            matrixStackIn.pop();
            super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        }
    }

    private static float func_229105_a_(int p_229105_0_, int p_229105_1_) {
        return (float)p_229105_0_ / (float)p_229105_1_;
    }

    private static void func_229106_a_(IVertexBuilder p_229106_0_, Matrix4f p_229106_1_, Matrix3f p_229106_2_, int p_229106_3_, float p_229106_4_, int p_229106_5_, int p_229106_6_, int p_229106_7_) {
        p_229106_0_.func_227888_a_(p_229106_1_, p_229106_4_ - 0.5F, (float)p_229106_5_ - 0.5F, 0.0F).func_225586_a_(255, 255, 255, 255).func_225583_a_((float)p_229106_6_, (float)p_229106_7_).func_227891_b_(OverlayTexture.field_229196_a_).func_227886_a_(p_229106_3_).func_227887_a_(p_229106_2_, 0.0F, 1.0F, 0.0F).endVertex();
    }

    private static void func_229104_a_(float p_229104_0_, float p_229104_1_, float p_229104_2_, IVertexBuilder p_229104_3_, Matrix4f p_229104_4_, float p_229104_5_) {
        p_229104_3_.func_227888_a_(p_229104_4_, p_229104_0_ * p_229104_5_, p_229104_1_ * (p_229104_5_ * p_229104_5_ + p_229104_5_) * 0.5F + 0.25F, p_229104_2_ * p_229104_5_).func_225586_a_(0, 0, 0, 255).endVertex();
    }

    public ResourceLocation getEntityTexture(Hook entity) {
        return BOBBER;
    }
}
*/