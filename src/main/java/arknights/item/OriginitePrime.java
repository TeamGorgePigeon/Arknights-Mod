package arknights.item;

import arknights.Arknights;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.*;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

import static net.minecraft.potion.Effects.*;

public class OriginitePrime extends BaseItem {
    List<Pair<EffectInstance, Float>> effects = new ArrayList<>();
    String playerName = null;

    public OriginitePrime(Properties p_i48476_1_) {
        super(p_i48476_1_);
    }

    @Override
    public void leftClick(LivingEntity livingEntity, World worldIn, ItemStack stack, boolean pressed){

        Vec3d pos = livingEntity.getPositionVec();
        if(!worldIn.isRemote){
            /*
            ArrowEntity arrowEntity = new ArrowEntity(worldIn, livingEntity);
            arrowEntity.setPosition(pos.x, pos.y, pos.z);
            arrowEntity.setNoGravity(true);
            worldIn.addEntity(arrowEntity);
            arrowEntity.shoot(livingEntity, livingEntity.rotationPitch, livingEntity.rotationYaw, 0.0F, 0.1F * 3.0F, 1.0F);
             */
            FireballEntity fireballEntity = new FireballEntity(worldIn, livingEntity, 0, 0, 0);
            fireballEntity.setPosition(pos.x, pos.y, pos.z);
            float f = -MathHelper.sin(livingEntity.rotationYaw * ((float)Math.PI / 180F)) * MathHelper.cos(livingEntity.rotationPitch * ((float)Math.PI / 180F));
            float f1 = -MathHelper.sin(livingEntity.rotationPitch * ((float)Math.PI / 180F));
            float f2 = MathHelper.cos(livingEntity.rotationYaw * ((float)Math.PI / 180F)) * MathHelper.cos(livingEntity.rotationPitch * ((float)Math.PI / 180F));
            Vec3d vec3d = new Vec3d(f, f1, f2);
            fireballEntity.setMotion(vec3d);
            worldIn.addEntity(fireballEntity);
        }
    }
    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.set(0, tooltip.get(0).applyTextStyle(TextFormatting.GOLD));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    //public static final Food PUREORIGINIUMS = (new Food.Builder()).hunger(10).saturation(0.3F).effect(new EffectInstance(Effects.SPEED, 400, 255), 1.0F).build();
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        this.effects.clear();
        this.playerName = playerIn.getScoreboardName();
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return ActionResult.func_226249_b_(itemstack);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        //String playerName = entityLiving.getScoreboardName()
        return onOriginniumsEaten(worldIn, stack, (PlayerEntity) entityLiving);
    }

    public ItemStack onOriginniumsEaten(World p_213357_1_, ItemStack p_213357_2_, PlayerEntity playerEntity) {
        playerEntity.getFoodStats().addStats(20,20);
        playerEntity.addStat(Stats.ITEM_USED.get(p_213357_2_.getItem()));
        p_213357_1_.playSound(null, playerEntity.func_226277_ct_(), playerEntity.func_226278_cu_(), playerEntity.func_226281_cx_(), playerEntity.getEatSound(p_213357_2_), SoundCategory.NEUTRAL, 1.0F, 1.0F + (p_213357_1_.rand.nextFloat() - p_213357_1_.rand.nextFloat()) * 0.4F);
        this.effectsList();
        playerEntity.clearActivePotions();
        applyFoodEffects(p_213357_2_, p_213357_1_, playerEntity);
        //playerEntity.setHealth(20.0F);
        //playerEntity.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 10));
        if ( !(playerEntity).abilities.isCreativeMode) {
            p_213357_2_.shrink(1);
        }
        return p_213357_2_;
    }

    private void applyFoodEffects(ItemStack p_213349_1_, World p_213349_2_, LivingEntity p_213349_3_) {
        for(Pair<EffectInstance, Float> pair : this.effects) {
            if (!p_213349_2_.isRemote && pair.getLeft() != null && p_213349_2_.rand.nextFloat() < pair.getRight()) {
                p_213349_3_.addPotionEffect(new EffectInstance(pair.getLeft()));
            }
        }
    }

    void effectsList(){
        if( /*this.playerName == "Dev" || */this.playerName.regionMatches(0, "Dr", 0, 2)){
            addEffectToList(SPEED, 600, 10);
            addEffectToList(HASTE, 600, 255);
            addEffectToList(STRENGTH, 200, 10);
            addEffectToList(JUMP_BOOST, 600, 2);
            addEffectToList(RESISTANCE, 600, 3);
            addEffectToList(FIRE_RESISTANCE, 600, 2);
            addEffectToList(WATER_BREATHING, 600, 1);
            addEffectToList(NIGHT_VISION, 600, 1);
            addEffectToList(DOLPHINS_GRACE, 600, 1);
        } else {
            int random1,random2,random3;
            random1 = (int)(1+Math.random()*2);
            random2 = (int)(100+Math.random()*1000);
            random3 = (int)(1+Math.random()*10);
            switch(random1){
                case 1:
                    addEffectToList(SPEED, random2, random3);
                    break;
                case 2:
                    addEffectToList(SLOWNESS, random2, random3);
                    break;default: break;
            }

            random1 = (int)(1+Math.random()*2);
            random2 = (int)(100+Math.random()*1000);
            random3 = (int)(1+Math.random()*10);
            switch(random1){
                case 1:
                    addEffectToList(HASTE, random2, random3);
                    break;
                case 2:
                    addEffectToList(MINING_FATIGUE, random2, random3);
                    break;default: break;
            }

            random1 = (int)(1+Math.random()*2);
            random2 = (int)(100+Math.random()*1000);
            random3 = (int)(1+Math.random()*2);
            switch(random1){
                case 1:
                    addEffectToList(STRENGTH, random2, random3);
                    break;
                case 2:
                    addEffectToList(WEAKNESS, random2, random3);
                    break;default: break;
            }

            random1 = (int)(1+Math.random()*2);
            random2 = (int)(100+Math.random()*1000);
            random3 = (int)(1+Math.random()*5);
            switch(random1){
                case 1:
                    addEffectToList(JUMP_BOOST, random2, random3);
                    break;
                    default: break;
            }

            random1 = (int)(1+Math.random()*2);
            random2 = (int)(100+Math.random()*1000);
            //random3 = (int)(1+Math.random()*10);
            switch(random1){
                case 1:
                    addEffectToList(NAUSEA, random2, 2);
                    break;
                default: break;
            }

            random1 = (int)(1+Math.random()*2);
            random2 = (int)(100+Math.random()*1000);
            random3 = (int)(1+Math.random()*3);
            switch(random1){
                case 1:
                    addEffectToList(RESISTANCE, random2, random3);
                    break;
                default: break;
            }

            random1 = (int)(1+Math.random()*2);
            random2 = (int)(100+Math.random()*1000);
            //random3 = (int)(1+Math.random()*10);
            switch(random1){
                case 1:
                    addEffectToList(FIRE_RESISTANCE, random2, 1);
                    break;
                default: break;
            }

            random1 = (int)(1+Math.random()*2);
            random2 = (int)(100+Math.random()*1000);
            //random3 = (int)(1+Math.random()*10);
            switch(random1){
                case 1:
                    addEffectToList(WATER_BREATHING, random2, 1);
                    break;
                default: break;
            }

            random1 = (int)(1+Math.random()*2);
            random2 = (int)(100+Math.random()*1000);
            //random3 = (int)(1+Math.random()*10);
            switch(random1){
                case 1:
                    addEffectToList(BLINDNESS, random2, 1);
                    break;
                case 2:
                    addEffectToList(NIGHT_VISION, random2, 1);
                    break;default: break;
            }

            random1 = (int)(1+Math.random()*2);
            random2 = (int)(100+Math.random()*1000);
            //random3 = (int)(1+Math.random()*10);
            switch(random1){
                case 1:
                    addEffectToList(SLOW_FALLING, random2, 1);
                    break;
                default: break;
            }

            random1 = (int)(1+Math.random()*2);
            random2 = (int)(100+Math.random()*1000);
            //random3 = (int)(1+Math.random()*10);
            switch(random1){
                case 1:
                    addEffectToList(DOLPHINS_GRACE, random2, 1);
                    break;
                default: break;
            }

            addEffectToList(WITHER, 800, 2);
        }

    }

    void addEffectToList(Effect effect, int time, int level){
        this.effects.add(Pair.of(new EffectInstance(effect, time, level), 1.0F));
    }
}
