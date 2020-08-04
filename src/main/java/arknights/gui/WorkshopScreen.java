package arknights.gui;

import arknights.Arknights;
import arknights.container.WorkshopContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class WorkshopScreen extends ContainerScreen<WorkshopContainer> {

    private ResourceLocation GUI = new ResourceLocation(Arknights.MODID,"textures/gui/workshop.png");
    private final WorkshopContainer container;
    public WorkshopScreen(WorkshopContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        this.guiLeft = (this.field_230708_k_ - 177) / 2;
        this.guiTop = (this.field_230709_l_ - 187) / 2;
        this.container = p_i51105_1_;
    }


    public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.func_230446_a_(matrixStack); // This causes the non-GUI background of the screen to darken
        super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
        this.func_230459_a_(matrixStack, mouseX, mouseY);
    }


    @Override
    public void func_230450_a_(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        GlStateManager.clearColor(1.0F,1.0F,1.0F,1.0F);
        this.getMinecraft().getTextureManager().bindTexture(GUI);
        int left = this.guiLeft;
        int top = this.guiTop;
        //this.blit(left, top, 0, 0,177,187);
        func_238464_a_(matrixStack, left, top, this.func_230927_p_(),0, 0, this.xSize, this.ySize,177,187);
        //this.blit(left + 4, top + 90, 4, 225, (100 - this.container.getTime()) * 167 / 100, 4);
    }
}
