package arknights.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BulletEntity extends AbstractArrowEntity {

    protected BulletEntity(EntityType<? extends AbstractArrowEntity> type, LivingEntity entity, World world) {
        super(type, entity, world);
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }
}
