package arknights.item;

import arknights.entity.notLiving.BulletEntity;
import arknights.registry.SoundHandler;
import com.sun.jna.platform.win32.WinNT;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeItem;

import javax.annotation.Nonnull;
import java.util.Random;

public class Apulupai extends GunItem implements IForgeItem{
    private int skill = 0;//0:none 1: 2: 3:
    private boolean isSkill = false;
    private int s1_rank, s2_rank, s3_rank;
    private int level;
    private SoundEvent event;
    private int SkillCD =600;
    private boolean pressed;
    private int tick = 0;
    private int tick2 = 0;
    private int delay = 18 ;
    private boolean isFirstUsing = true;

    public Apulupai(Properties p_i48487_1_) {
        super(p_i48487_1_);
        this.pressed = false;
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    public boolean onEntitySwing(ItemStack stack, LivingEntity entity)
    {
        return true;
    }

    @Override
    public void leftClick(LivingEntity livingEntity, World worldIn, ItemStack stack, boolean pressed){
        this.pressed = pressed;
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack stack, World world, @Nonnull Entity entity, int par4, boolean par5) {
        //System.out.print(this.tick + "\t" + this.pressed + "\n");
        if(this.tick == SkillCD-1 && !this.isSkill){
            this.isSkill = true;
            this.tick = 0;
            if (!((PlayerEntity) entity).isCreative()) {
            stack.damageItem( -stack.getMaxDamage() - 1, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity)entity).getActiveHand()));
            stack.damageItem( stack.getMaxDamage()-10, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity)entity).getActiveHand()));
            stack.damageItem( -stack.getMaxDamage()+20, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity)entity).getActiveHand()));
            }
            /*
            switch (new Random().nextInt(4)){
                case 0:
                    world.playSound(null, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), SoundHandler.EXUSIAI_SKILL1, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    //System.out.print(0);
                    break;
                case 1:
                    world.playSound(null, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), SoundHandler.EXUSIAI_SKILL2, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    //System.out.print(1);
                    break;
                case 2:
                    world.playSound(null, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), SoundHandler.EXUSIAI_SKILL3, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    //System.out.print(2);
                    break;
                case 3:
                    world.playSound(null, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), SoundHandler.EXUSIAI_SKILL4, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    //System.out.print(3);
                    break;
                default:
                    world.playSound(null, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), SoundHandler.EXUSIAI_SKILLATTACK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    //System.out.print(4);
                    break;
            }
             */
        }

        if(this.tick>=SkillCD/2 && this.isSkill){
            this.isSkill = false;
            this.tick = 0;
            if (!((PlayerEntity) entity).isCreative()) {
                stack.damageItem(-stack.getMaxDamage() - 1, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity) entity).getActiveHand()));
                stack.damageItem(stack.getMaxDamage() - 10, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity) entity).getActiveHand()));
            }
        }
        if(this.pressed && this.tick2%this.delay == 1){
            if (isFirstUsing && !((PlayerEntity) entity).isCreative()) {
                stack.damageItem( -stack.getMaxDamage() - 1, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity)entity).getActiveHand()));
                stack.damageItem(stack.getMaxDamage() - 10, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity) entity).getActiveHand()));
                isFirstUsing=false;
            }
            BulletEntity arrowEntity = new BulletEntity((LivingEntity) entity, world);
            arrowEntity.setNoGravity(true);
            arrowEntity.shoot(entity, entity.rotationPitch, entity.rotationYaw, 0.0F, 1.6F, 1.0F);
            world.addEntity(arrowEntity);
            Vec3d vec3d= entity.getPositionVec();
            world.addParticle(ParticleTypes.SMOKE,vec3d.x,vec3d.y+1,vec3d.z,0.25,0.25,0.25);
            if(!this.isSkill){
                if (!((PlayerEntity) entity).isCreative()) {
                    stack.damageItem(-stack.getMaxDamage() - 1, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity) entity).getActiveHand()));
                    stack.damageItem(stack.getMaxDamage() - 10, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity) entity).getActiveHand()));
                    stack.damageItem(-(this.tick * stack.getMaxDamage()) / SkillCD, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity) entity).getActiveHand()));
                }
                world.playSound(null, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), SoundHandler.EXUSIAI_ATTACK, SoundCategory.NEUTRAL, 1.0F, 1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F);
            } else {
                for(int i = 0;i < 4;i++){
                    BulletEntity arrowEntity2 = new BulletEntity((LivingEntity) entity, world);
                    arrowEntity2.setNoGravity(true);
                    arrowEntity2.shoot(entity, entity.rotationPitch, entity.rotationYaw, 0.0F, 2.0F, 1.0F);
                    world.addEntity(arrowEntity2);
                    world.addParticle(ParticleTypes.SMOKE,vec3d.x,vec3d.y+1,vec3d.z,0.25,0.25,0.25);
                }
                world.playSound(null, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), SoundHandler.EXUSIAI_SKILLATTACK, SoundCategory.NEUTRAL, 1.0F, 1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F);
                this.tick2 += 4;
            }

        }
        if(this.pressed){
            this.tick2++;
        }
        this.tick++;
        if(((PlayerEntity)entity).getHeldItemMainhand().getItem() != this){
            this.tick = 0;
            this.tick2 = 0;
            this.pressed = false;
            this.isSkill = false;
        }
    }
}
