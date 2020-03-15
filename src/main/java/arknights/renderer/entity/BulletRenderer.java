package arknights.renderer.entity;

import arknights.entity.BulletEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;

public class BulletRenderer<T extends BulletEntity> extends EntityRenderer<T> {

    protected BulletRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void func_225623_a_(@Nonnull T entity, float entityYaw, float partialTick, @Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer, int light) {
        matrix.func_227860_a_();
        matrix.func_227861_a_(0.0D, 0.5D, 0.0D);

        matrix.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-90.0F));
        matrix.func_227861_a_(-0.5D, -0.5D, 0.5D);
        matrix.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(90.0F));
        matrix.func_227865_b_();
        super.func_225623_a_(entity, entityYaw, partialTick, matrix, renderer, light);
    }

    @Override
    public ResourceLocation getEntityTexture(T t) {
        return null;
    }
}
