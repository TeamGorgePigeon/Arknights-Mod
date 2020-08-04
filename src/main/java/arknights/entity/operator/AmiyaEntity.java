package arknights.entity.operator;

//import arknights.entity.notLiving.AmiyaMagic;
import arknights.registry.EntityHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class AmiyaEntity extends RangeOperator {
    public AmiyaEntity(EntityType<? extends AmiyaEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
    }

    public AmiyaEntity(World world, Item item){
        super(EntityHandler.AMIYA, world, item);
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float v) {
        /*
        if(target != this.getOwner()) {
            double d0 = target.getPosX() - this.getPosX();
            double d1;
            double d2 = target.getPosZ() - this.getPosZ();
            double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
            //float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;

            if (!world.isRemote()) {
                AmiyaMagic bulletEntity = new AmiyaMagic(this, this.world);
                d1 = target.getPosYHeight(0.3333333333333333D) - bulletEntity.getPosY();
                bulletEntity.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, 1.0F);
                //this.playSound(SoundHandler.EXUSIAI_ATTACK, 1.0F, 1.0F);
                this.world.addEntity(bulletEntity);
            }
        }
         */
    }
}
