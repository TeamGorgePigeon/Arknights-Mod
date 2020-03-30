package arknights.entity;

import arknights.item.OperatorItem;
import arknights.registry.SoundHandler;
import net.minecraft.entity.*;
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

public class OperatorBase extends TameableEntity {
    private SummonOperatorGoal summonOperator;
    public Item item = Items.AIR;
    public static final DataParameter<Boolean> OPERATORATTACKING = EntityDataManager.createKey(ExusiaiEntity.class, DataSerializers.BOOLEAN);


    public OperatorBase(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
    }

    public OperatorBase(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_, Item item) {
        super(p_i48574_1_, p_i48574_2_);
        this.item = item;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        //this.goalSelector.addGoal(2, this.sitGoal);
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1.0F));
        this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1.0F, 120));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.summonOperator = new OperatorBase.SummonOperatorGoal(this);
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(3, this.summonOperator);
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, EnderDragonEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, WitherEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, EnemyBase.class, true));
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

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        //this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }

    public void onDeath(DamageSource cause) {
        if(this.item instanceof OperatorItem && !world.isRemote()){
            ItemEntity entity = new ItemEntity(world, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ(), new ItemStack(this.item, 1));
            world.addEntity(entity);
        }
    }

    static class SummonOperatorGoal extends Goal {
        private final OperatorBase operatorBase;

        public SummonOperatorGoal(OperatorBase operatorBaseIn) {
            this.operatorBase = operatorBaseIn;
        }

        @Override
        public boolean shouldExecute() {
            return false;
        }
    }

        public SoundEvent getDeathSound() {
            return SoundHandler.OPERATOR_DEAD;
        }
}

