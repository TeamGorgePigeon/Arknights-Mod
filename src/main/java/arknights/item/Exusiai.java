package arknights.item;

import arknights.entity.operator.ExusiaiEntity;
import net.minecraft.world.World;

public class Exusiai extends OperatorItem {
    public Exusiai(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public void newOperator(World world){
        this.operator = new ExusiaiEntity(world, this);
    }
}
