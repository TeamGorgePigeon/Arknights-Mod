package arknights.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;

public interface IWorkshopRecipe extends IRecipe<IInventory> {
    default IRecipeType<?> getType() {
        return IRecipeTypeArk.WORKSHOP;
    }
}
