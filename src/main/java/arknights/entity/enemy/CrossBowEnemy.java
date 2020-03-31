package arknights.entity.enemy;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public abstract class CrossBowEnemy extends AbstractRaiderEntity {
   protected CrossBowEnemy(EntityType<? extends CrossBowEnemy> type, World worldIn) {
      super(type, worldIn);
   }

   protected void registerGoals() {
      super.registerGoals();
   }

   public CreatureAttribute getCreatureAttribute() {
      return CreatureAttribute.ILLAGER;
   }

   @OnlyIn(Dist.CLIENT)
   public CrossBowEnemy.ArmPose getArmPose() {
      return CrossBowEnemy.ArmPose.CROSSED;
   }

   @OnlyIn(Dist.CLIENT)
   public static enum ArmPose {
      CROSSED,
      ATTACKING,
      SPELLCASTING,
      BOW_AND_ARROW,
      CROSSBOW_HOLD,
      CROSSBOW_CHARGE,
      CELEBRATING,
      NEUTRAL;
   }

   public class RaidOpenDoorGoal extends OpenDoorGoal {
      public RaidOpenDoorGoal(AbstractRaiderEntity p_i51284_2_) {
         super(p_i51284_2_, false);
      }

      /**
       * Returns whether the EntityAIBase should begin execution.
       */
      public boolean shouldExecute() {
         return super.shouldExecute() && CrossBowEnemy.this.isRaidActive();
      }
   }
}