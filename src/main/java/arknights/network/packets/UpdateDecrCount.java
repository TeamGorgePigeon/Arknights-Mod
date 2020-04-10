package arknights.network.packets;

import arknights.container.TradingHomeContainer;
import arknights.container.WorkshopContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateDecrCount {

    //private final int windowId;
    private final int count;

    public UpdateDecrCount(int count) {
        //this.windowId = windowId;
        this.count = count;
    }

    public static void encode(UpdateDecrCount msg, PacketBuffer buf) {
        //buf.writeInt(msg.windowId);
        buf.writeInt(msg.count);
    }

    public static UpdateDecrCount decode(PacketBuffer buf) {
        return new UpdateDecrCount(buf.readInt());
    }

    public static class Handler {

        public static void handle(final UpdateDecrCount msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> handleClient(msg));
            ctx.get().setPacketHandled(true);
        }

        //Needed to make the server be able to resolve registering the packet
        //@OnlyIn(Dist.CLIENT)
        private static void handleClient(final UpdateDecrCount msg) {
            PlayerEntity player = Minecraft.getInstance().player;
            //if (player.openContainer.windowId == msg.windowId) {
            //player.openContainer.updateProgressBar(msg.windowId, msg.count);
            ((WorkshopContainer)player.openContainer).resultSlot.decrCount = msg.count;
            //}
        }
    }
}
