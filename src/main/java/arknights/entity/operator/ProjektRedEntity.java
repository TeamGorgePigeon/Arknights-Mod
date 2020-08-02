package arknights.entity.operator;

import arknights.Skill;
import arknights.entity.model.ProjektRedModel;
import arknights.registry.EntityHandler;
import arknights.registry.ItemHandler;
import arknights.registry.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
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
import java.util.Random;

public class ProjektRedEntity extends MeleeOperator {
    public ProjektRedModel model;
    private static Skill skill1 = new Skill(0, 5, 0, Skill.SkillRecoverType.AUTORECOVER);
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
        super.livingTick();
        if (onSpawn) {
            Vec3d vec3d = this.getPositionVec();
            List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(vec3d.x - 8, vec3d.y - 2, vec3d.z - 8, vec3d.x + 8, vec3d.y + 2, vec3d.z + 8));
            for (int k2 = 0; k2 < list.size(); ++k2) {
                Entity entity = list.get(k2);
                if (entity instanceof LivingEntity && !(entity instanceof AnimalEntity) && entity != this.getOwner() && !(entity instanceof OperatorBase) ) {
                    ((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 60, 255));
                    entity.attackEntityFrom(DamageSource.causeMobDamage(this), 10);
                }
            }
            if (!this.world.isRemote) {
                this.playSound(SoundHandler.SKILL_WOLFROAR, 1.0F, 1.0F);
            }
            onSpawn=false;
        }
        if(!world.isRemote()) {
            this.tick++;
            if (this.tick == 600 ) {
                this.tick=0;
                this.yell();
            }
        }
    }

    protected void yell(){
        switch (new Random().nextInt(4)) {
            case 0:
                this.playSound(SoundHandler.PROJEKTRED_COMBATING1, 1.0F, 1.0F);
                break;
            case 1:
                this.playSound(SoundHandler.PROJEKTRED_COMBATING2, 1.0F, 1.0F);
                break;
            case 2:
                this.playSound(SoundHandler.PROJEKTRED_COMBATING3, 1.0F, 1.0F);
                break;
            case 3:
                this.playSound(SoundHandler.PROJEKTRED_COMBATING4, 1.0F, 1.0F);
                break;
            default:
                break;
        }
    }

    @Override
    public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
        super.setAttackTarget(entitylivingbaseIn); //Forge: Moved down to allow event handlers to write data manager values.
    }

}
