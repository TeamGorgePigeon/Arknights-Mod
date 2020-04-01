package arknights.entity.enemy;

import arknights.entity.ai.EnemyCrossbowAttackGoal;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ICrossbowUser;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public abstract class CrossBowEnemy extends EnemyBase implements ICrossbowUser, IRangedAttackMob {
   protected CrossBowEnemy(EntityType<? extends CrossBowEnemy> type, World worldIn) {
      super(type, worldIn);
   }

   public void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(2, new EnemyCrossbowAttackGoal<>(this, 1.0D, 8.0F));

   }

   public CreatureAttribute getCreatureAttribute() {
      return CreatureAttribute.UNDEFINED;
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
}