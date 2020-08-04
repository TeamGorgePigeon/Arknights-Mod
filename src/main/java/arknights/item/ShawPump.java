package arknights.item;

import arknights.registry.SoundHandler;
import arknights.utils.MyMathHelper;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ShawPump extends GunItem {

    private static final Rarity RARITY = Rarity.UNCOMMON;
    private int tick = 0;
    private int SkillCD = 300;//Skill CD
    private boolean lastisCreative = false;
    private boolean isFirstUsing = true;
    private boolean isSkill = false;

    public ShawPump(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return stack.isEnchanted() ? Rarity.RARE.compareTo(RARITY) > 0 ? Rarity.RARE : RARITY : RARITY;
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
        onPumpUsing(worldIn, itemstack,playerIn);
        return ActionResult.resultSuccess(itemstack);
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }

    public ItemStack onPumpUsing(World world, ItemStack stack, PlayerEntity playerEntity) {
        Vector3d vec3d = playerEntity.getPositionVec();
        if (isSkill && !world.isRemote()) {
            if (!playerEntity.isCreative()) {
                stack.damageItem(-stack.getMaxDamage() - 1,  playerEntity, (user) -> user.sendBreakAnimation(playerEntity.getActiveHand()));
                stack.damageItem(stack.getMaxDamage() - 10,  playerEntity, (user) -> user.sendBreakAnimation(playerEntity.getActiveHand()));
                stack.damageItem(-stack.getMaxDamage() + 20,  playerEntity, (user) -> user.sendBreakAnimation(playerEntity.getActiveHand()));
            }
            List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(playerEntity, new AxisAlignedBB(vec3d.x - 3, vec3d.y - 0.5, vec3d.z - 3, vec3d.x + 3, vec3d.y + 0.5, vec3d.z + 3));
            boolean isAnyEntity = false;
            //Mian2 Chao3
            //double angle = playerEntity.rotationYaw;
            //System.out.print(angle + "\t");
            //float f = -MathHelper.sin(angle) * 3;
            //float f1 = -MathHelper.sin((rotationPitchIn + pitchOffset) * ((float)Math.PI / 180F));
            //float f2 = MathHelper.cos(angle) * 3;
            if (!(list.size() ==0)) {
                for (int k2 = 0; k2 < list.size(); ++k2) {
                    Entity entity = list.get(k2);
                    //Following System.out.prints are for debug
                    //System.out.print((pos.x - playerEntity.getPositionVec().x) + " " + (pos.z - playerEntity.getPositionVec().z) + " ");
                    //System.out.print(entityAngle + " " + (playerEntity.rotationYaw % 360 > 180 ? playerEntity.rotationYaw % 360 - 360 : playerEntity.rotationYaw % 360) + " " + Math.abs(entityAngle - (playerEntity.rotationYaw % 360 > 180 ? playerEntity.rotationYaw % 360 - 360 : playerEntity.rotationYaw % 360)) + "\n");
                    //System.out.print(MyMathHelper.in360(entityAngle) + " " + MyMathHelper.in360(playerEntity.rotationYaw) + " " + Math.abs(MyMathHelper.in360(entityAngle) - MyMathHelper.in360(playerEntity.rotationYaw)) + "\n");
                    //if(entity.getDistance(playerEntity) <= 3 && Math.abs(entityAngle - (playerEntity.rotationYaw % 360 > 180 ? playerEntity.rotationYaw % 360 - 360 : playerEntity.rotationYaw % 360)) <= 90){
                    Vector3d pos = entity.getPositionVec();
                    double entityAngle = -Math.toDegrees(Math.atan2(pos.x - playerEntity.getPositionVec().x, pos.z - playerEntity.getPositionVec().z));
                    if ((Math.abs(MyMathHelper.in360(entityAngle) - MyMathHelper.in360(playerEntity.rotationYaw)) <= 90 || Math.abs(MyMathHelper.in360(entityAngle) - MyMathHelper.in360(playerEntity.rotationYaw)) >= 270) && entity.getDistance(playerEntity) <= 3) {
                        if (entity instanceof LivingEntity && !(entity instanceof PlayerEntity)) {
                            isAnyEntity = true;
                            ((LivingEntity) entity).func_233627_a_(5.0F, (double) MathHelper.sin(playerEntity.rotationYaw * ((float) Math.PI / 180F)), (double) (-MathHelper.cos(playerEntity.rotationYaw * ((float) Math.PI / 180F))));
                        }
                    }
                }
                if (isAnyEntity && !(playerEntity.isCreative())){
                    this.isSkill = false;
                    this.tick = 0;
                    stack.damageItem(-stack.getMaxDamage() - 1, playerEntity, (user) -> user.sendBreakAnimation(playerEntity.getActiveHand()));
                    stack.damageItem(stack.getMaxDamage() - 10, playerEntity, (user) -> user.sendBreakAnimation(playerEntity.getActiveHand()));
                }
            }
        }
        double faceAngle= MyMathHelper.in360(playerEntity.rotationYaw)*Math.PI/180;
        for (float R=0;R<=2;R=R+0.2F) {
            for (double angle = faceAngle + Math.PI*1/4; angle <= faceAngle + Math.PI * 3 / 4; angle = angle + Math.PI / 180) {
                double cos = Math.cos(angle);
                double sin = Math.sin(angle);
                world.addParticle(ParticleTypes.BUBBLE, vec3d.x + cos * R, vec3d.y + 0.8F, vec3d.z + sin * R, 0, 0.0D, 0);
                world.addParticle(ParticleTypes.BUBBLE, vec3d.x + cos * R, vec3d.y + 1F, vec3d.z + sin * R, 0, 0.0D, 0);
                world.addParticle(ParticleTypes.BUBBLE, vec3d.x + cos * R, vec3d.y + 1.2F, vec3d.z + sin * R, 0, 0.0D, 0);
                world.addParticle(new RedstoneParticleData(	0F,0F,200F,1F), vec3d.x + cos * R, vec3d.y + 0.8F, vec3d.z + sin * R, 0, 0, 0);
                world.addParticle(new RedstoneParticleData(	0F,0F,200F,1F), vec3d.x + cos * R, vec3d.y + 1F, vec3d.z + sin * R, 0, 0, 0);
                world.addParticle(new RedstoneParticleData(	0F,0F,200F,1F), vec3d.x + cos * R, vec3d.y + 1.2F, vec3d.z + sin * R, 0, 0, 0);
            }
        }
        world.playSound(null, playerEntity.getPosX(), playerEntity.getPosY(), playerEntity.getPosZ(), SoundHandler.SKILL_HYDRAULIC, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        return stack;
        }

    @Override
    public void inventoryTick(@Nonnull ItemStack stack, World world, @Nonnull Entity entity, int par4, boolean par5) {
        if (((PlayerEntity) entity).isCreative()){
            this.lastisCreative=true;
            this.isSkill = true;
        } else {
            if (this.lastisCreative | this.isFirstUsing) {
                this.tick = 0;
                this.isSkill=false;
                this.isFirstUsing=false;
                this.lastisCreative=false;
            }
            if (this.tick == SkillCD-1 && !this.isSkill) {
                this.isSkill = true;
                stack.damageItem(-stack.getMaxDamage() - 1, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity) entity).getActiveHand()));
                stack.damageItem(stack.getMaxDamage() - 10, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity) entity).getActiveHand()));
                stack.damageItem(-stack.getMaxDamage() + 20, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity) entity).getActiveHand()));
            }

            if (this.tick < SkillCD-1 && this.tick % 19 == 1 &&!this.isSkill ) {
                stack.damageItem(-stack.getMaxDamage() - 1, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity) entity).getActiveHand()));
                stack.damageItem(stack.getMaxDamage() - 10, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity) entity).getActiveHand()));
                stack.damageItem(-(this.tick * stack.getMaxDamage()) / SkillCD, (PlayerEntity) entity, (user) -> user.sendBreakAnimation(((PlayerEntity) entity).getActiveHand()));
            }

            this.tick++;

            if (((PlayerEntity) entity).getHeldItemMainhand().getItem() != this) {
                this.tick = 0;
            }
        }
    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltip, flags);
        tooltip.add(new TranslationTextComponent("info.skill"));
    }
}

