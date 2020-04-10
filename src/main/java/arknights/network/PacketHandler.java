package arknights.network;

import arknights.Arknights;
import arknights.network.packets.DisasterPacket;
import arknights.network.packets.LeftClickPacket;
import arknights.network.packets.UpdateDecrCount;
import arknights.network.packets.UpdateWindowPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public final class PacketHandler {
    private static final String PROTOCOL_VERSION = Integer.toString(1);



    public static SimpleChannel HANDLER = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Arknights.MODID,"main")
            ,() -> PROTOCOL_VERSION
            , PROTOCOL_VERSION::equals
            , PROTOCOL_VERSION::equals);

    private static int id = 0;
    public static void register(){
        HANDLER.registerMessage(id++, LeftClickPacket.class, LeftClickPacket::encode, LeftClickPacket::decode, LeftClickPacket.Handler::handle);
        HANDLER.registerMessage(id++, UpdateWindowPacket.class, UpdateWindowPacket::encode, UpdateWindowPacket::decode, UpdateWindowPacket.Handler::handle);
        HANDLER.registerMessage(id++, DisasterPacket.class, DisasterPacket::encode, DisasterPacket::decode, DisasterPacket.Handler::handle);
        HANDLER.registerMessage(id++, UpdateDecrCount.class, UpdateDecrCount::encode, UpdateDecrCount::decode, UpdateDecrCount.Handler::handle);
        //HANDLER.registerMessage(id++, EntityStatuePacket.class, EntityStatuePacket::encode, EntityStatuePacket::decode, EntityStatuePacket.Handler::handle);
    }
}
