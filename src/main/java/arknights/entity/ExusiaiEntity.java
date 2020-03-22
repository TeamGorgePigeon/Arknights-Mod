package arknights.entity;

import arknights.entity.notLiving.BulletEntity;
import arknights.registry.SoundHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class ExusiaiEntity extends OperatorBase implements IRangedAttackMob{

    public ExusiaiEntity(EntityType<? extends ExusiaiEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 20, 10.0F));
    }

    /**
     * Returns the Y Offset of this entity.
     */

    protected void registerAttributes() {
        super.registerAttributes();
    }

    public void attackEntityWithRangedAttack(LivingEntity target, float var2){
        BulletEntity bulletEntity = new BulletEntity(this, this.world);
        double d0 = target.func_226277_ct_() - this.func_226277_ct_();
        double d1 = target.func_226283_e_(0.3333333333333333D) - bulletEntity.func_226278_cu_();
        double d2 = target.func_226281_cx_() - this.func_226281_cx_();
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        //float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        bulletEntity.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, 1.0F);
        this.playSound(SoundHandler.EXUSIAI_ATTACK, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.addEntity(bulletEntity);
    }

/*
    public static boolean func_223331_b(EntityType<ExusiaiEntity> p_223331_0_, IWorld p_223331_1_, SpawnReason p_223331_2_, BlockPos p_223331_3_, Random p_223331_4_) {
        if (func_223324_d(p_223331_0_, p_223331_1_, p_223331_2_, p_223331_3_, p_223331_4_)) {
            PlayerEntity playerentity = p_223331_1_.getClosestPlayer((double) p_223331_3_.getX() + 0.5D, (double) p_223331_3_.getY() + 0.5D, (double) p_223331_3_.getZ() + 0.5D, 5.0D, true);
            return playerentity == null;
        } else {
            return false;
        }
    }
*/
}

