package arknights.entity.enemy;

import arknights.entity.model.CrownslayerModel;
import arknights.registry.ItemHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class CrownslayerEntity extends MeleeEnemy {
    public CrownslayerModel model;


    public CrownslayerEntity(EntityType<? extends CrownslayerEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemHandler.CROWNSLAYER_KNIFE));
    }

    @Override
    public void registerGoals() {
        super.registerGoals();
    }

    /**
     * Returns the Y Offset of this entity.
     */
    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_234295_eP_()
                .func_233815_a_(Attributes.field_233818_a_, 25.0D)
                .func_233815_a_(Attributes.field_233821_d_, 0.3D)
                .func_233815_a_(Attributes.field_233823_f_, 5.0D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        //this.dataManager.register(OPERATORATTACKING, false);
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

    public static boolean spawnCondition (EntityType<? extends CrownslayerEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
        //IWorldLightListener skyLight = world.func_225524_e_().getLightEngine(LightType.SKY);
        int light = world.getLightManager().getLightEngine(LightType.SKY).getLightFor(pos);
        return light > 1;
        //return true;
    }
}

