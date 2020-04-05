package arknights.entity.enemy;

import arknights.entity.operator.OperatorBase;
import arknights.item.OperatorItem;
import arknights.registry.ItemHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.World;
import org.omg.CORBA.INTERNAL;

import javax.annotation.Nullable;
import java.util.Random;

public class EnemyBase extends MonsterEntity {
    private SummonEnemyGoal summonEnemy;
    //public static final DataParameter<Boolean> OPERATORATTACKING = EntityDataManager.createKey(ExusiaiEntity.class, DataSerializers.BOOLEAN);


    public EnemyBase(EntityType<? extends MonsterEntity> p_i48574_1_, World p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
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

    @Override
    protected void registerData() {
        super.registerData();
        //this.dataManager.register(OPERATORATTACKING, false);
    }

    @Nullable
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

    public void DropItem(int level,int maxDropItem) {
        int random;
        int randomDropItem = new Random().nextInt(maxDropItem);
        for (int i=1;i<=randomDropItem;i++) {
            if (!world.isRemote()) {
                random = new Random().nextInt(150);
                if (random < 50) {
                    switch (new Random().nextInt(5)) {
                        case 0:
                            SummonItem(ItemHandler.ORIRONSHARD);
                            break;
                        case 1:
                            SummonItem(ItemHandler.SUGARSUBSTITUTE);
                            break;
                        case 2:
                            SummonItem(ItemHandler.ESTER);
                            break;
                        case 3:
                            SummonItem(ItemHandler.ORIROCK);
                            break;
                        case 4:
                            SummonItem(ItemHandler.DIKETON);
                            break;
                        case 5:
                            SummonItem(ItemHandler.DAMAGEDDEVICE);
                            break;
                        default:
                            break;
                    }
                }
                if (level >= 2) {
                    if (random >= 50 && random <= 90) {
                        switch (new Random().nextInt(5)) {
                            case 0:
                                SummonItem(ItemHandler.ORIRON);
                                break;
                            case 1:
                                SummonItem(ItemHandler.SUGAR);
                                break;
                            case 2:
                                SummonItem(ItemHandler.POLYESTER);
                                break;
                            case 3:
                                SummonItem(ItemHandler.ORIROCKCUBE);
                                break;
                            case 4:
                                SummonItem(ItemHandler.POLYKETON);
                                break;
                            case 5:
                                SummonItem(ItemHandler.DEVICE);
                                break;
                            default:
                                break;
                        }
                    }
                }
                if (level >= 3) {
                    if (random > 90 && random <= 98) {
                        switch (new Random().nextInt(9)) {
                            case 0:
                                SummonItem(ItemHandler.ORIRONCLUSTER);
                                break;
                            case 1:
                                SummonItem(ItemHandler.SUGARPACK);
                                break;
                            case 2:
                                SummonItem(ItemHandler.POLYESTERPACK);
                                break;
                            case 3:
                                SummonItem(ItemHandler.ORIROCKCLUSTER);
                                break;
                            case 4:
                                SummonItem(ItemHandler.AKETON);
                                break;
                            case 5:
                                SummonItem(ItemHandler.INTEGRATEDDEVICE);
                                break;
                            case 6:
                                SummonItem(ItemHandler.RMA7012);
                                break;
                            case 7:
                                SummonItem(ItemHandler.GRINDSTONE);
                                break;
                            case 8:
                                SummonItem(ItemHandler.MANGANESEORE);
                                break;
                            case 9:
                                SummonItem(ItemHandler.LOXICKOHL);
                                break;
                            default:
                                break;
                        }
                    }
                }
                if (level == 4) {
                    if (random > 98 && random <= 100) {
                        switch (new Random().nextInt(9)) {
                            case 0:
                                SummonItem(ItemHandler.ORIRONBLOCK);
                                break;
                            case 1:
                                SummonItem(ItemHandler.SUGARLUMP);
                                break;
                            case 2:
                                SummonItem(ItemHandler.POLYESTERLUMP);
                                break;
                            case 3:
                                SummonItem(ItemHandler.ORIROCKCONCENTRATION);
                                break;
                            case 4:
                                SummonItem(ItemHandler.KETONCOLLOID);
                                break;
                            case 5:
                                SummonItem(ItemHandler.OPTMIZEDDEVICE);
                                break;
                            case 6:
                                SummonItem(ItemHandler.RMA7024);
                                break;
                            case 7:
                                SummonItem(ItemHandler.GRINDSTONEPENTAHYDRATE);
                                break;
                            case 8:
                                SummonItem(ItemHandler.MANGANESETRIHYDRATE);
                                break;
                            case 9:
                                SummonItem(ItemHandler.WHITEHORSEKOHL);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

    public void SummonItem(IItemProvider Item) {
        ItemEntity entity = new ItemEntity(world, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ(), new ItemStack(Item, 1));
        world.addEntity(entity);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.75F;
    }
        //public SoundEvent getDeathSound() {
            //return SoundHandler.OPERATOR_DEAD;
        //}
}

