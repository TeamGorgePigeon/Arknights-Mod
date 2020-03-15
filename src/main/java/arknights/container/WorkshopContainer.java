package arknights.container;

import arknights.network.PacketHandler;
import arknights.network.packets.UpdateWindowPacket;
import arknights.recipe.*;
import arknights.registry.ContainerHandler;
import arknights.tileentity.TradingHomeEntity;
import arknights.tileentity.WorkshopEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.Optional;

public class WorkshopContainer extends Container {
    public final WorkshopEntity tileEntity;
    public final PlayerInventory playerInventory;
    private IItemHandler inv;// = new WorkshopInv();
    private IInventory craftingInv = new Inventory(3);
    private WorkshopResultInv inv2 = new WorkshopResultInv();
    public WorkshopResultSlot resultSlot;

    public WorkshopContainer(int p_i50090_1_, PlayerInventory playerInventoryIn, WorkshopEntity workshopEntity) {
        super(ContainerHandler.WORKSHOPCONTAINER, p_i50090_1_);
        this.tileEntity = workshopEntity;
        this.inv =  tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);
        this.playerInventory = playerInventoryIn;

        this.resultSlot = new WorkshopResultSlot(this.playerInventory.player, this.inv, this.inv2, 0, 139, 42);
        this.addSlot(resultSlot);
        this.addSlot(new SlotItemHandler(this.inv, 0, 15, 42));
        this.addSlot(new SlotItemHandler(this.inv, 1, 54, 42));
        this.addSlot(new SlotItemHandler(this.inv, 2, 93, 42));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 107 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventoryIn, k, 8 + k * 18, 165));
        }
    }


    private void craft(World p_217066_1_) {
        for(int i = 0; i < 3; i++){
            this.craftingInv.setInventorySlotContents(i, this.inv.getStackInSlot(i));
        }
        if (!p_217066_1_.isRemote) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)this.playerInventory.player;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<IWorkshopRecipe> optional = p_217066_1_.getServer().getRecipeManager().getRecipe(IRecipeTypeArk.WORKSHOP, this.craftingInv, p_217066_1_);
            if (optional.isPresent()) {
                WorkshopRecipe icraftingrecipe = (WorkshopRecipe) optional.get();
                this.resultSlot.itemStacks = icraftingrecipe.counts;
                if (this.inv2.canUseRecipe(p_217066_1_, serverplayerentity, icraftingrecipe)) {
                    itemstack = icraftingrecipe.getCraftingResult(this.craftingInv);
                }
            }
            this.inv2.setInventorySlotContents(0, itemstack);
            //serverplayerentity.connection.sendPacket(new SSetSlotPacket(p_217066_0_, 0, itemstack));
        }
    }

    public static WorkshopContainer fromNetwork(int windowId, PlayerInventory playerInv, PacketBuffer buf) {
        return new WorkshopContainer(windowId, playerInv, (WorkshopEntity) DistExecutor.runForDist(() -> () -> {
            BlockPos pos = buf.readBlockPos();
            return Minecraft.getInstance().world.getTileEntity(pos);
        }, () -> () -> {
            throw new RuntimeException("Shouldn't be called on server!");
        }));
    }

    //public void onCraftMatrixChanged(IInventory inventoryIn) {
        //func_217066_a(this.windowId, this.tileEntity.getWorld(), this.player, this.tileEntity.inv, this.tileEntity.field_75160_f);
    //}

    //public void func_201771_a(RecipeItemHelper p_201771_1_) {
        //this.tileEntity.inv.fillStackedContents(p_201771_1_);
    //}

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
        par1IContainerListener.sendWindowProperty(this, 0, 1);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if(this.tileEntity.a){
            this.craft(this.tileEntity.getWorld());
        }
        //PacketHandler.HANDLER.sendTo(new UpdateWindowPacket(windowId, 0), ((ServerPlayerEntity)playerInventory.player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerEntity) { // was originally "PlayerEntity playerEntity"
        return true;
    }
}
