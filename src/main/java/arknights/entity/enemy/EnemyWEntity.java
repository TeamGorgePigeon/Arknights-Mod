package arknights.entity.enemy;

import arknights.entity.model.WModel;
import arknights.registry.SoundHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class EnemyWEntity extends RangeEnemy {
    public WModel model;
    public boolean isSkill=false;
    public boolean isThrowingGrenade=false;
    public int tick=0;


    public EnemyWEntity(EntityType<? extends EnemyWEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
        //this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemHandler.CROWNSLAYER_KNIFE));
    }

    @Override
    public void registerGoals() {
        super.registerGoals();
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity livingEntity, float v) {
        if (!this.world.isRemote) {
            if (this.isSkill) {
                this.attackTargetWithBomb(livingEntity);
                this.isSkill=false;
            }
            if (this.isThrowingGrenade) {
                this.attackTargetWithGrenade(livingEntity);
                this.isThrowingGrenade=false;
            }
        }
    }

    /**
     * Returns the Y Offset of this entity.
     */
    @Override
    protected void registerData() {
        super.registerData();
        this.getAttribute(Attributes.field_233818_a_).setBaseValue(40.0D);
        this.setHealth(40.0F);
        //this.dataManager.register(OPERATORATTACKING, false);
    }

    public void onDeath(DamageSource source) {
        dropItem(3,5);
    }

    public void livingTick() {
        this.tick++;

        if (this.tick%59==0 && this.tick!=295) {
            this.isThrowingGrenade=true;
        }
        if (this.tick==290 && this.getAttackTarget()!=null) {
            this.goalSelector.disableFlag(Goal.Flag.MOVE);
            this.goalSelector.disableFlag(Goal.Flag.JUMP);
            this.getAttackTarget().world.playSound((PlayerEntity)null, this.getAttackTarget().getPosX(), this.getAttackTarget().getPosY(), this.getAttackTarget().getPosZ(), SoundHandler.SKILL_ENEMYWCOUNTDOWN, this.getAttackTarget().getSoundCategory(), 1.0F, 1.0F);
        }
        if (this.tick==350) {
            this.tick=0;
            if (this.getAttackTarget()!=null) {
                this.goalSelector.enableFlag(Goal.Flag.MOVE);
                this.goalSelector.enableFlag(Goal.Flag.JUMP);
                this.isSkill=true;
            }
        }
        super.livingTick();
    }

    public void attackTargetWithBomb(LivingEntity entityTarget) {
        entityTarget.attackEntityFrom(DamageSource.causeExplosionDamage(this), 15);
        entityTarget.world.playSound((PlayerEntity)null, entityTarget.getPosX(), entityTarget.getPosY(), entityTarget.getPosZ(), SoundHandler.ENEMYW_SKILL_GRENADE, entityTarget.getSoundCategory(), 1.0F, 1.0F);
    }
    public void attackTargetWithGrenade(LivingEntity entityTarget) {
        entityTarget.attackEntityFrom(DamageSource.causeExplosionDamage(this), 3);
        entityTarget.world.playSound((PlayerEntity)null, entityTarget.getPosX(), entityTarget.getPosY(), entityTarget.getPosZ(), SoundHandler.ENEMYW_GRENADE, entityTarget.getSoundCategory(), 1.0F, 1.0F);
    }

    public static boolean spawnCondition (EntityType<? extends EnemyWEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
        //IWorldLightListener skyLight = world.func_225524_e_().getLightEngine(LightType.SKY);
        int light = world.getLightManager().getLightEngine(LightType.SKY).getLightFor(pos);
        return light > 1;
        //return true;
    }
}

