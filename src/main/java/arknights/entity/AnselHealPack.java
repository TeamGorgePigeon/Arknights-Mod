package arknights.entity;

import arknights.registry.EntityHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AnselHealPack extends ProjectileItemEntity {
    public AnselHealPack(EntityType<? extends AnselHealPack> p_i50159_1_, World p_i50159_2_) {
        super(p_i50159_1_, p_i50159_2_);
    }

    public AnselHealPack(World worldIn, LivingEntity throwerIn) {
        super(EntityHandler.ANSELHEALPACK, throwerIn, worldIn);
    }

    public AnselHealPack(World worldIn, double x, double y, double z) {
        super(EntityHandler.ANSELHEALPACK, x, y, z, worldIn);
    }

    protected Item func_213885_i() {
        return Items.SNOWBALL;
    }

    @OnlyIn(Dist.CLIENT)
    private IParticleData func_213887_n() {
        ItemStack itemstack = this.func_213882_k();
        return (IParticleData) (itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemstack));
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            IParticleData iparticledata = this.func_213887_n();

            for (int i = 0; i < 8; ++i) {
                this.world.addParticle(iparticledata, this.func_226277_ct_(), this.func_226278_cu_(), this.func_226281_cx_(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult) result).getEntity();
            //int i = entity instanceof BlazeEntity ? 3 : 0;
            //entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) i);
            if(entity instanceof LivingEntity){
                ((LivingEntity) entity).setHealth(((LivingEntity) entity).getHealth() + 2);
                this.world.addParticle(ParticleTypes.FLAME, this.getPositionVec().x, this.getPositionVec().y, this.getPositionVec().z, 1, 1, 1);
            }
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }

    }
}