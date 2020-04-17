package arknights.block;

import arknights.registry.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class HeadHuntingBaggageBlock extends Block {
   private int openTime=0;
   private Item itemResult = null;
   public HeadHuntingBaggageBlock(Block.Properties p_i49994_1_) {
      super(p_i49994_1_);
   }

   public ActionResultType func_225533_a_(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) {
      if (worldIn.isRemote) {
         if (openTime==0) {
            itemResult=getRandomItem();
            System.out.print("yes\n");
         } else if (openTime==10) {
            ItemEntity entity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(itemResult, 1));
            worldIn.addEntity(entity);
               worldIn.removeBlock(pos, false);
            System.out.print("yes\n");
         }
      }
      openTime+=1;//bug:openTime will plus 2
      System.out.print(openTime+"\n");
      return ActionResultType.SUCCESS;
   }

   public Item getRandomItem() {
            int random = new Random().nextInt(100);
               if (random < 40) {
                  itemResult=ItemHandler._3Star.get(new Random().nextInt(ItemHandler._3Star.size()));
               }
               if (random >= 40 && random < 90) {
                  itemResult=ItemHandler._4Star.get(new Random().nextInt(ItemHandler._4Star.size()));
               }
               if (random >= 90 && random < 98) {
                  itemResult=ItemHandler._5Star.get(new Random().nextInt(ItemHandler._5Star.size()));
               }
               if (random >= 98 && random < 100) {
                  itemResult=ItemHandler._6Star.get(new Random().nextInt(ItemHandler._6Star.size()));
               }
      return itemResult;
   }

   @Override
   public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
      if (state.getBlock() != newState.getBlock()) {
         if (openTime!=0) {
            ItemEntity entity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(itemResult, 1));
            worldIn.addEntity(entity);
         }
      }
      super.onReplaced(state, worldIn, pos, newState, isMoving);
   }


   public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
      return false;
   }
}