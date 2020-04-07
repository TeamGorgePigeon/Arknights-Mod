package arknights.block;

import arknights.registry.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class HeadHuntingBaggageBlock extends Block {
   private int openTime=0;
   public World world;
   public HeadHuntingBaggageBlock(Block.Properties p_i49994_1_) {
      super(p_i49994_1_);
   }

   public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
      if (!worldIn.isRemote) {
         if (openTime==5) {
            int random = new Random().nextInt(100);
               if (random < 40) {
                  summonItem(ItemHandler._3Star.get(new Random().nextInt(ItemHandler._3Star.size())),pos);
               }
               if (random >= 40 && random < 90) {
                  summonItem(ItemHandler._4Star.get(new Random().nextInt(ItemHandler._4Star.size())),pos);
               }
               if (random >= 90 && random < 98) {
                  summonItem(ItemHandler._5Star.get(new Random().nextInt(ItemHandler._5Star.size())),pos);
               }
               if (random >= 98 && random < 100) {
                  summonItem(ItemHandler._6Star.get(new Random().nextInt(ItemHandler._6Star.size())),pos);
               }
         }else {
            openTime+=1;
         }
      }
   }

   public void summonItem(IItemProvider Item,BlockPos pos) {
      ItemEntity entity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Item, 1));
      world.addEntity(entity);
   }
   public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
      return false;
   }
}