package arknights.entity.operator;

import arknights.entity.special.Hook;
import arknights.registry.EntityHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.Item;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class RopeEntity extends RangeOperator {
    public Hook hook = null;


    public RopeEntity(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
    }

    public RopeEntity(World p_i48574_2_, Item item) {
        super(EntityHandler.ROPE, p_i48574_2_, item);
    }

    @Override
    public void registerGoals() {
        super.registerGoals();
        //this.goalSelector.addGoal(10, new RangedAttackGoal(this, 1.25D, 20, 10.0F));
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float v) {
        if (this.hook != null) {
            if (!this.world.isRemote) {
                this.hook.handleHookRetraction();
            }
        } else {
            if (!this.world.isRemote) {
                this.hook = new Hook(this, this.world);
                this.world.addEntity(this.hook);
            }
        }
    }
}
