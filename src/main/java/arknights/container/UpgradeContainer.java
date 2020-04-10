package arknights.container;

import arknights.registry.ContainerHandler;
import arknights.tileentity.UpgradeEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.DistExecutor;


public class UpgradeContainer extends Container {
    public UpgradeContainer(int id, PlayerInventory playerInventory, UpgradeEntity entity) {
        super(ContainerHandler.UPGRADECONTAINER, id);
    }

    public static UpgradeContainer fromNetwork(int windowId, PlayerInventory playerInv, PacketBuffer buf) {
        return new UpgradeContainer(windowId, playerInv, (UpgradeEntity) DistExecutor.runForDist(() -> () -> {
            BlockPos pos = buf.readBlockPos();
            return Minecraft.getInstance().world.getTileEntity(pos);
        }, () -> () -> {
            throw new RuntimeException("Shouldn't be called on server!");
        }));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return false;
    }
}
