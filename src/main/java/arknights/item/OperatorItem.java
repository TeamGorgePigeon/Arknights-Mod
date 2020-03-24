package arknights.item;

import arknights.entity.OperatorBase;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class OperatorItem extends Item {
    protected OperatorBase operator;
    private int id;
    private boolean isUsed = false;
    public OperatorItem(Properties p_i48487_1_) {
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

    public void newOperator(World world){

    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        //String playerName = entityLiving.getScoreboardName()
        System.out.print(this.isUsed);
        if(!worldIn.isRemote()){
            Vec3d pos = entityLiving.getPositionVec();
            if(this.isUsed){
                worldIn.getEntityByID(this.id).remove();
                this.isUsed = false;
            } else {
                this.newOperator(entityLiving.world);
                this.isUsed = true;
                this.id = this.operator.getEntityId();
                this.operator.setOwnerId(entityLiving.getUniqueID());
                this.operator.setPosition(pos.x, pos.y, pos.z);
                this.operator.setPositionAndRotation(pos.x, pos.y, pos.z, entityLiving.rotationYaw, entityLiving.rotationPitch);
                worldIn.addEntity(this.operator);
            }
        }
        return stack;
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltip, flags);
        tooltip.add(new TranslationTextComponent("右键放置干员，再次右键撤退"));
    }
}
