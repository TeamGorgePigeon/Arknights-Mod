package arknights.item;

import arknights.registry.SoundHandler;
import arknights.utils.MyMathHelper;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraft.item.ItemTier.DIAMOND;

public class CrownslayerKnife extends SwordItem {

    public CrownslayerKnife( Properties props) {
        super(DIAMOND, 3, -2.4F, props);
    }

    private static final Rarity RARITY = Rarity.UNCOMMON;

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
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damageItem( 1, attacker, (user) -> user.sendBreakAnimation(attacker.getActiveHand()));
        return true;
    }

}

