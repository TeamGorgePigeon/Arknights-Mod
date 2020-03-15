package arknights.registry;

import arknights.recipe.WorkshopRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class RecipeHandler  {
    public static final IRecipeSerializer<WorkshopRecipe> WORKSHOP_RECIPE = IRecipeSerializer.register("arknights:workshop", new WorkshopRecipe.Serializer());

    public static void register(RegistryEvent.Register<IRecipeSerializer<?>> evt) {
        IForgeRegistry r = evt.getRegistry();
        r.register(WORKSHOP_RECIPE);
    }
}
