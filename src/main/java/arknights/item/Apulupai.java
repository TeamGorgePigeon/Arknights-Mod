package arknights.item;

import arknights.entity.notLiving.BulletEntity;
import arknights.registry.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeItem;

import javax.annotation.Nonnull;
import java.util.Random;

public class Apulupai extends BaseItem implements IForgeItem{
    private int skill = 0;//0:none 1: 2: 3:
    private boolean isSkill = false;
    private int s1_rank, s2_rank, s3_rank;
    private int level;
    private SoundEvent event;
    private boolean pressed;
    private int tick = 0;
    private int tick2 = 0;
    public Apulupai(Properties p_i48487_1_) {
        super(p_i48487_1_);
        this.pressed = false;
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
        if(this.tick == 599 && !this.isSkill){
            switch (new Random().nextInt(4)){
                case 0:
                    world.playSound(null, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), SoundHandler.EXUSIAI_SKILL1, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    System.out.print(0);
                    break;
                case 1:
                    world.playSound(null, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), SoundHandler.EXUSIAI_SKILL2, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    System.out.print(1);
                    break;
                case 2:
                    world.playSound(null, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), SoundHandler.EXUSIAI_SKILL3, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    System.out.print(2);
                    break;
                case 3:
                    world.playSound(null, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), SoundHandler.EXUSIAI_SKILL4, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    System.out.print(3);
                    break;
                default:
                    world.playSound(null, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), SoundHandler.EXUSIAI_SKILLATTACK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    System.out.print(4);
                    break;
            }
        }
        if(this.tick == 600 && !this.isSkill){
            this.isSkill = true;
            this.tick = 0;
        }
        if(this.tick>=300 && this.isSkill){
            this.isSkill = false;
            this.tick = 0;
        }
        if(this.pressed && this.tick2%20 == 1){
            BulletEntity arrowEntity = new BulletEntity((LivingEntity) entity, world);
            arrowEntity.setNoGravity(true);
            arrowEntity.shoot(entity, entity.rotationPitch, entity.rotationYaw, 0.0F, 10.0F * 3.0F, 1.0F);
            world.addEntity(arrowEntity);
            if(!this.isSkill){
                world.playSound(null, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), SoundHandler.EXUSIAI_ATTACK, SoundCategory.NEUTRAL, 1.0F, 1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F);
            } else {
                for(int i = 0;i < 4;i++){
                    BulletEntity arrowEntity2 = new BulletEntity((LivingEntity) entity, world);
                    arrowEntity2.setNoGravity(true);
                    arrowEntity2.shoot(entity, entity.rotationPitch, entity.rotationYaw, 0.0F, 10.0F, 1.0F);
                    world.addEntity(arrowEntity2);
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
