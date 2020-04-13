package arknights.entity.enemy;

import arknights.entity.notLiving.BulletEntity;
import arknights.entity.operator.OperatorBase;
import arknights.registry.SoundHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.List;

public class FlyingEnemy extends FlyingEntity implements IMob {
    private SummonEnemyGoal summonEnemy;
    protected int tick = 0;
    private static final DataParameter<Integer> SIZE = EntityDataManager.createKey(FlyingEnemy.class, DataSerializers.VARINT);
    private Vec3d orbitOffset = Vec3d.ZERO;
    private BlockPos orbitPosition = BlockPos.ZERO;
    private FlyingEnemy.AttackPhase attackPhase =FlyingEnemy.AttackPhase.CIRCLE;
    //public static final DataParameter<Boolean> OPERATORATTACKING = EntityDataManager.createKey(ExusiaiEntity.class, DataSerializers.BOOLEAN);

    static enum AttackPhase {
        CIRCLE,
        SWOOP;
    }

    public FlyingEnemy(EntityType<? extends FlyingEnemy> p_i48574_1_, World p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
        this.setCustomName(this.getDisplayName());
        this.moveController = new FlyingEnemy.MoveHelperController(this);
        this.lookController = new FlyingEnemy.LookHelperController(this);
    }

    class AttackEntityGoal extends Goal {
        private final EntityPredicate field_220842_b = (new EntityPredicate()).setDistance(64.0D);
        private int tickDelay = 20;

        private AttackEntityGoal() {
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            if (this.tickDelay > 0) {
                --this.tickDelay;
                return false;
            } else {
                this.tickDelay = 60;
                Vec3d vec3d = FlyingEnemy.this.getPositionVec();
                List<Entity> list = FlyingEnemy.this.world.getEntitiesWithinAABBExcludingEntity(FlyingEnemy.this, new AxisAlignedBB(vec3d.x - 16, vec3d.y - 16, vec3d.z - 16, vec3d.x + 16, vec3d.y + 16, vec3d.z + 16));
                if (!list.isEmpty()) {
                    list.sort((p_203140_0_, p_203140_1_) -> {
                        return p_203140_0_.func_226278_cu_() > p_203140_1_.func_226278_cu_() ? -1 : 1;
                    });

                    for(Entity entity : list) {
                        if (entity instanceof OperatorBase | entity instanceof PlayerEntity) {
                            if (FlyingEnemy.this.func_213344_a((LivingEntity) entity, EntityPredicate.DEFAULT)) {
                                FlyingEnemy.this.setAttackTarget((LivingEntity) entity);
                                return true;
                            }
                        }
                    }
                }

                return false;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            LivingEntity livingentity = FlyingEnemy.this.getAttackTarget();
            return livingentity != null ? FlyingEnemy.this.func_213344_a(livingentity, EntityPredicate.DEFAULT) : false;
        }
    }


    public void registerGoals() {
        this.goalSelector.addGoal(2, new FlyingEnemy.BulletAttackGoal());
        this.goalSelector.addGoal(3, new FlyingEnemy.OrbitPointGoal());
        this.targetSelector.addGoal(1, new AttackEntityGoal());
    }

    @Override
    protected void registerData() {
        super.registerData();
        //this.dataManager.register(OPERATORATTACKING, false);
    }

    @Override

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        //this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }

    static class SummonEnemyGoal extends Goal {
        private final EnemyBase enemyBase;

        public SummonEnemyGoal(EnemyBase enemyBaseIn) {
            this.enemyBase = enemyBaseIn;
        }

        @Override
        public boolean shouldExecute() {
            return false;
        }
    }

    abstract class MoveGoal extends Goal {
        public MoveGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        protected boolean func_203146_f() {
            return FlyingEnemy.this.orbitOffset.squareDistanceTo(FlyingEnemy.this.func_226277_ct_(), FlyingEnemy.this.func_226278_cu_(), FlyingEnemy.this.func_226281_cx_()) < 4.0D;
        }
    }

    class OrbitPointGoal extends FlyingEnemy.MoveGoal {
        private float field_203150_c;
        private float field_203151_d;
        private float field_203152_e;
        private float field_203153_f;

