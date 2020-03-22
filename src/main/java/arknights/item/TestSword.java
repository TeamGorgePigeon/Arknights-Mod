package arknights.item;

import arknights.utils.MyMathHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class TestSword extends BaseItem {

    private int tick = 0;//Skill CD
    private boolean rightPressed = false; //Maybe useful
    public TestSword(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 10;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return ActionResult.func_226249_b_(itemstack);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        //String playerName = entityLiving.getScoreboardName()
        return onSwordSwing(worldIn, stack, (PlayerEntity) entityLiving);
    }

    public ItemStack onSwordSwing(World p_213357_1_, ItemStack p_213357_2_, PlayerEntity playerEntity) {
        Vec3d vec3d = playerEntity.getPositionVec();
        List<Entity> list = p_213357_1_.getEntitiesWithinAABBExcludingEntity(playerEntity, new AxisAlignedBB(vec3d.x - 3, vec3d.y - 0.5, vec3d.z - 3, vec3d.x + 3, vec3d.y + 0.5, vec3d.z + 3));
        //Mian2 Chao3
        //double angle = playerEntity.rotationYaw;
        //System.out.print(angle + "\t");
        //float f = -MathHelper.sin(angle) * 3;
        //float f1 = -MathHelper.sin((rotationPitchIn + pitchOffset) * ((float)Math.PI / 180F));
        //float f2 = MathHelper.cos(angle) * 3;
        for(int k2 = 0; k2 < list.size(); ++k2){
            Entity entity = list.get(k2);
            Vec3d pos = entity.getPositionVec();
            double entityAngle = -Math.toDegrees(Math.atan2(pos.x - playerEntity.getPositionVec().x, pos.z - playerEntity.getPositionVec().z));
            //Following System.out.prints are for debug
            //System.out.print((pos.x - playerEntity.getPositionVec().x) + " " + (pos.z - playerEntity.getPositionVec().z) + " ");
            //System.out.print(entityAngle + " " + (playerEntity.rotationYaw % 360 > 180 ? playerEntity.rotationYaw % 360 - 360 : playerEntity.rotationYaw % 360) + " " + Math.abs(entityAngle - (playerEntity.rotationYaw % 360 > 180 ? playerEntity.rotationYaw % 360 - 360 : playerEntity.rotationYaw % 360)) + "\n");
            //System.out.print(MyMathHelper.in360(entityAngle) + " " + MyMathHelper.in360(playerEntity.rotationYaw) + " " + Math.abs(MyMathHelper.in360(entityAngle) - MyMathHelper.in360(playerEntity.rotationYaw)) + "\n");
            //if(entity.getDistance(playerEntity) <= 3 && Math.abs(entityAngle - (playerEntity.rotationYaw % 360 > 180 ? playerEntity.rotationYaw % 360 - 360 : playerEntity.rotationYaw % 360)) <= 90){
            if((Math.abs(MyMathHelper.in360(entityAngle) - MyMathHelper.in360(playerEntity.rotationYaw)) <= 90 || Math.abs(MyMathHelper.in360(entityAngle) - MyMathHelper.in360(playerEntity.rotationYaw)) >= 270) && entity.getDistance(playerEntity) <= 3){//Use MyMathHelper in utils to calculate the angle, and make sure only attack entities in front of you. Distance means the range, you can change it.
                entity.attackEntityFrom(DamageSource.MAGIC, 10);
            }
        }
        /*
        for(int k2 = 0; k2 < list.size(); ++k2){
            Entity entity = list.get(k2);
            entity.attackEntityFrom(DamageSource.MAGIC, 10);
            System.out.print(1);
            System.out.print(entity);
        }

         */
        p_213357_1_.playSound(null, playerEntity.func_226277_ct_(), playerEntity.func_226278_cu_(), playerEntity.func_226281_cx_(), SoundEvents.ENTITY_ARROW_HIT, SoundCategory.NEUTRAL, 1.0F, 1.0F + (p_213357_1_.rand.nextFloat() - p_213357_1_.rand.nextFloat()) * 0.4F);
        return p_213357_2_;
    }
}
