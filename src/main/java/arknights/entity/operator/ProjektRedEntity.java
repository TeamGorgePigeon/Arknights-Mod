package arknights.entity.operator;

import arknights.entity.model.CrownslayerModel;
import arknights.registry.EntityHandler;
import arknights.registry.ItemHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ProjektRedEntity extends MeleeOperator {
    public CrownslayerModel model;

    public ProjektRedEntity(EntityType<? extends ProjektRedEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemHandler.PROJEKTRED_KNIFE));
    }

    public ProjektRedEntity(World worldIn, Item item) {
        super(EntityHandler.PROJEKTRED, worldIn, item);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemHandler.PROJEKTRED_KNIFE));
    }

    @Override
    public void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
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