        private OrbitPointGoal() {
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            return FlyingEnemy.this.getAttackTarget() == null || FlyingEnemy.this.attackPhase == FlyingEnemy.AttackPhase.CIRCLE;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.field_203151_d = 5.0F + FlyingEnemy.this.rand.nextFloat() * 10.0F;
            this.field_203152_e = -4.0F + FlyingEnemy.this.rand.nextFloat() * 9.0F;
            this.field_203153_f = FlyingEnemy.this.rand.nextBoolean() ? 1.0F : -1.0F;
            this.func_203148_i();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (FlyingEnemy.this.rand.nextInt(350) == 0) {
                this.field_203152_e = -4.0F + FlyingEnemy.this.rand.nextFloat() * 9.0F;
            }

            if (FlyingEnemy.this.rand.nextInt(250) == 0) {
                ++this.field_203151_d;
                if (this.field_203151_d > 15.0F) {
                    this.field_203151_d = 5.0F;
                    this.field_203153_f = -this.field_203153_f;
                }
            }

            if (FlyingEnemy.this.rand.nextInt(450) == 0) {
                this.field_203150_c = FlyingEnemy.this.rand.nextFloat() * 2.0F * (float)Math.PI;
                this.func_203148_i();
            }

            if (this.func_203146_f()) {
                this.func_203148_i();
            }

            if (FlyingEnemy.this.orbitOffset.y < FlyingEnemy.this.func_226278_cu_() && !FlyingEnemy.this.world.isAirBlock((new BlockPos(FlyingEnemy.this)).down(1))) {
                this.field_203152_e = Math.max(1.0F, this.field_203152_e);
                this.func_203148_i();
            }

            if (FlyingEnemy.this.orbitOffset.y > FlyingEnemy.this.func_226278_cu_() && !FlyingEnemy.this.world.isAirBlock((new BlockPos(FlyingEnemy.this)).up(1))) {
                this.field_203152_e = Math.min(-1.0F, this.field_203152_e);
                this.func_203148_i();
            }

        }

        private void func_203148_i() {
            if (BlockPos.ZERO.equals(FlyingEnemy.this.orbitPosition)) {
                FlyingEnemy.this.orbitPosition = new BlockPos(FlyingEnemy.this);
            }

            this.field_203150_c += this.field_203153_f * 15.0F * ((float)Math.PI / 180F);
            FlyingEnemy.this.orbitOffset = (new Vec3d(FlyingEnemy.this.orbitPosition)).add((double)(this.field_203151_d * MathHelper.cos(this.field_203150_c)), (double)(-4.0F + this.field_203152_e), (double)(this.field_203151_d * MathHelper.sin(this.field_203150_c)));
        }
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.75F;
    }
        //public SoundEvent getDeathSound() {
            //return SoundHandler.OPERATOR_DEAD;
        //}
        class BulletAttackGoal extends FlyingEnemy.MoveGoal {
            private BulletAttackGoal() {
            }

            /**
             * Returns whether the EntityAIBase should begin execution.
             */
            public boolean shouldExecute() {
                return FlyingEnemy.this.getAttackTarget() != null && FlyingEnemy.this.attackPhase == FlyingEnemy.AttackPhase.SWOOP;
            }

            /**
             * Returns whether an in-progress EntityAIBase should continue executing
             */
            public boolean shouldContinueExecuting() {
                LivingEntity livingentity = FlyingEnemy.this.getAttackTarget();
                if (livingentity == null) {
                    return false;
                } else if (!livingentity.isAlive()) {
                    return false;
                } else if (!(livingentity instanceof PlayerEntity) || !((PlayerEntity) livingentity).isSpectator() && !((PlayerEntity) livingentity).isCreative()) {
                    if (!this.shouldExecute()) {
                        return false;
                    } else {
                        if (FlyingEnemy.this.ticksExisted % 20 == 0) {
                            List<CatEntity> list = FlyingEnemy.this.world.getEntitiesWithinAABB(CatEntity.class, FlyingEnemy.this.getBoundingBox().grow(16.0D), EntityPredicates.IS_ALIVE);
                            if (!list.isEmpty()) {
                                for (CatEntity catentity : list) {
                                    catentity.func_213420_ej();
                                }

                                return false;
                            }
                        }

                        return true;
                    }
                } else {
                    return false;
                }
            }

            /**
             * Execute a one shot task or start executing a continuous task
             */
            public void startExecuting() {
            }

            /**
             * Reset the task's internal state. Called when this task is interrupted by another one
             */
            public void resetTask() {
                FlyingEnemy.this.setAttackTarget((LivingEntity) null);
                FlyingEnemy.this.attackPhase = FlyingEnemy.AttackPhase.CIRCLE;
            }

