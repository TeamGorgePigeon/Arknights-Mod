package arknights.item;

import arknights.entity.operator.AmiyaEntity;
import net.minecraft.world.World;

public class Amiya extends OperatorItem {
    public Amiya(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public void newOperator(World world){
        this.operator = new AmiyaEntity(world, this);
    }
}
