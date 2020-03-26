package arknights.gui;

import arknights.Arknights;
import arknights.container.WorkshopContainer;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class WorkshopScreen extends ContainerScreen<WorkshopContainer> {

    private ResourceLocation GUI = new ResourceLocation(Arknights.MODID,"textures/gui/workshop.png");
    private final WorkshopContainer container;
    public WorkshopScreen(WorkshopContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        this.guiLeft = (this.width - 177) / 2;
        this.guiTop = (this.height - 187) / 2;
        this.container = p_i51105_1_;
    }
    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(); // This causes the non-GUI background of the screen to darken
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }


    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.func_227673_b_(1.0F,1.0F,1.0F,1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int left = this.guiLeft;
        int top = this.guiTop;
        this.blit(left, top, 0, 0,177,187);
        //this.blit(left + 4, top + 90, 4, 225, (100 - this.container.getTime()) * 167 / 100, 4);
    }
}
