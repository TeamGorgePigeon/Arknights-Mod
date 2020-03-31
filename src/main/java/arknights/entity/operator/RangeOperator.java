package arknights.entity.operator;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public abstract class RangeOperator extends OperatorBase implements IRangedAttackMob {
    public RangeOperator(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_){
        super(p_i48574_1_, p_i48574_2_);
    }

    public RangeOperator(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_, Item item) {
        super(p_i48574_1_, p_i48574_2_, item);
    }

    @Override
    public void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 20, 10.0F));
    }

    @Override
    public abstract void attackEntityWithRangedAttack(LivingEntity livingEntity, float v);
}
