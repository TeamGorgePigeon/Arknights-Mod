package arknights.tileentity;

import arknights.container.WorkshopContainer;
import arknights.recipe.WorkshopInv;
import arknights.registry.TileEntityHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WorkshopEntity extends TileEntity implements INamedContainerProvider, ITickableTileEntity {

    public final ItemStackHandler inventory = new ItemStackHandler(3);
    public boolean a = false;
    private LazyOptional<IItemHandler> handler = LazyOptional.of(() -> inventory);
    public WorkshopEntity() {
        super(TileEntityHandler.WORKSHOPENTITY);
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new WorkshopContainer(i, playerInventory, this);
        //return new WorkshopContainer(i, playerInventory, IWorldPosCallable.of(this.world, this.pos));
    }

    @Override
    public final CompoundNBT getUpdateTag() {
        return write(new CompoundNBT());
    }

    public void func_230337_a_(BlockState blockState, CompoundNBT nbt) {
        super.func_230337_a_(blockState, nbt);
        inventory.deserializeNBT(nbt);
    }

    @Nonnull
    @Override
    public CompoundNBT write( CompoundNBT nbt) {
        nbt = super.write(nbt);
        nbt.merge(inventory.serializeNBT());
        return nbt;
    }

    @Override
    public void tick() {
        if(this.inventory.getStackInSlot(0) != ItemStack.EMPTY || this.inventory.getStackInSlot(1) != ItemStack.EMPTY || this.inventory.getStackInSlot(2) != ItemStack.EMPTY){
            this.a = true;
        } else {
            this.a = false;
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing){
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            //T result = (T) (facing == Direction.DOWN ? downInventory : upInventory);
            //return LazyOptional.of(() -> result);
            //return LazyOptional.of(() -> (T)handler);
            return handler.cast();
        }
        return super.getCapability(capability, facing);
    }
}
