package arknights.renderer.entity;

import arknights.entity.enemy.FlyingEnemy;
import arknights.entity.model.MonsterUAVModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterUAVRenderer extends MobRenderer<FlyingEnemy, MonsterUAVModel<FlyingEnemy>> {
    private static final ResourceLocation MONSTERUAV_TEXTURES = new ResourceLocation("arknights:textures/entity/monster_uav.png");

    public MonsterUAVRenderer(EntityRendererManager p_i48829_1_) {
        super(p_i48829_1_, new MonsterUAVModel<>(), 0.75F);
    }


    public ResourceLocation getEntityTexture(FlyingEnemy entity) {
        return MONSTERUAV_TEXTURES;
    }

    protected void preRenderCallback(FlyingEnemy entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        int i = entitylivingbaseIn.getFlyingEnemySize();
        float f = 1.0F + 0.15F * (float)i;
        matrixStackIn.scale(f, f, f);
        matrixStackIn.translate(0.0D, 1.3125D, 0.1875D);
    }

    protected void applyRotations(FlyingEnemy entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(entityLiving.rotationPitch));
    }
}