package arknights.container;

import arknights.network.PacketHandler;
import arknights.network.packets.UpdateWindowPacket;
import arknights.registry.ContainerHandler;
import arknights.tileentity.TradingHomeEntity;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class TradingHomeContainer extends Container {
    public final TradingHomeEntity tileEntity;
    public final PlayerInventory playerInventory;
    public int time;
    private IItemHandler inv;

    public TradingHomeContainer(int windowId, PlayerInventory playerInventoryIn, TradingHomeEntity tileEntity) {
        super(ContainerHandler.TRADINGHOMECONTAINER, windowId);
        this.tileEntity = tileEntity;
        this.inv = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);
        this.playerInventory = playerInventoryIn;

        this.addSlot(new SlotItemHandler(inv, 100, 139, 42){
            @Override
        public boolean isItemValid(ItemStack stack) {
            return false;
        }});
        this.addSlot(new SlotItemHandler(inv, 101, 15, 42));
        this.addSlot(new SlotItemHandler(inv, 102, 54, 42));
        this.addSlot(new SlotItemHandler(inv, 103, 93, 42));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 107 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventoryIn, k, 8 + k * 18, 165));
        }
    }


    public static TradingHomeContainer fromNetwork(int windowId, PlayerInventory playerInv, PacketBuffer buf) {
        return new TradingHomeContainer(windowId, playerInv, (TradingHomeEntity) DistExecutor.runForDist(() -> () -> {
            BlockPos pos = buf.readBlockPos();
            return Minecraft.getInstance().world.getTileEntity(pos);
        }, () -> () -> {
            throw new RuntimeException("Shouldn't be called on server!");
        }));
    }

    @Override
    public ItemStack transferStackInSlot(final PlayerEntity player, final int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(index);
        if ((slot != null) && slot.getHasStack()) {
            final ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            final int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.size();
            if (index < containerSlots) {
                if (!mergeItemStack(itemstack1, containerSlots, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!mergeItemStack(itemstack1, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, itemstack1);
        }
        return itemstack;
    }

    @Override
    public void addListener(@Nonnull IContainerListener par1IContainerListener) {
        super.addListener(par1IContainerListener);
        par1IContainerListener.sendWindowProperty(this, 0, tileEntity.time);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        PacketHandler.HANDLER.sendTo(new UpdateWindowPacket(windowId, tileEntity.time), ((ServerPlayerEntity)playerInventory.player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);

    }

    @Override
    public void updateProgressBar(int id, int data) {
        if(id == 0){
            tileEntity.time = data;
        }
    }

    public int getTime(){
        return this.time;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerEntity) { // was originally "PlayerEntity playerEntity"
        return true;
    }
}
