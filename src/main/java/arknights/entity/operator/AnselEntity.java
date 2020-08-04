package arknights.entity.operator;

import arknights.entity.AnselHealPack;
import arknights.registry.EntityHandler;
import arknights.registry.SoundHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class AnselEntity extends MedicSingle {
    protected int tick = 0;
    public AnselEntity(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
    }

    public AnselEntity(World p_i48574_2_, Item item) {
        super(EntityHandler.ANSEL, p_i48574_2_, item);
    }

    public void livingTick() {
        super.livingTick();
        if(!world.isRemote()) {
            this.tick++;
            if (this.tick == 599 ) {
                this.tick=0;
                this.yell();
            }
        }
    }

    protected void yell(){
        switch (new Random().nextInt(2)) {
            case 0:
                this.playSound(SoundHandler.ANSEL_COMBATING1, 1.0F, 1.0F);
                break;
            case 1:
                this.playSound(SoundHandler.ANSEL_COMBATING2, 1.0F, 1.0F);
                break;
            default:
                break;
        }
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        AnselHealPack anselHealPack = new AnselHealPack(this.world, this);
        double d0 = target.getPosYEye() - (double)1.1F;
        double d1 = target.getPosX() - this.getPosX();
        double d2 = d0 - anselHealPack.getPosY();
        double d3 = target.getPosZ() - this.getPosZ();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        anselHealPack.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        //System.out.print(this.getAttackTarget() + "\n");
        this.world.addEntity(anselHealPack);
    }
}
