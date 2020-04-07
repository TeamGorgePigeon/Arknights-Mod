package arknights.entity.operator;

import arknights.entity.AnselHealPack;
import arknights.registry.EntityHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class AnselEntity extends MedicSingle {
    public AnselEntity(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
    }

    public AnselEntity(World p_i48574_2_, Item item) {
        super(EntityHandler.ANSEL, p_i48574_2_, item);
    }



    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        AnselHealPack anselHealPack = new AnselHealPack(this.world, this);
        double d0 = target.func_226280_cw_() - (double)1.1F;
        double d1 = target.func_226277_ct_() - this.func_226277_ct_();
        double d2 = d0 - anselHealPack.func_226278_cu_();
        double d3 = target.func_226281_cx_() - this.func_226281_cx_();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        anselHealPack.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        System.out.print(this.getAttackTarget() + "\n");
        this.world.addEntity(anselHealPack);
    }
}
