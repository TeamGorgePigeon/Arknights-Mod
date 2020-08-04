package arknights.entity.operator;

import arknights.Skill;
import arknights.entity.model.ExusiaiModel;
//import arknights.entity.notLiving.BulletEntity;
import arknights.registry.EntityHandler;
import arknights.registry.ItemHandler;
import arknights.registry.SoundHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class ExusiaiEntity extends RangeOperator{
    private static final Skill skill3 = new Skill(20,30,300, Skill.SkillRecoverType.AUTORECOVER);
    public ExusiaiModel model;

    public ExusiaiEntity(EntityType<? extends ExusiaiEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemHandler.APULUPAI));
        this.sp = skill3.spInit;
    }

    public ExusiaiEntity(World world, Item item){
        super(EntityHandler.EXUSIAI, world, item);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemHandler.APULUPAI));
        this.sp = skill3.spInit;
    }

    public boolean isAttacking(){
        return this.dataManager.get(OPERATORATTACKING);
    }

    /**
     * Returns the Y Offset of this entity.
     */

    public void attackEntityWithRangedAttack(LivingEntity target, float var2){
        /*
        if(!(target instanceof OperatorBase) && target != this.getOwner()) {
            double deltaX = target.getPosX() - this.getPosX();
            double deltaY;
            double deltaZ = target.getPosZ() - this.getPosZ();
            double distance = (double) MathHelper.sqrt(deltaX * deltaX + deltaZ * deltaZ);
            //float f = MathHelper.sqrt(deltaY * deltaY + distance * distance) * 0.2F;

            if (!this.isSkill && !world.isRemote()) {
                BulletEntity bulletEntity = new BulletEntity(this, this.world);

                deltaY = target.getPosYHeight(0.3333333333333333D) - bulletEntity.getPosY();
                bulletEntity.shoot(deltaX, deltaY, deltaZ, 1.6F, 1.0F);
                this.playSound(SoundHandler.EXUSIAI_ATTACK, 1.0F, 1.0F);
                this.world.addEntity(bulletEntity);
            } else if (this.isSkill) {
                for (int i = 0; i < 5; i++) {
                    BulletEntity bulletEntity = new BulletEntity(this, this.world);
                    deltaY = target.getPosYHeight(0.3333333333333333D) - bulletEntity.getPosY();
                    //float f = MathHelper.sqrt(deltaY * deltaY + distance * distance) * 0.2F;
                    bulletEntity.shoot(deltaX, deltaY, deltaZ, 1.6F, 1.0F);
                    this.world.addEntity(bulletEntity);
                }
                this.playSound(SoundHandler.EXUSIAI_SKILLATTACK, 1.0F, 1.0F);
            }
        }
*/
    }

    protected void yell(){
        switch (new Random().nextInt(4)) {
            case 0:
                this.playSound(SoundHandler.EXUSIAI_COMBATING1, 1.0F, 1.0F);
                break;
            case 1:
                this.playSound(SoundHandler.EXUSIAI_COMBATING2, 1.0F, 1.0F);
                break;
            case 2:
                this.playSound(SoundHandler.EXUSIAI_COMBATING3, 1.0F, 1.0F);
                break;
            case 3:
                this.playSound(SoundHandler.EXUSIAI_COMBATING4, 1.0F, 1.0F);
                break;
            default:
                break;
        }
    }

    public void livingTick() {
        if(!world.isRemote()) {
            this.dataManager.set(OPERATORATTACKING, (this.getAttackTarget() != null));

            this.tick++;
            if (this.tick % 19 == 2 && !this.isSkill) {
                this.sp++;
            }
            if (this.sp >= skill3.spCost) {
                this.sp = 0;
                this.isSkill = true;
                this.tick = 0;
                this.yell();
            }
            if (this.tick >= skill3.maxSkillTime && this.isSkill) {
                this.tick = 0;
                this.isSkill = false;
            }
        }
        super.livingTick();
    }

    @Override
    public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
        super.setAttackTarget(entitylivingbaseIn); //Forge: Moved down to allow event handlers to write data manager values.
    }

/*
    public static boolean func_223331_b(EntityType<ExusiaiEntity> p_223331_0_, IWorld p_223331_1_, SpawnReason p_223331_2_, BlockPos p_223331_3_, Random p_223331_4_) {
        if (func_223324_d(p_223331_0_, p_223331_1_, p_223331_2_, p_223331_3_, p_223331_4_)) {
            PlayerEntity playerentity = p_223331_1_.getClosestPlayer((double) p_223331_3_.getX() + 0.5D, (double) p_223331_3_.getY() + 0.5D, (double) p_223331_3_.getZ() + 0.5D, 5.0D, true);
            return playerentity == null;
        } else {
            return false;
        }
    }
*/
}

