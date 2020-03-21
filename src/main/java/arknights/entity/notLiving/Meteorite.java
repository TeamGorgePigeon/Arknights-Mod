package arknights.entity.notLiving;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Meteorite extends FireballEntity {
    private int ticksInAir;

    public Meteorite(EntityType<? extends Meteorite> p_i50163_1_, World p_i50163_2_) {
        super(p_i50163_1_, p_i50163_2_);
    }

    public Meteorite(World p_i1768_1_, double p_i1768_2_, double p_i1768_4_, double p_i1768_6_, double p_i1768_8_, double p_i1768_10_, double p_i1768_12_) {
        super(p_i1768_1_, p_i1768_2_, p_i1768_4_, p_i1768_6_, p_i1768_8_, p_i1768_10_, p_i1768_12_);
    }

    @Override
    public void tick() {
        if (this.world.isRemote || (this.shootingEntity == null || !this.shootingEntity.removed) && this.world.isBlockLoaded(new BlockPos(this))) {
            super.tick();
            if (this.isFireballFiery()) {
                this.setFire(1);
            }

            ++this.ticksInAir;
            RayTraceResult raytraceresult = ProjectileHelper.func_221266_a(this, true, this.ticksInAir >= 25, this.shootingEntity, RayTraceContext.BlockMode.COLLIDER);
            if (raytraceresult.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onImpact(raytraceresult);
            }

            Vec3d vec3d = this.getMotion();
            double d0 = this.func_226277_ct_() + vec3d.x;
            double d1 = this.func_226278_cu_() + vec3d.y;
            double d2 = this.func_226281_cx_() + vec3d.z;
            ProjectileHelper.rotateTowardsMovement(this, 0.2F);
            float f = this.getMotionFactor();
            if (this.isInWater()) {
                for(int i = 0; i < 4; ++i) {
                    float f1 = 0.25F;
                    this.world.addParticle(ParticleTypes.BUBBLE, d0 - vec3d.x * 0.25D, d1 - vec3d.y * 0.25D, d2 - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
                }

                f = 0.8F;
            }

            this.setMotion(vec3d.add(this.accelerationX, this.accelerationY, this.accelerationZ).scale((double)f));
            this.world.addParticle(ParticleTypes.FLAME, d0, d1 + 0.5D, d2, 0.0D, 0.0D, 0.0D);
            this.setPosition(d0, d1, d2);
        } else {
            this.remove();
        }
    }
}
