package arknights.renderer.entity;

import arknights.entity.model.BulletModel;
import arknights.entity.notLiving.BulletEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class BulletRenderer<T extends BulletEntity> extends EntityRenderer<BulletEntity> implements IEntityRenderer<BulletEntity, BulletModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("arknights:textures/entity/bullet.png");
    protected final BulletModel model = new BulletModel();

    public BulletRenderer(EntityRendererManager manager) {
        super(manager);
    }

    public void func_225623_a_(BulletEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
        super.func_225623_a_(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
        p_225623_4_.func_227860_a_();
        long i = (long)p_225623_1_.getEntityId() * 493286711L;
        i = i * i * 4392167121L + i * 98761L;
        float f = (((float)(i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f1 = (((float)(i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f2 = (((float)(i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        p_225623_4_.func_227861_a_((double)f, (double)f1, (double)f2);
        double d0 = MathHelper.lerp((double)p_225623_3_, p_225623_1_.lastTickPosX, p_225623_1_.func_226277_ct_());
        double d1 = MathHelper.lerp((double)p_225623_3_, p_225623_1_.lastTickPosY, p_225623_1_.func_226278_cu_());
        double d2 = MathHelper.lerp((double)p_225623_3_, p_225623_1_.lastTickPosZ, p_225623_1_.func_226281_cx_());
        double d3 = (double)0.3F;
        //Vec3d vec3d = p_225623_1_.getPos(d0, d1, d2);
        float f3 = MathHelper.lerp(p_225623_3_, p_225623_1_.prevRotationPitch, p_225623_1_.rotationPitch);


        p_225623_4_.func_227861_a_(0.0D, 0.375D, 0.0D);
        p_225623_4_.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F - p_225623_2_));
        p_225623_4_.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(-f3));
        float f6 = (float) p_225623_1_.getDamage() - p_225623_3_;
        if (f6 < 0.0F) {
            f6 = 0.0F;
        }

        p_225623_4_.func_227862_a_(-1.0F, -1.0F, 1.0F);
        this.model.func_225597_a_(p_225623_1_, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F);
        IVertexBuilder ivertexbuilder = p_225623_5_.getBuffer(this.model.func_228282_a_(this.getEntityTexture(p_225623_1_)));
        this.model.func_225598_a_(p_225623_4_, ivertexbuilder, p_225623_6_, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
        p_225623_4_.func_227865_b_();
    }


    @Override
    public BulletModel getEntityModel() {
        return new BulletModel();
    }

    @Override
    public ResourceLocation getEntityTexture(BulletEntity bulletEntity) {
        return TEXTURE;
    }
}
