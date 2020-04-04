package arknights.item;

import arknights.Arknights;
import arknights.network.PacketHandler;
import arknights.network.packets.LeftClickPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class GunItem extends Item {

    public GunItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
        //MinecraftForge.EVENT_BUS.addListener(this::emptyLeftClick);
    }
/*
    public void emptyLeftClick(PlayerInteractEvent.LeftClickEmpty event){
        PacketHandler.HANDLER.sendToServer(new LeftClickPacket());
    }*/

    public void leftClick(LivingEntity livingEntity, World worldIn, ItemStack stack, boolean pressed){

    }
}