            /**
             * Keep ticking a continuous task that has already been started
             */
            public void tick() {
                LivingEntity livingentity = FlyingEnemy.this.getAttackTarget();
                if (FlyingEnemy.this.getBoundingBox().grow((double) 0.2F).intersects(livingentity.getBoundingBox())) {
                    FlyingEnemy.this.attackEntityWithRangedAttack(livingentity);
                    FlyingEnemy.this.attackPhase = FlyingEnemy.AttackPhase.CIRCLE;
                    FlyingEnemy.this.world.playEvent(1039, new BlockPos(FlyingEnemy.this), 0);
                } else if (FlyingEnemy.this.collidedHorizontally || FlyingEnemy.this.hurtTime > 0) {
                    FlyingEnemy.this.attackPhase = FlyingEnemy.AttackPhase.CIRCLE;
                }
            }
        }


    class MoveHelperController extends MovementController {
        private float speedFactor = 0.1F;

        public MoveHelperController(MobEntity entityIn) {
            super(entityIn);
        }

        public void tick() {
            if (FlyingEnemy.this.collidedHorizontally) {
                FlyingEnemy.this.rotationYaw += 180.0F;
                this.speedFactor = 0.1F;
            }

            float f = (float)(FlyingEnemy.this.orbitOffset.x - FlyingEnemy.this.func_226277_ct_());
            float f1 = (float)(FlyingEnemy.this.orbitOffset.y - FlyingEnemy.this.func_226278_cu_());
            float f2 = (float)(FlyingEnemy.this.orbitOffset.z - FlyingEnemy.this.func_226281_cx_());
            double d0 = (double)MathHelper.sqrt(f * f + f2 * f2);
            double d1 = 1.0D - (double)MathHelper.abs(f1 * 0.7F) / d0;
            f = (float)((double)f * d1);
            f2 = (float)((double)f2 * d1);
            d0 = (double)MathHelper.sqrt(f * f + f2 * f2);
            double d2 = (double)MathHelper.sqrt(f * f + f2 * f2 + f1 * f1);
            float f3 = FlyingEnemy.this.rotationYaw;
            float f4 = (float)MathHelper.atan2((double)f2, (double)f);
            float f5 = MathHelper.wrapDegrees(FlyingEnemy.this.rotationYaw + 90.0F);
            float f6 = MathHelper.wrapDegrees(f4 * (180F / (float)Math.PI));
            FlyingEnemy.this.rotationYaw = MathHelper.approachDegrees(f5, f6, 4.0F) - 90.0F;
            FlyingEnemy.this.renderYawOffset = FlyingEnemy.this.rotationYaw;
            if (MathHelper.degreesDifferenceAbs(f3, FlyingEnemy.this.rotationYaw) < 3.0F) {
                this.speedFactor = MathHelper.approach(this.speedFactor, 1.8F, 0.005F * (1.8F / this.speedFactor));
            } else {
                this.speedFactor = MathHelper.approach(this.speedFactor, 0.2F, 0.025F);
            }

            float f7 = (float)(-(MathHelper.atan2((double)(-f1), d0) * (double)(180F / (float)Math.PI)));
            FlyingEnemy.this.rotationPitch = f7;
            float f8 = FlyingEnemy.this.rotationYaw + 90.0F;
            double d3 = (double)(this.speedFactor * MathHelper.cos(f8 * ((float)Math.PI / 180F))) * Math.abs((double)f / d2);
            double d4 = (double)(this.speedFactor * MathHelper.sin(f8 * ((float)Math.PI / 180F))) * Math.abs((double)f2 / d2);
            double d5 = (double)(this.speedFactor * MathHelper.sin(f7 * ((float)Math.PI / 180F))) * Math.abs((double)f1 / d2);
            Vec3d vec3d = FlyingEnemy.this.getMotion();
            FlyingEnemy.this.setMotion(vec3d.add((new Vec3d(d3, d5, d4)).subtract(vec3d).scale(0.2D)));
        }
    }

    class LookHelperController extends LookController {
        public LookHelperController(MobEntity entityIn) {
            super(entityIn);
        }

        /**
         * Updates look
         */
        public void tick() {
        }
    }

    private boolean attackEntityWithRangedAttack(LivingEntity entityIn) {
        float f = 3.0f;//(float) this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);
        if (flag) {
            double deltaX = entityIn.func_226277_ct_() - this.func_226277_ct_();
            double deltaY;
            double deltaZ = entityIn.func_226281_cx_() - this.func_226281_cx_();
            BulletEntity bulletEntity = new BulletEntity(this, this.world);
            deltaY = entityIn.func_226283_e_(0.3333333333333333D) - bulletEntity.func_226278_cu_();
            bulletEntity.shoot(deltaX, deltaY, deltaZ, 1.6F, 1.0F);
            this.playSound(SoundHandler.EXUSIAI_ATTACK, 1.0F, 1.0F);
            this.world.addEntity(bulletEntity);
        }
        return flag;
    }
}

