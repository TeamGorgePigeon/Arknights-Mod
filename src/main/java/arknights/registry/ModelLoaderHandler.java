package arknights.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;

public class ModelLoaderHandler extends ModelLoaderRegistry {

    public static void myRegister()
    {
        registerLoader(new ResourceLocation("arknights:models"), OBJLoader.INSTANCE);
    }
}
