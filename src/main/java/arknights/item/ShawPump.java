package arknights.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
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
        return ActionResult.func_226249_b_(itemstack);
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }

    public ItemStack onPumpUsing(World world, ItemStack stack, PlayerEntity playerEntity) {
        if (isSkill && !world.isRemote()) {
            if (!playerEntity.isCreative()) {
                stack.damageItem(-stack.getMaxDamage() - 1,  playerEntity, (user) -> user.sendBreakAnimation(playerEntity.getActiveHand()));
                stack.damageItem(stack.getMaxDamage() - 10,  playerEntity, (user) -> user.sendBreakAnimation(playerEntity.getActiveHand()));
                stack.damageItem(-stack.getMaxDamage() + 20,  playerEntity, (user) -> user.sendBreakAnimation(playerEntity.getActiveHand()));
            }
            Vec3d vec3d = playerEntity.getPositionVec();
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
                    if (entity instanceof LivingEntity && !(entity instanceof PlayerEntity)) {
                        isAnyEntity=true;
                        ((LivingEntity) entity).knockBack(playerEntity, 5.0F, (double) MathHelper.sin(playerEntity.rotationYaw * ((float) Math.PI / 180F)), (double) (-MathHelper.cos(playerEntity.rotationYaw * ((float) Math.PI / 180F))));
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
}

