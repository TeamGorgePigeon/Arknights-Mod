package arknights.entity.operator;

import arknights.entity.model.ProjektRedModel;
import arknights.registry.EntityHandler;
import arknights.registry.ItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ProjektRedEntity extends MeleeOperator {
    public ProjektRedModel model;
    public boolean onSpawn=true;


    public ProjektRedEntity(EntityType<? extends ProjektRedEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemHandler.PROJEKTRED_KNIFE));
    }

    @Override
    public void registerGoals() {
        super.registerGoals();
    }

    public ProjektRedEntity(World world, Item item){
        super(EntityHandler.PROJEKTRED, world, item);
    }
    /**
     * Returns the Y Offset of this entity.
     */

    protected void registerAttributes() {
        super.registerAttributes();
    }


    public void livingTick() {
        if (onSpawn) {
            Vec3d vec3d = this.getPositionVec();
            List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(vec3d.x - 8, vec3d.y - 2, vec3d.z - 8, vec3d.x + 8, vec3d.y + 2, vec3d.z + 8));
            for (int k2 = 0; k2 < list.size(); ++k2) {
                Entity entity = list.get(k2);
                if (entity instanceof LivingEntity && entity != this.getOwner() && !(entity instanceof OperatorBase)) {
                    ((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 60, 255));
                    entity.attackEntityFrom(DamageSource.causeMobDamage(this), 10);
                }
            }
            onSpawn=false;
        }
        super.livingTick();
    }

    @Override
    public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
        super.setAttackTarget(entitylivingbaseIn); //Forge: Moved down to allow event handlers to write data manager values.
    }

}
