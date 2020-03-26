package arknights.entity.disaster;

import arknights.entity.notLiving.Meteorite;
import arknights.network.PacketHandler;
import arknights.network.packets.DisasterPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Random;

public class DisasterZero extends Entity {
    private static final DataParameter<Integer> TIME = EntityDataManager.createKey(DisasterZero.class, DataSerializers.VARINT);
    private int time = 500;
    private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
    public DisasterZero(EntityType<?> p_i48580_1_, World p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
        if(new Random().nextInt(100) >= 98)
            //this.remove();
            System.out.print(1);
        //PacketHandler.HANDLER.send(PacketDistributor.ALL.noArg(), new DisasterPacket(true));
    }

    @Override
    public void tick(){
        if(this.time % 5 == 0){
            /*
            TNTEntity tntEntity = new TNTEntity(this.getEntityWorld(),
                    this.getPositionVec().x + (20 - new Random().nextInt(40)), this.getPositionVec().y, this.getPositionVec().z + (20 - new Random().nextInt(40)),
                    null);
            this.getEntityWorld().addEntity(tntEntity);
            */
            /*
            FireballEntity fireballEntity = new FireballEntity(this.getEntityWorld(),
                    this.getPositionVec().x + (100 - new Random().nextInt(200)), this.getPositionVec().y + 128, this.getPositionVec().z + (100 - new Random().nextInt(200)), 0, -500, 0){
                @Override
                protected IParticleData getParticle() {
                    return ParticleTypes.FLAME;
                }
            };
            */
            Meteorite fireballEntity = new Meteorite(this.getEntityWorld(),
                    this.getPositionVec().x + (100 - new Random().nextInt(200)), this.getPositionVec().y + 128, this.getPositionVec().z + (100 - new Random().nextInt(200)), 0, -500, 0);
            fireballEntity.explosionPower = 10;
            //fireballEntity.spawnRunningParticles();
            this.getEntityWorld().addEntity(fireballEntity);
        }
        if(this.time<=0){
            this.remove();
            //PacketHandler.HANDLER.send(PacketDistributor.ALL.noArg(), new DisasterPacket(false));
        }
        this.time--;
    }

    @Override
    protected void registerData() {
        //this.dataManager.register(TIME, 500);
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
