package arknights.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Optional;

public interface IRecipeTypeArk<T extends IRecipe<?>> {
    IRecipeType<IWorkshopRecipe> WORKSHOP = register("workshop");

    static <T extends IRecipe<?>> IRecipeType<T> register(final String key) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(key), new IRecipeType<T>() {
            public String toString() {
                return key;
            }
        });
    }

    default <C extends ItemStackHandler> Optional<T> matches(IRecipeArk<C> recipe, World worldIn, C inv) {
        return recipe.matches(inv, worldIn) ? Optional.of((T)recipe) : Optional.empty();
    }
}
