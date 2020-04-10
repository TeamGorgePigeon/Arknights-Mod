package arknights.registry;

import arknights.container.TradingHomeContainer;
import arknights.container.UpgradeContainer;
import arknights.container.WorkshopContainer;
import arknights.gui.TradingHomeScreen;
import arknights.gui.UpgradeScreen;
import arknights.gui.WorkshopScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.registries.IForgeRegistry;

public class ContainerHandler {
    public static ContainerType<TradingHomeContainer> TRADINGHOMECONTAINER = IForgeContainerType.create(TradingHomeContainer::fromNetwork);
    public static ContainerType<WorkshopContainer> WORKSHOPCONTAINER = IForgeContainerType.create(WorkshopContainer::fromNetwork);
    public static ContainerType<UpgradeContainer> UPGRADECONTAINER = IForgeContainerType.create(UpgradeContainer::fromNetwork);


    public static void register(RegistryEvent.Register<ContainerType<?>> evt){
        IForgeRegistry r = evt.getRegistry();
        r.register(TRADINGHOMECONTAINER.setRegistryName(BlockHandler.TRADINGHOME.getRegistryName()));
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            //ScreenManager.registerFactory(TRADINGHOMECONTAINER, TradingHomeScreen::new);
            ScreenManager.registerFactory(TRADINGHOMECONTAINER, TradingHomeScreen::new);
            ScreenManager.registerFactory(WORKSHOPCONTAINER, WorkshopScreen::new);
            ScreenManager.registerFactory(UPGRADECONTAINER, UpgradeScreen::new);
        });

        r.register(WORKSHOPCONTAINER.setRegistryName(BlockHandler.WORKSHOP.getRegistryName()));
        r.register(UPGRADECONTAINER.setRegistryName(BlockHandler.UPGRADE.getRegistryName()));
    }

}
