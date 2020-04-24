package arknights.recipe;

import arknights.registry.RecipeHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class WorkshopRecipe implements IWorkshopRecipe {
    private final ResourceLocation id;
    private final String group;
    private final ItemStack recipeOutput;
    private final NonNullList<Ingredient> recipeItems;
    private final boolean isSimple;
    private final IInventory inventory = new Inventory(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY);
    public final NonNullList<ItemStack> itemStacks;
    public  NonNullList<Item> items = NonNullList.withSize(3, Items.AIR);
    protected final Item itemRecipe[] = new Item[]{Items.AIR,Items.AIR,Items.AIR};

    public int CItemToInt(Item item) {
        int itemToInt = -1;
        int count = 0;
        /*
        if (item == this.itemRecipe[0]) {
            itemToInt=0;
        } else if (item == this.itemRecipe[1]) {
            itemToInt=1;
        } else if (item == this.itemRecipe[2]) {
            itemToInt=2;
        } else {itemToInt=-1;}*/
        for (ItemStack itemStack : this.itemStacks){
            if(item == itemStack.getItem()){
                itemToInt = count;
            }
            count++;
        }
        return itemToInt;
    }


    public WorkshopRecipe(ResourceLocation idIn, String groupIn, ItemStack recipeOutputIn, NonNullList<Ingredient> recipeItemsIn, NonNullList<ItemStack> itemStacks) {
        this.id = idIn;
        this.group = groupIn;
        this.recipeOutput = recipeOutputIn;
        this.recipeItems = recipeItemsIn;
        this.isSimple = recipeItemsIn.stream().allMatch(Ingredient::isSimple);
        this.itemStacks = itemStacks;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public IRecipeSerializer<?> getSerializer() {
        return RecipeHandler.WORKSHOP_RECIPE;
    }

    /**
     * Recipes with equal group are combined into one button in the recipe book
     */
    public String getGroup() {
        return this.group;
    }

    /**
     * Get the result of this recipe, usually for display purposes (e.g. recipe book). If your recipe has more than one
     * possible result (e.g. it's dynamic and depends on its inputs), then return an empty stack.
     */
    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.recipeItems;
    }

    public boolean matches(IInventory inv, World worldIn) {
        RecipeItemHelper recipeitemhelper = new RecipeItemHelper();
        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
        int i = 0;
        int getItemRecipe;
        boolean isMatchItemCountMain = false;
        boolean[] isMatchItemCount = new boolean[3];

        for(int j = 0; j < inv.getSizeInventory(); ++j) {
            ItemStack itemstack = inv.getStackInSlot(j);
            if (!itemstack.isEmpty()) {
                ++i;
                if (isSimple)
                    recipeitemhelper.func_221264_a(itemstack, 1);
                else inputs.add(itemstack);
                if (j <= this.itemStacks.size()-1) {
                    getItemRecipe=CItemToInt(inv.getStackInSlot(j).getItem());
                    if (getItemRecipe!=-1 & getItemRecipe < this.itemStacks.size()) {
                    isMatchItemCount[j] =  itemstack.getCount() >= this.itemStacks.get(getItemRecipe).getCount();
                        inv.getStackInSlot(j);
                        this.items.set(j, itemstack.getItem());
                    }
                }
            } else {isMatchItemCount[j]=true;}
        }
        if (isMatchItemCount[0] & isMatchItemCount[1] & isMatchItemCount[2]) {isMatchItemCountMain=true;}
        return i == this.recipeItems.size() && (isSimple ? recipeitemhelper.canCraft(this, (IntList)null) : net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs,  this.recipeItems) != null) && isMatchItemCountMain;
    }

    public ItemStack getCraftingResult(IInventory inv) {
        for(int i = 0; i < 3; i++){
            this.inventory.setInventorySlotContents(i, inv.getStackInSlot(i));
        }
        return this.recipeOutput.copy();
    }

    /**
     * Used to determine if this recipe can fit in a grid of the given width/height
     */
    public boolean canFit(int width, int height) {
        return width * height >= this.recipeItems.size();
    }

    public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<WorkshopRecipe> {
        private static final ResourceLocation NAME = new ResourceLocation("arknights", "workshop");
        public WorkshopRecipe read(ResourceLocation recipeId, JsonObject json) {
            String s = JSONUtils.getString(json, "group", "");
            NonNullList<Ingredient> nonnulllist = readIngredients(JSONUtils.getJsonArray(json, "ingredients"));
            NonNullList<ItemStack> nonullistStacks = readItemStacks(json);//NonNullList.withSize(3, 0);//readInteger(JSONUtils.getJsonArray(json, "itemStacks"));
            ItemStack itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
            return new WorkshopRecipe(recipeId, s, itemstack, nonnulllist, nonullistStacks);
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray p_199568_0_) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for(int i = 0; i < p_199568_0_.size(); ++i) {
                Ingredient ingredient = Ingredient.deserialize(p_199568_0_.get(i));
                if (!ingredient.hasNoMatchingItems()) {
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }
        /*
        private static NonNullList<Integer> readInteger(JsonObject json){
            NonNullList<Integer> integers = NonNullList.create();
            JsonArray array1 = JSONUtils.getJsonArray(json, "itemStacks");

            for(int i = 0; i < array1.size(); ++i) {
                //Ingredient ingredient = Ingredient.deserialize(array.get(i));
                //ResourceLocation resourcelocation1 = new ResourceLocation(JSONUtils.getString(array2.get(i), "item"));
                //Item item = Registry.ITEM.getValue(resourcelocation1).orElseThrow(() -> {
                //    return new JsonSyntaxException("Unknown item '" + resourcelocation1 + "'");
                //});
                int count = array1.get(i).getAsInt();
                //System.out.print(count);
                //if (!ingredient.hasNoMatchingItems()) {
                integers.add(count);
                //}
            }

            return integers;
        }

         */

        private static NonNullList<ItemStack> readItemStacks(JsonObject json){
            NonNullList<ItemStack> nonnulllist = NonNullList.create();
            //NonNullList<Integer> integers = NonNullList.create();
            JsonArray array1 = JSONUtils.getJsonArray(json, "counts");
            JsonArray array2 = JSONUtils.getJsonArray(json, "items");

            for(int i = 0; i < array1.size(); ++i) {
                //Ingredient ingredient = Ingredient.deserialize(array.get(i));
                ResourceLocation resourcelocation1 = new ResourceLocation(array2.get(i).getAsString());

                Item item = Registry.ITEM.getValue(resourcelocation1).orElseThrow(() -> {
                    return new JsonSyntaxException("Unknown item '" + resourcelocation1 + "'");
                });
                int count = array1.get(i).getAsInt();

                //this.itemRecipe[i]=item;

                nonnulllist.add(new ItemStack(item, count));
                //System.out.print(count);
                //if (!ingredient.hasNoMatchingItems()) {
                //integers.add(count);
                //}
            }

            return nonnulllist;
        }

        public WorkshopRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            String s = buffer.readString(32767);
            int i = buffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
            NonNullList<ItemStack> nonnulllistCount = NonNullList.withSize(i, ItemStack.EMPTY);

            for(int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, Ingredient.read(buffer));
                nonnulllistCount.set(j, buffer.readItemStack());
            }

            ItemStack itemstack = buffer.readItemStack();
            return new WorkshopRecipe(recipeId, s, itemstack, nonnulllist, nonnulllistCount);
        }

        public void write(PacketBuffer buffer, WorkshopRecipe recipe) {
            buffer.writeString(recipe.group);
            buffer.writeVarInt(recipe.recipeItems.size());

            for(Ingredient ingredient : recipe.recipeItems) {
                ingredient.write(buffer);
            }
            for(ItemStack stack : recipe.itemStacks){
                buffer.writeItemStack(stack);
            }

            buffer.writeItemStack(recipe.recipeOutput);
        }
    }
}
