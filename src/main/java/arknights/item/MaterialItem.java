package arknights.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MaterialItem extends Item {
    public int id;
    public TextFormatting color;
    //public Item item1, item2, item3;
    //public int id1, id2, id3;
    public List<Integer> materialId = new ArrayList<>();
    public List<Integer> materialNum = new ArrayList<>();
    public int num1, num2, num3;
    public MaterialItem(Properties p_i48487_1_, int id, TextFormatting color,int id1, int num1, int id2, int num2, int id3, int num3) {
        super(p_i48487_1_);
        this.id = id;
        this.color = color;
        this.materialId.add(id1);
        this.materialId.add(id2);
        this.materialId.add(id3);
        this.materialNum.add(num1);
        this.materialNum.add(num2);
        this.materialNum.add(num3);
        //this.id1 = id1;
        //this.id2 = id2;
        //this.id3 = id3;
        //this.num1 = num1;
        //this.num2 = num2;
        //this.num3 = num3;
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.set(0, new TranslationTextComponent(tooltip.get(0).getString()).func_240699_a_(this.color));
    }
}
