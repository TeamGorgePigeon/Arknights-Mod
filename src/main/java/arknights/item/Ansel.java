package arknights.item;

import arknights.entity.operator.AnselEntity;
import net.minecraft.world.World;

public class Ansel extends OperatorItem {
    public Ansel(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public void newOperator(World world){
        this.operator = new AnselEntity(world, this);
    }
}