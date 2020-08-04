package arknights.item;

import arknights.entity.operator.OperatorBase;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class OperatorItem extends Item {
    protected OperatorBase operator;
    public int id;
    public boolean isUsed;
    public int cd = 0;
    public int level = 1;
    public int eliteLevel = 0;
    public int trust  = 0;
    public int xp=0;

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
        return ActionResult.resultSuccess(itemstack);
    }

    public void newOperator(World world){

    }

    @Override
    public void inventoryTick(@Nonnull ItemStack stack, World world, @Nonnull Entity entity, int par4, boolean par5) {
        if(this.cd>0)this.cd--;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        //String playerName = entityLiving.getScoreboardName()
        if(!worldIn.isRemote()){
            Vector3d pos = entityLiving.getPositionVec();
            this.newOperator(entityLiving.world);
            if (stack.getTag() == null) {
                CompoundNBT compound = new CompoundNBT();
                compound.putInt("EliteLevel", this.eliteLevel);
                compound.putInt("Level", this.level);
                compound.putInt("Trust", this.trust);
                compound.putInt("Xp", this.xp);
                stack.setTag(compound);
            }
            this.operator.readAdditional(stack.getTag());
            this.operator.setOwnerId(entityLiving.getUniqueID());
            this.operator.setPosition(pos.x, pos.y, pos.z);
            this.operator.setPositionAndRotation(pos.x, pos.y, pos.z, entityLiving.rotationYaw, entityLiving.rotationPitch);
            worldIn.addEntity(this.operator);
            stack.shrink(1);
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
        if (stack.getTag() != null) {
            this.eliteLevel = stack.getTag().getInt("EliteLevel");
            this.level = stack.getTag().getInt("Level");
            this.trust = stack.getTag().getInt("Trust");
            this.xp = stack.getTag().getInt("Xp");
        }
        String level=new TranslationTextComponent("info.level").getString();
        String eliteLevel=new TranslationTextComponent("info.elitelevel").getString();
        String trust=new TranslationTextComponent("info.trust").getString();
        if (this.eliteLevel==0) {
            tooltip.add(new TranslationTextComponent(level + this.level + " " + trust + ":" + this.trust + "%"));
        } else {
            tooltip.add(new TranslationTextComponent(eliteLevel + this.eliteLevel + " " + level + this.level + " " + trust + ":" + this.trust + "%"));
        }
    }
}
