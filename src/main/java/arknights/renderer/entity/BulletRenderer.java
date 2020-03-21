package arknights.renderer.entity;

import arknights.entity.model.BulletModel;
import arknights.entity.notLiving.BulletEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class BulletRenderer<T extends BulletEntity> extends EntityRenderer<BulletEntity> implements IEntityRenderer<BulletEntity, BulletModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("arknights:textures/entity/bullet.png");

    public BulletRenderer(EntityRendererManager manager) {
        super(manager);
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
