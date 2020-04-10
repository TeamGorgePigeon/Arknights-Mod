package arknights.tileentity;

import arknights.container.UpgradeContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class UpgradeEntity extends TileEntity implements INamedContainerProvider {
    private final ItemStackHandler inventory = new ItemStackHandler(104);
    private LazyOptional<IItemHandler> handler = LazyOptional.of(() -> inventory);

    public UpgradeEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new UpgradeContainer(i, playerInventory, this);
    }

    @Override
    public final CompoundNBT getUpdateTag() {
        return write(new CompoundNBT());
    }

    @Override
    public void read(@Nonnull CompoundNBT nbt) {
        super.read(nbt);
    }

    @Nonnull
    @Override
    public CompoundNBT write( CompoundNBT nbt) {
        nbt = super.write(nbt);
        return nbt;
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
