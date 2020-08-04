package arknights.entity.operator;

import arknights.entity.ai.MedicSingleHealTarget;
import arknights.entity.enemy.EnemyBase;
import arknights.item.OperatorItem;
import arknights.registry.SoundHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class MedicSingle extends OperatorBase implements IRangedAttackMob {
    private MedicSingle.SummonOperatorGoal summonOperator;
    public Item item = Items.AIR;
    public static final DataParameter<Boolean> OPERATORATTACKING = EntityDataManager.createKey(ExusiaiEntity.class, DataSerializers.BOOLEAN);
    protected int sp;
    protected int tick = 0;
    protected boolean isSkill = false;
    protected List<LivingEntity> targets = new ArrayList<>();

    public MedicSingle(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
    }

    public MedicSingle(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_, Item item) {
        super(p_i48574_1_, p_i48574_2_);
        this.item = item;
    }

    public void tick(){
        super.tick();
        if(this.tick % 20 == 1) {
            float hp = 65536;
            int i = 0;
            int n = 0;
            if (!this.world.<LivingEntity>getEntitiesWithinAABB(PlayerEntity.class, this.getBoundingBox().grow(16, 4.0D, 16), null).isEmpty()) {
                for (LivingEntity entity : this.world.getEntitiesWithinAABB(PlayerEntity.class, this.getBoundingBox().grow(16, 4.0D, 16), null)) {
                    if (entity == this.getOwner() && !this.targets.contains(entity)) {
                        this.targets.add(entity);
                    }
                }
            }
            if (!this.world.<LivingEntity>getEntitiesWithinAABB(OperatorBase.class, this.getBoundingBox().grow(16, 4.0D, 16), null).isEmpty()) {
                for (LivingEntity entity : this.world.<LivingEntity>getEntitiesWithinAABB(OperatorBase.class, this.getBoundingBox().grow(16, 4.0D, 16), null)) {
                    if (entity != null) {
                        if (((OperatorBase) entity).getOwner() == this.getOwner() && !this.targets.contains(entity)) {
                            this.targets.add(entity);
                        }
                    }
                }
            }
            if (this.targets.size() > 0) {
                for (LivingEntity entity : this.targets) {
                    if (entity.getHealth() < hp) {
                        hp = entity.getHealth();
                        n = i;
                    }
                    i++;
                }
                this.setAttackTarget(this.targets.get(n));
            } else {
                //this.nearestTarget = null;
            }
        }
        this.tick++;
    }

    protected void registerGoals() {
        //this.goalSelector.addGoal(2, this.sitGoal);
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1.0F));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0F, 120));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.summonOperator = new MedicSingle.SummonOperatorGoal(this);
        this.goalSelector.addGoal(3, new SwimGoal(this));
        this.goalSelector.addGoal(3, this.summonOperator);
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 20, 10.0F));
        //this.targetSelector.addGoal(1, new MedicSingleHealTarget(this, true));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return TameableEntity.func_233639_cI_()
                .func_233815_a_(Attributes.field_233818_a_, 20.0D)
                .func_233815_a_(Attributes.field_233821_d_, 0.3D)
                .func_233815_a_(Attributes.field_233823_f_, 5.0D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(OPERATORATTACKING, false);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageableEntity) {
        return null;
    }


    public void onDeath(DamageSource cause) {
        if(this.item instanceof OperatorItem && !world.isRemote()){
            ItemEntity entity = new ItemEntity(world, this.getPositionVec().getX(), this.getPositionVec().getY(), this.getPositionVec().getZ(), new ItemStack(this.item, 1));
            world.addEntity(entity);
        }
    }

    static class SummonOperatorGoal extends Goal {
        private final MedicSingle medicSingle;

        public SummonOperatorGoal(MedicSingle medicSingle) {
            this.medicSingle = medicSingle;
        }

        @Override
        public boolean shouldExecute() {
            return false;
        }
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.75F;
    }
    public SoundEvent getDeathSound() {
        return SoundHandler.OPERATOR_DEAD;
    }
}
