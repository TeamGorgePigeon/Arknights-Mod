package arknights.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class ExusiaiEntity extends TameableEntity {
    private ExusiaiEntity.SummonExusiaiGoal summonExusiai;

    public ExusiaiEntity(EntityType<? extends ExusiaiEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, this.sitGoal);
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.summonExusiai = new ExusiaiEntity.SummonExusiaiGoal(this);
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(3, this.summonExusiai);
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, true));
    }

    /**
     * Returns the Y Offset of this entity.
     */

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.1F;
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }

    protected boolean func_225502_at_() {
        return false;
    }


    /**
     * Called when the entity is attacked.
     */


    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return null;
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

    static class SummonExusiaiGoal extends Goal {
        private final ExusiaiEntity exusiai;

        public SummonExusiaiGoal(ExusiaiEntity exusiaiIn) {
            this.exusiai = exusiaiIn;
        }

        @Override
        public boolean shouldExecute() {
            return false;
        }
    }
}

