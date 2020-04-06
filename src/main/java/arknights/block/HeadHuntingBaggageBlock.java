package arknights.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HeadHuntingBaggageBlock extends Block {
   private int openTime=0;

   public HeadHuntingBaggageBlock(Block.Properties p_i49994_1_) {
      super(p_i49994_1_);
   }

   public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
      if (!worldIn.isRemote) {
         if (openTime==5) {
         //TODO
         }else {
            openTime+=1;
         }
      }
   }
   public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
      return false;
   }
}