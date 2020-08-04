package arknights.entity.enemy;

import arknights.entity.operator.OperatorBase;
import arknights.item.OperatorItem;
import arknights.registry.ItemHandler;
import arknights.registry.SoundHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.omg.CORBA.INTERNAL;

import javax.annotation.Nullable;
import java.util.Random;

public class EnemyBase extends MonsterEntity {
    private SummonEnemyGoal summonEnemy;
    //public static final DataParameter<Boolean> OPERATORATTACKING = EntityDataManager.createKey(ExusiaiEntity.class, DataSerializers.BOOLEAN);


    public EnemyBase(EntityType<? extends MonsterEntity> p_i48574_1_, World p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
        this.setCustomName(this.getDisplayName());
    }

    public void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1.0F));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0F, 120));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.summonEnemy = new EnemyBase.SummonEnemyGoal(this);
        this.goalSelector.addGoal(3, new SwimGoal(this));
        this.goalSelector.addGoal(3, this.summonEnemy);
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, OperatorBase.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static AttributeModifierMap.MutableAttribute func_234305_eI_() {
        return MonsterEntity.func_234295_eP_().func_233815_a_(Attributes.field_233818_a_, 16.0D).func_233815_a_(Attributes.field_233821_d_, (double)0.3F);
    }

    public static AttributeModifierMap.MutableAttribute func_234278_m_() {
        return MonsterEntity.func_234295_eP_().func_233815_a_(Attributes.field_233821_d_, 0.25D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.getAttribute(Attributes.field_233818_a_).setBaseValue(20.0D);
        this.setHealth(20.0F);
        this.getAttribute(Attributes.field_233821_d_).setBaseValue(0.3D);
        //this.dataManager.register(OPERATORATTACKING, false);
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

    public void dropItem(int level, int maxDropItem) {
        int random;
        int randomDropItem = new Random().nextInt(maxDropItem);
        for (int i=1;i<=randomDropItem;i++) {
            if (!world.isRemote()) {
                random = new Random().nextInt(200);
                if (random < 50) {
                    summonItem(ItemHandler._whiteMaterial.get(new Random().nextInt(ItemHandler._whiteMaterial.size())));
                }
                if (level >= 2) {
                    if (random >= 50 && random <= 90) {
                        summonItem(ItemHandler._greenMaterial.get(new Random().nextInt(ItemHandler._greenMaterial.size())));
                    }
                }
                if (level >= 3) {
                    if (random > 90 && random <= 98) {
                        summonItem(ItemHandler._blueMaterial.get(new Random().nextInt(ItemHandler._blueMaterial.size())));
                    }
                }
                if (level == 4) {
                    if (random > 98 && random <= 100) {
                        summonItem(ItemHandler._purpleMaterial.get(new Random().nextInt(ItemHandler._purpleMaterial.size())));
                    }
                }
            }
        }
    }

    public void summonItem(IItemProvider Item) {
        ItemEntity entity = new ItemEntity(world, this.getPositionVec().getX(), this.getPositionVec().getY(), this.getPositionVec().getZ(), new ItemStack(Item, 1));
        world.addEntity(entity);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.75F;
    }
        public SoundEvent getDeathSound() { return SoundHandler.ENEMY_DEAD; }
}

