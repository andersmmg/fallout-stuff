package com.andersmmg.falloutstuff.block.custom;

import com.andersmmg.falloutstuff.item.ModItems;
import com.andersmmg.falloutstuff.util.VoxelUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class CramBlock extends AbstractFloorFacingBlock {
    public static final IntProperty COUNT = IntProperty.of("count", 1, 3);
    private static final VoxelShape VOXEL_SHAPE = Block.createCuboidShape(4, 0, 5, 12, 7, 10);
    private static final VoxelShape VOXEL_SHAPE_2 = Block.createCuboidShape(3, 0, 1, 12, 7, 14);
    private static final VoxelShape VOXEL_SHAPE_3 = VoxelShapes.combineAndSimplify(Block.createCuboidShape(2, 0, 2, 12, 7, 14), Block.createCuboidShape(2, 7, 5, 11, 14, 13), BooleanBiFunction.OR);

    public CramBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(COUNT, 1));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        if (!player.getMainHandStack().isEmpty()) {
            return ActionResult.PASS;
        }
        switch (state.get(COUNT)) {
            case 1 -> {
                world.breakBlock(pos, true);
                return ActionResult.SUCCESS;
            }
            case 2 -> {
                world.setBlockState(pos, state.with(CramBlock.COUNT, 1), 3);
                BlockSoundGroup blockSoundGroup = state.getSoundGroup();
                world.playSound(null, pos, blockSoundGroup.getBreakSound(), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F);
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.CRAM_ITEM));
                return ActionResult.SUCCESS;
            }
            case 3 -> {
                world.setBlockState(pos, state.with(CramBlock.COUNT, 2), 3);
                BlockSoundGroup blockSoundGroup = state.getSoundGroup();
                world.playSound(null, pos, blockSoundGroup.getBreakSound(), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F);
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.CRAM_ITEM));
                return ActionResult.SUCCESS;
            }
            default -> {
                return ActionResult.PASS;
            }
        }
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        return List.of(new ItemStack(ModItems.CRAM_ITEM).copyWithCount(state.get(COUNT)));
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        ItemStack itemStack = super.getPickStack(world, pos, state);
        itemStack.setCount(state.get(COUNT));
        return itemStack;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelUtils.rotateShape(getDirection(state), switch (state.get(COUNT)) {
            case 2 -> VOXEL_SHAPE_2;
            case 3 -> VOXEL_SHAPE_3;
            default -> VOXEL_SHAPE;
        });
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, COUNT);
    }
}