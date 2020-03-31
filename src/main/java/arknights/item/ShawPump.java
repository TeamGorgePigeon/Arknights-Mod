package arknights.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ShawPump extends BaseItem {

    private static final Rarity RARITY = Rarity.UNCOMMON;

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
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }

    public void inventoryTick(@Nonnull ItemStack stack, World world, @Nonnull Entity entity, int par4, boolean par5) {
        //todo
    }
}

