package arknights.registry;

import arknights.tileentity.TradingHomeEntity;
import arknights.tileentity.WorkshopEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static arknights.Arknights.MODID;

public class TileEntityHandler {
    public static final TileEntityType<?> TRADINGHOMEENTITY= TileEntityType.Builder.create(TradingHomeEntity::new, BlockHandler.TRADINGHOME).build(null).setRegistryName(MODID, "trading_home");
    public static final TileEntityType<?> WORKSHOPENTITY = TileEntityType.Builder.create(WorkshopEntity::new, BlockHandler.WORKSHOP).build(null).setRegistryName(MODID, "workshop");

    @SubscribeEvent
    public static void register(RegistryEvent.Register<TileEntityType<?>> evt){
        IForgeRegistry<TileEntityType<?>> r = evt.getRegistry();
        r.register(TRADINGHOMEENTITY);
        r.register(WORKSHOPENTITY);
    }
}
