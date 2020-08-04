package arknights.gui;

import arknights.container.UpgradeContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class UpgradeScreen extends ContainerScreen<UpgradeContainer> {
    public UpgradeScreen(UpgradeContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
    }

    @Override
    protected void func_230450_a_(MatrixStack matrixStack, float v, int i, int i1) {

    }

}
