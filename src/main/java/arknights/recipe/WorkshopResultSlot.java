package arknights.recipe;

import arknights.Arknights;
import arknights.registry.RecipeHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Optional;

public class WorkshopResultSlot extends Slot {

    private final IItemHandler field_75239_a;
    private final IInventory transferInv = new Inventory(3);
    private final PlayerEntity player;
    private int amountCrafted;
    public WorkshopRecipe workshopRecipe;
    public NonNullList<ItemStack> itemStacks = NonNullList.create();

    public WorkshopResultSlot(PlayerEntity player, IItemHandler craftingInventory, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
        this.player = player;
        this.field_75239_a = craftingInventory;
        for(int i = 0; i < 3; i++){
            this.transferInv.setInventorySlotContents(i, this.field_75239_a.getStackInSlot(i));
        }
    }
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    public ItemStack decrStackSize(int amount) {
        if (this.getHasStack()) {
            this.amountCrafted += Math.min(amount, this.getStack().getCount());
        }

        return super.decrStackSize(amount);
    }

    protected void onCrafting(ItemStack stack, int amount) {
        this.amountCrafted += amount;
        this.onCrafting(stack);
    }

    protected void onSwapCraft(int p_190900_1_) {
        this.amountCrafted += p_190900_1_;
    }

    protected void onCrafting(ItemStack stack) {
        if (this.amountCrafted > 0) {
            stack.onCrafting(this.player.world, this.player, this.amountCrafted);
            //net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerCraftingEvent(this.player, stack, this.field_75239_a);
        }

        if (this.inventory instanceof IRecipeHolder) {
            ((IRecipeHolder)this.inventory).onCrafting(this.player);
        }

        this.amountCrafted = 0;
    }

    public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
        this.onCrafting(stack);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(thePlayer);
        NonNullList<ItemStack> nonnulllist = thePlayer.world.getRecipeManager().getRecipeNonNull(IRecipeTypeArk.WORKSHOP, this.transferInv, thePlayer.world);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);
        //Optional<IWorkshopRecipe> optional = thePlayer.world.getServer().getRecipeManager().getRecipe(IRecipeTypeArk.WORKSHOP, this.transferInv, thePlayer.world);
        //WorkshopRecipe workshopRecipe = (arknights.recipe.WorkshopRecipe) optional.get();
        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = this.field_75239_a.getStackInSlot(i);
            ItemStack itemstack1 = nonnulllist.get(i);
            int count = this.field_75239_a.getStackInSlot(i).getCount();
            for(int j = 0; j < this.itemStacks.size(); ++j) {
                if (!itemstack.isEmpty()) {
                    if (this.itemStacks.get(j) != null) {
                        if (this.field_75239_a.getStackInSlot(i).getItem() == this.itemStacks.get(j).getItem()) {
                            //this.field_75239_a.extractItem(i, this.itemStacks.get(j).getCount(), false);
                            this.field_75239_a.getStackInSlot(i).shrink(this.itemStacks.get(j).getCount());
                            count = this.field_75239_a.getStackInSlot(i).getCount();
                            itemstack = this.field_75239_a.getStackInSlot(i);
                        }
                    }
                    //System.out.print(this.itemStacks.get(j).getItem() + "\t" + this.field_75239_a.getStackInSlot(i).getItem() + "\n");
                }
            }
                if (!itemstack1.isEmpty()) {
                    if (itemstack.isEmpty()) {
                        this.field_75239_a.insertItem(i, itemstack1, false);
                    } else if (ItemStack.areItemsEqual(itemstack, itemstack1) && ItemStack.areItemStackTagsEqual(itemstack, itemstack1)) {
                        itemstack1.grow(itemstack.getCount());
                        this.field_75239_a.insertItem(i, itemstack1, false);
                    } else if (!this.player.inventory.addItemStackToInventory(itemstack1)) {
                        this.player.dropItem(itemstack1, false);
                    }
                }
            this.field_75239_a.getStackInSlot(i).setCount(count);
        }
        return stack;
    }
}
