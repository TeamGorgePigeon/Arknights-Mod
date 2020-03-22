package arknights.entity.living;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class OriginiumSlugEntity extends MonsterEntity {
   private OriginiumSlugEntity.SummonOriginiumSlugGoal summonOriginiumSlug;

   public OriginiumSlugEntity(EntityType<? extends OriginiumSlugEntity> typeIn, World worldIn) {
      super(typeIn, worldIn);
      //this.recalculateSize();
   }

   protected void registerGoals() {
      this.summonOriginiumSlug = new OriginiumSlugEntity.SummonOriginiumSlugGoal(this);
      this.goalSelector.addGoal(1, new SwimGoal(this));
      this.goalSelector.addGoal(3, this.summonOriginiumSlug);
      this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
   }

   /**
    * Returns the Y Offset of this entity.
    */

   protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
      return 0.1F;
   }

   protected void registerAttributes() {
      super.registerAttributes();
      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
      this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
      //this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
   }

   protected boolean func_225502_at_() {
      return false;
   }


   /**
    * Called when the entity is attacked.
    */
   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isInvulnerableTo(source)) {
         return false;
      } else {
         if ((source instanceof EntityDamageSource || source == DamageSource.MAGIC) && this.summonOriginiumSlug != null) {
            this.summonOriginiumSlug.notifyHurt();
         }

         return super.attackEntityFrom(source, amount);
      }
   }


   public static boolean func_223331_b(EntityType<OriginiumSlugEntity> p_223331_0_, IWorld p_223331_1_, SpawnReason p_223331_2_, BlockPos p_223331_3_, Random p_223331_4_) {
      if (func_223324_d(p_223331_0_, p_223331_1_, p_223331_2_, p_223331_3_, p_223331_4_)) {
         PlayerEntity playerentity = p_223331_1_.getClosestPlayer((double) p_223331_3_.getX() + 0.5D, (double) p_223331_3_.getY() + 0.5D, (double) p_223331_3_.getZ() + 0.5D, 5.0D, true);
         return playerentity == null;
      } else {
         return false;
      }
   }


   static class SummonOriginiumSlugGoal extends Goal {
      private final OriginiumSlugEntity originiumslug;
      private int lookForFriends;

      public SummonOriginiumSlugGoal(OriginiumSlugEntity originiumslugIn) {
         this.originiumslug = originiumslugIn;
      }

      public void notifyHurt() {
         if (this.lookForFriends == 0) {
            this.lookForFriends = 20;
         }

      }

      public boolean shouldExecute() {
         return this.lookForFriends > 0;
      }

      @Nullable
      public AxisAlignedBB getCollisionBox(Entity entityIn) {
         return new AxisAlignedBB(-4.0, -3.0, -5.0, 4.0, 3.0, 5.0);
      }
   }
}

