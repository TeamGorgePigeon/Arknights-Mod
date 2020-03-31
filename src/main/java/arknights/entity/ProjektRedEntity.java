package arknights.entity;

import arknights.entity.model.CrownslayerModel;
import arknights.registry.EntityHandler;
import arknights.registry.ItemHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ProjektRedEntity extends OperatorBase {
    public CrownslayerModel model;


    public ProjektRedEntity(EntityType<? extends ProjektRedEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemHandler.PROJEKTRED_KNIFE));
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
    }


    public void livingTick() {

        super.livingTick();
    }

    @Override
    public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
        super.setAttackTarget(entitylivingbaseIn); //Forge: Moved down to allow event handlers to write data manager values.
    }

}
