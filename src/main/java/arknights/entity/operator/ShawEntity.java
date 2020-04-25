package arknights.entity.operator;

import arknights.Skill;
import arknights.entity.model.ShawModel;
import arknights.registry.EntityHandler;
import arknights.registry.ItemHandler;
import arknights.registry.SoundHandler;
import arknights.utils.MyMathHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
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
                this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemHandler.SHAW_PUMP));
                this.sp = 0;
                this.isSkill = true;
                this.tick = 0;
                this.yell();
            }
        }
        super.livingTick();
    }

    protected void yell(){
        switch (new Random().nextInt(4)) {
            case 0:
                this.playSound(SoundHandler.SHAW_COMBATING1, 1.0F, 1.0F);
                break;
            case 1:
                this.playSound(SoundHandler.SHAW_COMBATING2, 1.0F, 1.0F);
                break;
            case 2:
                this.playSound(SoundHandler.SHAW_COMBATING3, 1.0F, 1.0F);
                break;
            case 3:
                this.playSound(SoundHandler.SHAW_COMBATING4, 1.0F, 1.0F);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        Vec3d vec3d = this.getPositionVec();
        boolean flag =true;
        if (!(entityIn instanceof OperatorBase)) {
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
            flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);
            if (flag && !this.isSkill) {
                if (f1 > 0.0F && entityIn instanceof LivingEntity) {
                    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(vec3d.x - 3, vec3d.y - 0.5, vec3d.z - 3, vec3d.x + 3, vec3d.y + 0.5, vec3d.z + 3));
                    for (int k2 = 0; k2 < list.size(); ++k2) {
                        Entity entity = list.get(k2);
                        Vec3d pos = entity.getPositionVec();
                        double entityAngle = -Math.toDegrees(Math.atan2(pos.x - this.getPositionVec().x, pos.z - this.getPositionVec().z));
                        if ((Math.abs(MyMathHelper.in360(entityAngle) - MyMathHelper.in360(this.rotationYaw)) <= 90 || Math.abs(MyMathHelper.in360(entityAngle) - MyMathHelper.in360(this.rotationYaw)) >= 270) && entity.getDistance(this) <= 3) {
                            if (entity instanceof LivingEntity && !(entity instanceof OperatorBase)) {
                                ((LivingEntity) entity).knockBack(this, 5.0F, (double) MathHelper.sin(this.rotationYaw * ((float) Math.PI / 180F)), (double) (-MathHelper.cos(this.rotationYaw * ((float) Math.PI / 180F))));
                            }
                        }
                    }
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
                List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(vec3d.x - 3, vec3d.y - 0.5, vec3d.z - 3, vec3d.x + 3, vec3d.y + 0.5, vec3d.z + 3));
                for (int k2 = 0; k2 < list.size(); ++k2) {
                    Entity entity = list.get(k2);
                    Vec3d pos = entity.getPositionVec();
                    double entityAngle = -Math.toDegrees(Math.atan2(pos.x - this.getPositionVec().x, pos.z - this.getPositionVec().z));
                    if ((Math.abs(MyMathHelper.in360(entityAngle) - MyMathHelper.in360(this.rotationYaw)) <= 90 || Math.abs(MyMathHelper.in360(entityAngle) - MyMathHelper.in360(this.rotationYaw)) >= 270) && entity.getDistance(this) <= 3) {
                        if (entity instanceof LivingEntity && !(entity instanceof OperatorBase)) {
                            ((LivingEntity) entity).knockBack(this, 5.0F, (double) MathHelper.sin(this.rotationYaw * ((float) Math.PI / 180F)), (double) (-MathHelper.cos(this.rotationYaw * ((float) Math.PI / 180F))));
                        }
                    }
                }
                this.setMotion(this.getMotion().mul(0.6D, 1.0D, 0.6D));
                this.isSkill = false;
                this.tick = 0;
            }
            this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemHandler.SHAW_AXE));
        }
        if (this.isSkill && this.world.isRemote) {
            double faceAngle = MyMathHelper.in360(this.rotationYaw) * Math.PI / 180;
            for (float R = 0; R <= 2; R = R + 0.2F) {
                for (double angle = faceAngle + Math.PI * 1 / 4; angle <= faceAngle + Math.PI * 3 / 4; angle = angle + Math.PI / 180) {
                    double cos = Math.cos(angle);
                    double sin = Math.sin(angle);
                    this.world.addParticle(ParticleTypes.CRIT,vec3d.x + cos * R, vec3d.y + 0.8F, vec3d.z + sin * R, 0, 0.0D, 0);
                    this.world.addParticle(ParticleTypes.BUBBLE, vec3d.x + cos * R, vec3d.y + 1F, vec3d.z + sin * R, 0, 0.0D, 0);
                    this.world.addParticle(ParticleTypes.BUBBLE, vec3d.x + cos * R, vec3d.y + 1.2F, vec3d.z + sin * R, 0, 0.0D, 0);
                    this.world.addParticle(new RedstoneParticleData(0F, 0F, 200F, 1F), vec3d.x + cos * R, vec3d.y + 0.8F, vec3d.z + sin * R, 0, 0, 0);
                    this.world.addParticle(new RedstoneParticleData(0F, 0F, 200F, 1F), vec3d.x + cos * R, vec3d.y + 1F, vec3d.z + sin * R, 0, 0, 0);
                    this.world.addParticle(new RedstoneParticleData(0F, 0F, 200F, 1F), vec3d.x + cos * R, vec3d.y + 1.2F, vec3d.z + sin * R, 0, 0, 0);
                }
            }
        }
        return flag;
    }

    @Override
    public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
        super.setAttackTarget(entitylivingbaseIn); //Forge: Moved down to allow event handlers to write data manager values.
    }

}
