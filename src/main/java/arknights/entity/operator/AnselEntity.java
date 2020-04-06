package arknights.entity.operator;

import arknights.registry.EntityHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class AnselEntity extends MedicSingle {
    public AnselEntity(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
    }

    public AnselEntity(World p_i48574_2_, Item item) {
        super(EntityHandler.ANSEL, p_i48574_2_, item);
    }



    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

    }
}
