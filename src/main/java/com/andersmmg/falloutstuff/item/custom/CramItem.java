package com.andersmmg.falloutstuff.item.custom;

import com.andersmmg.falloutstuff.block.custom.CramBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;

public class CramItem extends BlockItem {
    public CramItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().getBlockState(context.getBlockPos()).getBlock() instanceof CramBlock) {
            BlockState state = context.getWorld().getBlockState(context.getBlockPos());
            return switch (state.get(CramBlock.COUNT)) {
                case 1 -> {
                    context.getWorld().setBlockState(context.getBlockPos(), state.with(CramBlock.COUNT, 2), 3);
                    BlockSoundGroup blockSoundGroup = state.getSoundGroup();
                    context.getWorld().playSound(null, context.getBlockPos(), blockSoundGroup.getPlaceSound(), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F);
                    context.getStack().decrement(1);
                    yield ActionResult.SUCCESS;
                }
                case 2 -> {
                    context.getWorld().setBlockState(context.getBlockPos(), state.with(CramBlock.COUNT, 3), 3);
                    BlockSoundGroup blockSoundGroup = state.getSoundGroup();
                    context.getWorld().playSound(null, context.getBlockPos(), this.getPlaceSound(state), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F);
                    context.getStack().decrement(1);
                    yield ActionResult.SUCCESS;
                }
                default -> ActionResult.FAIL;
            };
        }
        return super.useOnBlock(context);
    }
}
