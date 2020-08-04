package arknights.entity.enemy;

import arknights.item.FaustCrossBow;
import arknights.registry.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class FaustEntity extends CrossBowEnemy {
    private static final DataParameter<Boolean> DATA_CHARGING_STATE = EntityDataManager.createKey(FaustEntity.class, DataSerializers.BOOLEAN);
    private final Inventory inventory = new Inventory(5);
    protected Raid raid;

    public FaustEntity(EntityType<? extends FaustEntity> p_i50198_1_, World p_i50198_2_) {
        super(p_i50198_1_, p_i50198_2_);
    }

    public void registerGoals() {
        super.registerGoals();
    }


    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_CHARGING_STATE, false);
        this.getAttribute(Attributes.field_233821_d_).setBaseValue((double)0.35F);
        this.getAttribute(Attributes.field_233818_a_).setBaseValue(30.0D);
        this.setHealth(20.0F);
        this.getAttribute(Attributes.field_233823_f_).setBaseValue(20.0D);
        this.getAttribute(Attributes.field_233819_b_).setBaseValue(32.0D);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isCharging() {
        return this.dataManager.get(DATA_CHARGING_STATE);
    }

    public void setCharging(boolean p_213671_1_) {
        this.dataManager.set(DATA_CHARGING_STATE, p_213671_1_);
    }

    @Override
    public void func_230284_a_(LivingEntity livingEntity, ItemStack itemStack, ProjectileEntity projectileEntity, float v) {

    }

    @Override
    public void func_230283_U__() {

    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        ListNBT listnbt = new ListNBT();

        for(int i = 0; i < this.inventory.getSizeInventory(); ++i) {
            ItemStack itemstack = this.inventory.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
                listnbt.add(itemstack.write(new CompoundNBT()));
            }
        }

        compound.put("Inventory", listnbt);
    }

    @OnlyIn(Dist.CLIENT)
    public CrossBowEnemy.ArmPose getArmPose() {
        if (this.isCharging()) {
            return CrossBowEnemy.ArmPose.CROSSBOW_CHARGE;
        } else if (this.getHeldItemMainhand().getItem()==Items.CROSSBOW) {
            return CrossBowEnemy.ArmPose.CROSSBOW_HOLD;
        } else {
            return this.isAggressive() ? CrossBowEnemy.ArmPose.ATTACKING : CrossBowEnemy.ArmPose.NEUTRAL;
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        ListNBT listnbt = compound.getList("Inventory", 10);

        for(int i = 0; i < listnbt.size(); ++i) {
            ItemStack itemstack = ItemStack.read(listnbt.getCompound(i));
            if (!itemstack.isEmpty()) {
                this.inventory.addItem(itemstack);
            }
        }

        this.setCanPickUpLoot(true);
    }

    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        Block block = worldIn.getBlockState(pos.down()).getBlock();
        return block != Blocks.GRASS_BLOCK && block != Blocks.SAND ? 0.5F - worldIn.getBrightness(pos) : 10.0F;
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.setEquipmentBasedOnDifficulty(difficultyIn);
        this.setEnchantmentBasedOnDifficulty(difficultyIn);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    /**
     * Gives armor or weapon for entity based on given DifficultyInstance
     */
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        ItemStack itemstack = new ItemStack(ItemHandler.FAUST_CROSSBOW);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, itemstack);
    }

    /**
     * Returns whether this Entity is on the same team as the given Entity.
     */
    public boolean isOnSameTeam(Entity entityIn) {
        if (super.isOnSameTeam(entityIn)) {
            return true;
        } else if (entityIn instanceof LivingEntity && ((LivingEntity)entityIn).getCreatureAttribute() == CreatureAttribute.ILLAGER) {
            return this.getTeam() == null && entityIn.getTeam() == null;
        } else {
            return false;
        }
    }


    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        Hand hand = ProjectileHelper.getHandWith(this, Items.CROSSBOW);
        ItemStack itemstack = this.getHeldItem(hand);
        if (this.getHeldItemMainhand().getItem()==ItemHandler.FAUST_CROSSBOW) {
            FaustCrossBow.fireProjectiles(this.world, this, hand, itemstack, 1.6F, (float)(14 - this.world.getDifficulty().getId() * 4));
        }

        this.idleTime = 0;
    }

    public void shoot(LivingEntity p_213670_1_, ItemStack p_213670_2_, ProjectileEntity p_213670_3_, float p_213670_4_) {
        Entity entity = (Entity)p_213670_3_;
        double d0 = p_213670_1_.getPosX() - this.getPosX();
        double d1 = p_213670_1_.getPosZ() - this.getPosZ();
        double d2 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1);
        double d3 = p_213670_1_.getPosYHeight(0.3333333333333333D) - entity.getPosY() + d2 * (double)0.2F;
        Vector3f vector3f = this.func_213673_a(new Vector3d(d0, d3, d1), p_213670_4_);
        p_213670_3_.shoot((double)vector3f.getX(), (double)vector3f.getY(), (double)vector3f.getZ(), 1.6F, (float)(14 - this.world.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.ITEM_CROSSBOW_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
    }

    private Vector3f func_213673_a(Vector3d p_213673_1_, float p_213673_2_) {
        Vector3d vec3d = p_213673_1_.normalize();
        Vector3d vec3d1 = vec3d.crossProduct(new Vector3d(0.0D, 1.0D, 0.0D));
        if (vec3d1.lengthSquared() <= 1.0E-7D) {
            vec3d1 = vec3d.crossProduct(this.getUpVector(1.0F));
        }

        Quaternion quaternion = new Quaternion(new Vector3f(vec3d1), 90.0F, true);
        Vector3f vector3f = new Vector3f(vec3d);
        vector3f.transform(quaternion);
        Quaternion quaternion1 = new Quaternion(vector3f, p_213673_2_, true);
        Vector3f vector3f1 = new Vector3f(vec3d);
        vector3f1.transform(quaternion1);
        return vector3f1;
    }
}