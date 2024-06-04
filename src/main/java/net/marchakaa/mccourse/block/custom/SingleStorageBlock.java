package net.marchakaa.mccourse.block.custom;

import net.marchakaa.mccourse.MCCourseMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SingleStorageBlock extends HorizontalDirectionalBlock {
    private ItemStack item = ItemStack.EMPTY;
    private int amount = 0;
    private static final int MAX_AMOUNT = 200;
    
    public SingleStorageBlock(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        ItemStack itemInHand = pPlayer.getItemInHand(pHand);
        if(!itemInHand.isEmpty()) {
            MCCourseMod.LOGGER.info("There is (" + amount + ") {" + itemInHand.getItem() + "} (" + itemInHand.getCount() + ")");
            if(item.isEmpty()) {
                item = itemInHand.copy();
                item.setCount(1);
                amount = itemInHand.getCount();
                pPlayer.getItemInHand(pHand).shrink(itemInHand.getCount());
            } else {
                if(itemInHand.getItem().equals(item.getItem())) {
                    if(amount + itemInHand.getCount() >= MAX_AMOUNT) {
                        amount = MAX_AMOUNT;
                        pPlayer.getItemInHand(pHand).shrink(MAX_AMOUNT - amount);

                    } else {
                        amount += itemInHand.getCount();
                        pPlayer.getItemInHand(pHand).shrink(itemInHand.getCount());
                    }
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
}
