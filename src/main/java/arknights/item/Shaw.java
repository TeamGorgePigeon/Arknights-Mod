package arknights.item;

import arknights.entity.operator.ShawEntity;
import net.minecraft.world.World;

public class Shaw extends OperatorItem {
    public Shaw(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public void newOperator(World world){ this.operator = new ShawEntity(world, this); }
}
