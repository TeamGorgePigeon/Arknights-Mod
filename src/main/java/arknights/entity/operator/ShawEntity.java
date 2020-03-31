package arknights.entity.operator;

import arknights.Skill;
import arknights.entity.model.CrownslayerModel;
import arknights.entity.model.ShawModel;
import arknights.registry.EntityHandler;
import arknights.registry.ItemHandler;
import arknights.registry.SoundHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class ShawEntity extends MeleeOperator {
    public ShawModel model;
    private static Skill skill1 = new Skill(0, 5, 0, Skill.SkillRecoverType.AUTORECOVER);

    public ShawEntity(EntityType<? extends ShawEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemHandler.SHAW_AXE));
    }

    public ShawEntity(World world, Item item){
        super(EntityHandler.SHAW, world, item);
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
        //this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }


    public void livingTick() {
        if(!world.isRemote()) {
            this.dataManager.set(OPERATORATTACKING, (this.getAttackTarget() != null));
            this.tick++;
            if (this.tick % 19 == 2 && !this.isSkill) {
                this.sp++;
            }
            if (this.sp >= skill1.spCost) {
                this.sp = 0;
                this.isSkill = true;
                this.tick = 0;
            }
        }
        super.livingTick();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        float f = 3.0f;//(float) this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
        float f1 = (float) this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).getValue();
        if (entityIn instanceof LivingEntity) {
            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((LivingEntity) entityIn).getCreatureAttribute());
            f1 += (float) EnchantmentHelper.getKnockbackModifier(this);
        }
        int i = EnchantmentHelper.getFireAspectModifier(this);
        if (i > 0) {
            entityIn.setFire(i * 4);
        }
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);
        if (flag && !this.isSkill) {
            if (f1 > 0.0F && entityIn instanceof LivingEntity) {
                ((LivingEntity) entityIn).knockBack(this, f1 * 0.5F, (double) MathHelper.sin(this.rotationYaw * ((float) Math.PI / 180F)), (double) (-MathHelper.cos(this.rotationYaw * ((float) Math.PI / 180F))));
                this.setMotion(this.getMotion().mul(0.6D, 1.0D, 0.6D));
            }
            if (entityIn instanceof PlayerEntity) {
                PlayerEntity playerentity = (PlayerEntity) entityIn;
                ItemStack itemstack = this.getHeldItemMainhand();
                ItemStack itemstack1 = playerentity.isHandActive() ? playerentity.getActiveItemStack() : ItemStack.EMPTY;
                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.canDisableShield(itemstack1, playerentity, this) && itemstack1.isShield(playerentity)) {
                    float f2 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;
                    if (this.rand.nextFloat() < f2) {
                        playerentity.getCooldownTracker().setCooldown(itemstack.getItem(), 100);
                        this.world.setEntityState(playerentity, (byte) 30);
                    }
                }
            }
            this.applyEnchantments(this, entityIn);
            this.setLastAttackedEntity(entityIn);
        } else {
            ((LivingEntity) entityIn).knockBack(this, 5.0F, (double) MathHelper.sin(this.rotationYaw * ((float) Math.PI / 180F)), (double) (-MathHelper.cos(this.rotationYaw * ((float) Math.PI / 180F))));
            this.setMotion(this.getMotion().mul(0.6D, 1.0D, 0.6D));
            this.isSkill = false;
            this.tick = 0;
        }
        return flag;
    }

    @Override
    public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
        super.setAttackTarget(entitylivingbaseIn); //Forge: Moved down to allow event handlers to write data manager values.
    }

}
