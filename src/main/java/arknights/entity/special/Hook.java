package arknights.entity.special;

import arknights.entity.operator.RopeEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeFluidState;

import javax.annotation.Nullable;

public class Hook extends Entity {
    private static final DataParameter<Integer> DATA_HOOKED_ENTITY = EntityDataManager.createKey(Hook.class, DataSerializers.VARINT);
    private boolean inGround;
    private int ticksInGround;
    private final RopeEntity angler;
    private int ticksInAir;
    public Entity caughtEntity;
    private Hook.State currentState = Hook.State.FLYING;

    private Hook(World world, RopeEntity user) {
        super(EntityType.FISHING_BOBBER, world);
        this.ignoreFrustumCheck = true;
        this.angler = user;
    }

    @OnlyIn(Dist.CLIENT)
    public Hook(World worldIn, RopeEntity user, double x, double y, double z) {
        this(worldIn, user);
        this.setPosition(x, y, z);
        this.prevPosX = this.getPosX();
        this.prevPosY = this.getPosY();
        this.prevPosZ = this.getPosZ();
    }

    public Hook(RopeEntity user, World world) {
        this(world, user);
        float f = this.angler.rotationPitch;
        float f1 = this.angler.rotationYaw;
        float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
        float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
        double d0 = this.angler.getPosX() - (double)f3 * 0.3D;
        double d1 = this.angler.getPosYEye();
        double d2 = this.angler.getPosZ() - (double)f2 * 0.3D;
        this.setLocationAndAngles(d0, d1, d2, f1, f);
        Vector3d vec3d = new Vector3d((double)(-f3), (double)MathHelper.clamp(-(f5 / f4), -5.0F, 5.0F), (double)(-f2));
        double d3 = vec3d.length();
        vec3d = vec3d.mul(0.6D / d3 + 0.5D + this.rand.nextGaussian() * 0.0045D, 0.6D / d3 + 0.5D + this.rand.nextGaussian() * 0.0045D, 0.6D / d3 + 0.5D + this.rand.nextGaussian() * 0.0045D);
        this.setMotion(vec3d.scale(10.0D));
        this.rotationYaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * (double)(180F / (float)Math.PI));
        //this.rotationPitch = (float)(MathHelper.atan2(vec3d.y, (double)MathHelper.sqrt(func_213296_b(vec3d))) * (double)(180F / (float)Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    public Hook(EntityType<?> p_i48580_1_, World p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
        this.angler = new RopeEntity(this.world, new Item(new Item.Properties()));
    }


    protected void registerData() {
        this.getDataManager().register(DATA_HOOKED_ENTITY, 0);
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        if (DATA_HOOKED_ENTITY.equals(key)) {
            int i = this.getDataManager().get(DATA_HOOKED_ENTITY);
            this.caughtEntity = i > 0 ? this.world.getEntityByID(i - 1) : null;
        }

        super.notifyDataManagerChange(key);
    }

    /**
     * Checks if the entity is in range to render.
     */
    @OnlyIn(Dist.CLIENT)
    public boolean isInRangeToRenderDist(double distance) {
        double d0 = 64.0D;
        return distance < 4096.0D;
    }

    /**
     * Sets a target for the client to interpolate towards over the next few ticks
     */
    @OnlyIn(Dist.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();
        if (this.angler == null) {
            this.remove();
        } else if (this.world.isRemote || !this.shouldStopFishing()) {
            if (this.inGround) {
                ++this.ticksInGround;
                if (this.ticksInGround >= 1200) {
                    this.remove();
                    return;
                }
            }

            //BlockPos blockpos = new BlockPos(this);
            //IForgeFluidState ifluidstate = this.world.getFluidState(blockpos);

            if (this.currentState == Hook.State.FLYING) {
                if (this.caughtEntity != null) {
                    this.setMotion(Vector3d.ZERO);
                    this.currentState = Hook.State.HOOKED_IN_ENTITY;
                    return;
                }

                if (!this.world.isRemote) {
                    this.checkCollision();
                }

                if (!this.inGround && !this.onGround && !this.collidedHorizontally) {
                    ++this.ticksInAir;
                } else {
                    this.ticksInAir = 0;
                    this.setMotion(Vector3d.ZERO);
                }
            } else {
                if (this.currentState == Hook.State.HOOKED_IN_ENTITY) {
                    if (this.caughtEntity != null) {
                        if (this.caughtEntity.removed) {
                            this.caughtEntity = null;
                            this.currentState = Hook.State.FLYING;
                        } else {
                           // this.setPosition(this.caughtEntity.getPosX(), this.caughtEntity.func_226283_e_(0.8D), this.caughtEntity.getPosZ());
                        }
                    }

                    return;
                }
            }

            /*if (!ifluidstate.isTagged(FluidTags.WATER)) {
                this.setMotion(this.getMotion().add(0.0D, -0.03D, 0.0D));
            }*/

            this.move(MoverType.SELF, this.getMotion());
            this.updateRotation();
            this.setMotion(this.getMotion().scale(0.92D));
            //this.func_226264_Z_();
        }
    }

    private boolean shouldStopFishing() {
        if (!this.angler.removed && this.angler.isAlive() && !(this.getDistanceSq(this.angler) > 1024.0D)) {
            return false;
        } else {
            this.remove();
            return true;
        }
    }

    private void updateRotation() {
        Vector3d vec3d = this.getMotion();
        //float f = MathHelper.sqrt(func_213296_b(vec3d));
        this.rotationYaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * (double)(180F / (float)Math.PI));

        /*for(this.rotationPitch = (float)(MathHelper.atan2(vec3d.y, (double)f) * (double)(180F / (float)Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
            ;
        }*/

        while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
            this.prevRotationPitch += 360.0F;
        }

        while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
            this.prevRotationYaw -= 360.0F;
        }

        while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = MathHelper.lerp(0.2F, this.prevRotationPitch, this.rotationPitch);
        this.rotationYaw = MathHelper.lerp(0.2F, this.prevRotationYaw, this.rotationYaw);
    }

    private void checkCollision() {
        /*RayTraceResult raytraceresult = ProjectileHelper.func_221267_a(this, this.getBoundingBox().expand(this.getMotion()).grow(1.0D), (p_213856_1_) -> {
            return !p_213856_1_.isSpectator() && (p_213856_1_.canBeCollidedWith() || p_213856_1_ instanceof ItemEntity) && (p_213856_1_ != this.angler || this.ticksInAir >= 5);
        }, RayTraceContext.BlockMode.COLLIDER, true);
        if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
            if (raytraceresult.getType() == RayTraceResult.Type.ENTITY) {
                this.caughtEntity = ((EntityRayTraceResult)raytraceresult).getEntity();
                this.setHookedEntity();
            } else {
                this.inGround = true;
            }
        }*/

    }

    private void setHookedEntity() {
        this.getDataManager().set(DATA_HOOKED_ENTITY, this.caughtEntity.getEntityId() + 1);
    }

    public void writeAdditional(CompoundNBT compound) {
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
    }

    public int handleHookRetraction() {//Get the hook back
        if (!this.world.isRemote && this.angler != null) {
            int i = 0;
            if (this.caughtEntity != null) {
                this.bringInHookedEntity();
                this.world.setEntityState(this, (byte)31);
                i = this.caughtEntity instanceof ItemEntity ? 3 : 5;
            }

            if (this.inGround) {
                i = 2;
            }

            this.remove();

            return i;
        } else {
            return 0;
        }
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    /**
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 31 && this.world.isRemote && this.caughtEntity instanceof PlayerEntity && ((PlayerEntity)this.caughtEntity).isUser()) {
            this.bringInHookedEntity();
        }

        super.handleStatusUpdate(id);
    }*/

    protected void bringInHookedEntity() {
        if (this.angler != null) {
            Vector3d vec3d = (new Vector3d(this.angler.getPosX() - this.getPosX(), this.angler.getPosY() - this.getPosY(), this.angler.getPosZ() - this.getPosZ())).scale(0.1D);
            this.caughtEntity.setMotion(this.caughtEntity.getMotion().add(vec3d.scale(5.0D)));
            this.remove();
            this.angler.hook = null;
        }
    }

    protected boolean func_225502_at_() {
        return false;
    }

    @Override
    public void remove(boolean keepData) {
        super.remove(keepData);
        if (this.angler != null) {
            this.angler.hook = null;
        }

    }

    @Nullable
    public RopeEntity getAngler() {
        return this.angler;
    }

    /**
     * Returns false if this Entity is a boss, true otherwise.
     */
    public boolean isNonBoss() {
        return false;
    }

    public IPacket<?> createSpawnPacket() {
        Entity entity = this.getAngler();
        return new SSpawnObjectPacket(this, entity == null ? this.getEntityId() : entity.getEntityId());
    }

    static enum State {
        FLYING,
        HOOKED_IN_ENTITY,
    }
}