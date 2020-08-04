package arknights.item;

import arknights.registry.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class OrirockCube extends BlockItem {

    public static Item item1 = ItemHandler.ORIROCK;
    public static int num1 = 3;
    public int id = 2;
    public OrirockCube(Block p_i48527_1_, Properties p_i48527_2_) {
        super(p_i48527_1_, p_i48527_2_);
    }
    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.set(0, new TranslationTextComponent(tooltip.get(0).getString()).func_240699_a_(TextFormatting.YELLOW));
    }
}
