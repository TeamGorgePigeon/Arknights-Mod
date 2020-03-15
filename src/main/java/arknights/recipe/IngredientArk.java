package arknights.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nullable;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class IngredientArk {
    //public int count;
    public ItemStack item;

    public IngredientArk (@Nullable JsonElement json) {
        //this.count = JSONUtils.getInt(json, "count");
        this.item = new ItemStack(Registry.ITEM.getValue(new ResourceLocation(JSONUtils.getString(json, "item"))).get(), JSONUtils.getInt(json, "count"));
    }

    public IngredientArk(ItemStack stack){
        this.item = stack;
    }
}
