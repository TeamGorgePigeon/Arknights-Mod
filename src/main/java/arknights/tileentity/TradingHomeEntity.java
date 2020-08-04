package arknights.tileentity;

import arknights.container.TradingHomeContainer;
import arknights.registry.ItemHandler;
import arknights.registry.TileEntityHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TradingHomeEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private final ItemStackHandler inventory = new ItemStackHandler(104);
    private LazyOptional<IItemHandler> handler = LazyOptional.of(() -> inventory);
    public int time = 0;
    private boolean firstManuf = true;

    public TradingHomeEntity() {
        super(TileEntityHandler.TRADINGHOMEENTITY);
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    //@Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new TradingHomeContainer(i, playerInventory, this);
        //return new WorkshopContainer(i, playerInventory, IWorldPosCallable.of(this.world, this.pos));
    }

    @Override
    public final CompoundNBT getUpdateTag() {
        return write(new CompoundNBT());
    }

    @Nonnull
    @Override
    public CompoundNBT write( CompoundNBT nbt) {
        nbt = super.write(nbt);

        return nbt;
    }

    public void func_230337_a_(BlockState blockState, CompoundNBT nbt) {
        super.func_230337_a_(blockState, nbt);
        inventory.deserializeNBT(nbt);
        CompoundNBT time = getUpdateTag().getCompound("time");
    }

    private boolean canManufacture(){
        return (this.inventory.getStackInSlot(101).getItem() == ItemHandler.ORIROCKCUBE || this.inventory.getStackInSlot(102).getItem() == ItemHandler.ORIROCKCUBE || this.inventory.getStackInSlot(103).getItem() == ItemHandler.ORIROCKCUBE) &&
                (this.inventory.getStackInSlot(101).getCount() >= 2|| this.inventory.getStackInSlot(102).getCount() >= 2 || this.inventory.getStackInSlot(103).getCount() >= 2) && (this.inventory.getStackInSlot(100).getCount() < 64);
    }

    @Override
    public void tick() {
        if(!getWorld().isRemote){
            if(time<=0 && canManufacture()){
                this.inventory.insertItem(100, new ItemStack(ItemHandler.ORIGINIUMSHARD), false);
                if(this.inventory.getStackInSlot(101).getCount() >= 2){
                    this.inventory.getStackInSlot(101).setCount(this.inventory.getStackInSlot(101).getCount() - 2);
                }
                else if(this.inventory.getStackInSlot(102).getCount() >= 2){
                    this.inventory.getStackInSlot(102).setCount(this.inventory.getStackInSlot(102).getCount() - 2);
                }
                else if(this.inventory.getStackInSlot(103).getCount() >= 2){
                    this.inventory.getStackInSlot(103).setCount(this.inventory.getStackInSlot(103).getCount() - 2);
                }
                this.time = 100;
            }
            if(canManufacture()){
                this.time--;
            }
            if(!canManufacture()){
                this.time = 100;
            }

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
