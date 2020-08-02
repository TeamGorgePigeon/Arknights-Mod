package arknights.entity.enemy;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public abstract class RangeEnemy extends EnemyBase implements IRangedAttackMob {
    public RangeEnemy(EntityType<? extends MonsterEntity> p_i48574_1_, World p_i48574_2_){
        super(p_i48574_1_, p_i48574_2_);
    }

    @Override
    public void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 20, 10.0F));
    }

    @Override
    public abstract void attackEntityWithRangedAttack(LivingEntity livingEntity, float v);
}
