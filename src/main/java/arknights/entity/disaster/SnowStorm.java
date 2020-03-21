package arknights.entity.disaster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.world.World;

import java.util.Random;

public class SnowStorm extends Entity {
    private static final DataParameter<Integer> TIME = EntityDataManager.createKey(DisasterZero.class, DataSerializers.VARINT);
    private int time = 1000;
    private double angle;
    public SnowStorm(EntityType<?> p_i48580_1_, World p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
        this.angle = new Random().nextInt(360) * Math.PI / 180;
    }

    @Override
    public void tick(){
        for(int i = 0; i <= 50; i++){
            SnowballEntity snowballEntity = new SnowballEntity(this.getEntityWorld(), this.getPositionVec().x + (100 - new Random().nextInt(200)), this.getPositionVec().y + new Random().nextInt(100), this.getPositionVec().z + (100 - new Random().nextInt(200)));
            //snowballEntity.setMotion(1 * Math.cos(this.angle), 0, 1 * Math.sin(this.angle));
            this.getEntityWorld().addEntity(snowballEntity);
            //System.out.print(snowballEntity.getPositionVec());
        }
        this.time--;
        if(this.time < 0){
            this.remove();
        }
    }

    @Override
    protected void registerData() { //this.dataManager.register(TIME, 100);
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
        //this.time = compound.getInt("time");
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        //compound.putInt("time", time);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return new SSpawnObjectPacket(this);
    }
}
