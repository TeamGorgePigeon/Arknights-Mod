package arknights.entity.operator;

import arknights.container.UpgradeContainer;
import arknights.container.WorkshopContainer;
import arknights.entity.enemy.EnemyBase;
import arknights.item.OperatorItem;
import arknights.registry.SoundHandler;
import arknights.tileentity.TradingHomeEntity;
import arknights.tileentity.UpgradeEntity;
import arknights.tileentity.WorkshopEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nullable;
import java.util.Objects;

public class OperatorBase extends TameableEntity {
    private SummonOperatorGoal summonOperator;
    public Item item = Items.AIR;
    public static final DataParameter<Boolean> OPERATORATTACKING = EntityDataManager.createKey(ExusiaiEntity.class, DataSerializers.BOOLEAN);
    protected int sp;
    protected int tick = 0;
    protected boolean isSkill = false;
    private int eliteLevel=0;
    private int level=1;
    private int trust=0;
    private int xp=0;
    public boolean onSpawn=true;
    private UpgradeEntity upgradeEntity;

    public OperatorBase(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
    }

    public OperatorBase(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_, Item item) {
        super(p_i48574_1_, p_i48574_2_);
        this.item = item;
    }

    protected void yell(){

    }

    protected void registerGoals() {
        //this.goalSelector.addGoal(2, this.sitGoal);
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1.0F));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0F, 120));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.summonOperator = new OperatorBase.SummonOperatorGoal(this);
        this.goalSelector.addGoal(3, new SwimGoal(this));
        this.goalSelector.addGoal(3, this.summonOperator);
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EnderDragonEntity.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, WitherEntity.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EnemyBase.class, true));
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(OPERATORATTACKING, false);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return TameableEntity.func_233639_cI_()
                .func_233815_a_(Attributes.field_233818_a_, 20.0D)
                .func_233815_a_(Attributes.field_233821_d_, 0.3D)
                .func_233815_a_(Attributes.field_233823_f_, 5.0D);
    }

    public void livingTick() {
        if (onSpawn) {
            loadName();
            onSpawn=false;
        }
        super.livingTick();
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageableEntity) {
        return null;
    }

    public void onDeath(DamageSource cause) {
        if(this.item instanceof OperatorItem && !world.isRemote()){
            ItemStack itemStack = new ItemStack(this.item, 1);
            CompoundNBT compound = new CompoundNBT();
            compound.putInt("EliteLevel", this.eliteLevel);
            compound.putInt("Level", this.level);
            compound.putInt("Trust", this.trust);
            compound.putInt("Xp", this.xp);
            itemStack.setTag(compound);
            ItemEntity entity = new ItemEntity(world, this.getOwner().getPositionVec().getX(), this.getOwner().getPositionVec().getY(), this.getOwner().getPositionVec().getZ(), itemStack);
            world.addEntity(entity);
        }
    }

    static class SummonOperatorGoal extends Goal {
        private final OperatorBase operatorBase;

        public SummonOperatorGoal(OperatorBase operatorBaseIn) {
            this.operatorBase = operatorBaseIn;
        }

        @Override
        public boolean shouldExecute() {
            return false;
        }
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("EliteLevel", this.eliteLevel);
        compound.putInt("Level", this.level);
        compound.putInt("Trust", this.trust);
        compound.putInt("Xp", this.xp);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */

     public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.eliteLevel = compound.getInt("EliteLevel");
        this.level = compound.getInt("Level");
        this.trust = compound.getInt("Trust");
        this.xp = compound.getInt("Xp");
    }

    /*
    public boolean processInteract(PlayerEntity player, Hand hand) {
        if (!world.isRemote) {
            BlockPos pos=player.getPosition();
            player.openContainer(new SimpleNamedContainerProvider((p_213701_1_, p_213701_2_, p_213701_3_) -> {
                return new UpgradeContainer(p_213701_1_, p_213701_2_);
            }, new TranslationTextComponent(this.getDisplayName().getString())));
            //NetworkHooks.openGui((ServerPlayerEntity) player, , pos);
        }
         return true;
    }*/

    public void loadName() {
        if (!world.isRemote) {
            this.setCustomName(null);
            if (this.getOwner() != null) {
                String displayOwner = this.getOwner().getDisplayName().getString();
                String of = new TranslationTextComponent("info.of").getString();
                if (of=="'s")
                this.setCustomName(new TranslationTextComponent(displayOwner  + of + " " + this.getDisplayName().getString()));
            } else {
                this.setCustomName(new TranslationTextComponent(this.getDisplayName().getString()));
            }
        }
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.75F;
    }
        public SoundEvent getDeathSound() {
            return SoundHandler.OPERATOR_DEAD;
        }
}

