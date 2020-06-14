package arknights.entity.enemy;

import arknights.entity.model.WModel;
import arknights.registry.ItemHandler;
import net.minecraft.entity.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class EnemyWEntity extends MeleeEnemy {
    public WModel model;


    public EnemyWEntity(EntityType<? extends EnemyWEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
        //this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemHandler.CROWNSLAYER_KNIFE));
    }

    @Override
    public void registerGoals() {
        super.registerGoals();
    }

    /**
     * Returns the Y Offset of this entity.
     */

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }

    public void onDeath(DamageSource source) {
        dropItem(3,5);
    }

    public void livingTick() {

        super.livingTick();
    }

    @Override
    public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
        super.setAttackTarget(entitylivingbaseIn); //Forge: Moved down to allow event handlers to write data manager values.
    }

    public static boolean spawnCondition (EntityType<? extends EnemyWEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
        //IWorldLightListener skyLight = world.func_225524_e_().getLightEngine(LightType.SKY);
        int light = world.func_225524_e_().getLightEngine(LightType.SKY).getLightFor(pos);
        return light > 1;
        //return true;
    }
}

