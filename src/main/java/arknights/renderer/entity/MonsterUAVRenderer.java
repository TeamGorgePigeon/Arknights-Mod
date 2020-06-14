package arknights.renderer.entity;

import arknights.entity.enemy.FlyingEnemy;
import arknights.entity.model.MonsterUAVModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
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

    protected void func_225620_a_(FlyingEnemy p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
        int i = p_225620_1_.getFlyingEnemySize();
        float f = 1.0F + 0.15F * (float)i;
        p_225620_2_.func_227862_a_(f, f, f);
        p_225620_2_.func_227861_a_(0.0D, 1.3125D, 0.1875D);
    }

    protected void func_225621_a_(FlyingEnemy p_225621_1_, MatrixStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        super.func_225621_a_(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
        p_225621_2_.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(p_225621_1_.rotationPitch));
    }
}