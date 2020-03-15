package arknights.recipe;

import arknights.container.WorkshopContainer;
import arknights.tileentity.WorkshopEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.List;

public class WorkshopInv extends ItemStackHandler implements IRecipeHelperPopulator {

    private final NonNullList<ItemStack> stackList;
    //private final WorkshopContainer container;
    //private final WorkshopEntity workshopEntity;

    public WorkshopInv() {
        this.stackList = NonNullList.withSize(3, ItemStack.EMPTY);
        //this.container = eventHandlerIn;
        //this.container = container;
        //this.workshopEntity = this.container.tileEntity;
    }



    public int getSizeInventory() {
        return this.stackList.size();
    }

    public boolean isEmpty() {
        for(ItemStack itemstack : this.stackList) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public ItemStack getStackInSlot(int index) {
        return index >= this.getSizeInventory() ? ItemStack.EMPTY : this.stackList.get(index);
    }

    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.stackList, index);
    }

    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack = ItemStackHelper.getAndSplit(this.stackList, index, count);
        if (!itemstack.isEmpty()) {
            //this.field_70465_c.onCraftMatrixChanged(this);
            //this.container.crafting();
        }

        return itemstack;
    }

    public void setInventorySlotContents(int index, ItemStack stack) {
        this.stackList.set(index, stack);
        //this.field_70465_c.onCraftMatrixChanged(this);
        //this.container.crafting();
    }

    public void markDirty() {
    }

    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    public void clear() {
        this.stackList.clear();
    }

    public void fillStackedContents(RecipeItemHelper helper) {
        for(ItemStack itemstack : this.stackList) {
            helper.accountPlainStack(itemstack);
        }

    }
}
