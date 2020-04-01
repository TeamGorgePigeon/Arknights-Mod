package arknights.renderer.entity;

import arknights.entity.operator.RopeEntity;
import arknights.entity.special.Hook;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class HookRenderer extends EntityRenderer<Hook> {
    private static final ResourceLocation BOBBER = new ResourceLocation("textures/entity/fishing_hook.png");
    private static final RenderType field_229103_e_ = RenderType.func_228638_b_(BOBBER);

    public HookRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    public void func_225623_a_(Hook p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
        RopeEntity angler = p_225623_1_.getAngler();
        if (angler != null) {
            p_225623_4_.func_227860_a_();
            p_225623_4_.func_227860_a_();
            p_225623_4_.func_227862_a_(0.5F, 0.5F, 0.5F);
            p_225623_4_.func_227863_a_(this.renderManager.func_229098_b_());
            p_225623_4_.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F));
            MatrixStack.Entry matrixstack$entry = p_225623_4_.func_227866_c_();
            Matrix4f matrix4f = matrixstack$entry.func_227870_a_();
            Matrix3f matrix3f = matrixstack$entry.func_227872_b_();
            IVertexBuilder ivertexbuilder = p_225623_5_.getBuffer(field_229103_e_);
            func_229106_a_(ivertexbuilder, matrix4f, matrix3f, p_225623_6_, 0.0F, 0, 0, 1);
            func_229106_a_(ivertexbuilder, matrix4f, matrix3f, p_225623_6_, 1.0F, 0, 1, 1);
            func_229106_a_(ivertexbuilder, matrix4f, matrix3f, p_225623_6_, 1.0F, 1, 1, 0);
            func_229106_a_(ivertexbuilder, matrix4f, matrix3f, p_225623_6_, 0.0F, 1, 0, 0);
            p_225623_4_.func_227865_b_();

            float f = angler.getSwingProgress(p_225623_3_);
            float f1 = MathHelper.sin(MathHelper.sqrt(f) * (float)Math.PI);
            float f2 = MathHelper.lerp(p_225623_3_, angler.prevRenderYawOffset, angler.renderYawOffset) * ((float)Math.PI / 180F);
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
                Vec3d vec3d = new Vec3d(-0.36D * d7, -0.045D * d7, 0.4D);
                vec3d = vec3d.rotatePitch(-MathHelper.lerp(p_225623_3_, angler.prevRotationPitch, angler.rotationPitch) * ((float)Math.PI / 180F));
                vec3d = vec3d.rotateYaw(-MathHelper.lerp(p_225623_3_, angler.prevRotationYaw, angler.rotationYaw) * ((float)Math.PI / 180F));
                vec3d = vec3d.rotateYaw(f1 * 0.5F);
                vec3d = vec3d.rotatePitch(-f1 * 0.7F);
                d4 = MathHelper.lerp((double)p_225623_3_, angler.prevPosX, angler.func_226277_ct_()) + vec3d.x;
                d5 = MathHelper.lerp((double)p_225623_3_, angler.prevPosY, angler.func_226278_cu_()) + vec3d.y;
                d6 = MathHelper.lerp((double)p_225623_3_, angler.prevPosZ, angler.func_226281_cx_()) + vec3d.z;
                f3 = angler.getEyeHeight();
            } else {
                d4 = MathHelper.lerp((double)p_225623_3_, angler.prevPosX, angler.func_226277_ct_()) - d1 * d2 - d0 * 0.8D;
                d5 = angler.prevPosY + (double)angler.getEyeHeight() + (angler.func_226278_cu_() - angler.prevPosY) * (double)p_225623_3_ - 0.45D;
                d6 = MathHelper.lerp((double)p_225623_3_, angler.prevPosZ, angler.func_226281_cx_()) - d0 * d2 + d1 * 0.8D;
                f3 = angler.isCrouching() ? -0.1875F : 0.0F;
            }

            double d9 = MathHelper.lerp((double)p_225623_3_, p_225623_1_.prevPosX, p_225623_1_.func_226277_ct_());
            double d10 = MathHelper.lerp((double)p_225623_3_, p_225623_1_.prevPosY, p_225623_1_.func_226278_cu_()) + 0.25D;
            double d8 = MathHelper.lerp((double)p_225623_3_, p_225623_1_.prevPosZ, p_225623_1_.func_226281_cx_());
            float f4 = (float)(d4 - d9);
            float f5 = (float)(d5 - d10) + f3;
            float f6 = (float)(d6 - d8);
            IVertexBuilder ivertexbuilder1 = p_225623_5_.getBuffer(RenderType.func_228659_m_());
            Matrix4f matrix4f1 = p_225623_4_.func_227866_c_().func_227870_a_();
            int j = 16;

            for(int k = 0; k < 16; ++k) {
                func_229104_a_(f4, f5, f6, ivertexbuilder1, matrix4f1, func_229105_a_(k, 16));
                func_229104_a_(f4, f5, f6, ivertexbuilder1, matrix4f1, func_229105_a_(k + 1, 16));
            }

            p_225623_4_.func_227865_b_();
            super.func_225623_a_(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
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
