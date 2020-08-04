package arknights.network.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class DisasterPacket {
    private static boolean isDisaster;
    public DisasterPacket(boolean isDisaster){
        super();
        this.isDisaster = isDisaster;
    }
    public static void encode(DisasterPacket msg, PacketBuffer buf){
    }

    public static DisasterPacket decode(PacketBuffer buf){
        return new DisasterPacket(isDisaster);
    }

    public static class Handler {
        public static void handle(DisasterPacket msg, Supplier<NetworkEvent.Context> ctx){
            ctx.get().enqueueWork(() -> {
                //Minecraft.getInstance().world.getDimension().getLightmapColors(5.0F, 10.0F, 0.0F, 0.0F, new Vector3f(0, 0, 0));
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
