package arknights.entity.enemy;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class MeleeEnemy extends EnemyBase {
    public MeleeEnemy(EntityType<? extends MonsterEntity> p_i48574_1_, World p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
    }

    @Override
    public void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
    }

}
