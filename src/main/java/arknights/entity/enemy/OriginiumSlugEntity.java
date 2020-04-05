package arknights.entity.enemy;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class OriginiumSlugEntity extends MeleeEnemy {
   private OriginiumSlugEntity.SummonOriginiumSlugGoal summonOriginiumSlug;
   public EntitySize size;

   public OriginiumSlugEntity(EntityType<? extends OriginiumSlugEntity> typeIn, World worldIn) {
      super(typeIn, worldIn);
   }

   public static boolean spawnCondition (EntityType<? extends OriginiumSlugEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
      //IWorldLightListener skyLight = world.func_225524_e_().getLightEngine(LightType.SKY);
      int light = world.func_225524_e_().getLightEngine(LightType.SKY).getLightFor(pos);
      return light > 1;
      //return true;
   }

   /**
    * Returns the Y Offset of this entity.
    */

   public double getYOffset() {
      return 0.1D;
   }

   protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
      return 0.1F;
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
   }

   @Override
   public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
      super.setAttackTarget(entitylivingbaseIn); //Forge: Moved down to allow event handlers to write data manager values.
   }
}

