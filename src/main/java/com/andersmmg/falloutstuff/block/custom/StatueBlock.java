package com.andersmmg.falloutstuff.block.custom;

import com.andersmmg.falloutstuff.util.VoxelUtils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class StatueBlock extends FacingBlock {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public StatueBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            return;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof Inventory) {
            ItemScatterer.spawn(world, pos, (Inventory) blockEntity);
            world.updateComparators(pos, this);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    protected static Direction getDirection(BlockState state) {
        return state.get(FACING);
    }

    private static final VoxelShape BOX_SHAPE = Block.createCuboidShape(5, 0, 5, 11, 10, 11);
    private static final VoxelShape BOX_SHAPE_SOUTH = VoxelUtils.rotateShape(Direction.NORTH, Direction.SOUTH, BOX_SHAPE);
    private static final VoxelShape BOX_SHAPE_EAST = VoxelUtils.rotateShape(Direction.NORTH, Direction.EAST, BOX_SHAPE);
    private static final VoxelShape BOX_SHAPE_WEST = VoxelUtils.rotateShape(Direction.NORTH, Direction.WEST, BOX_SHAPE);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = getDirection(state);
        return switch (facing) {
            case SOUTH -> BOX_SHAPE_SOUTH;
            case EAST -> BOX_SHAPE_EAST;
            case WEST -> BOX_SHAPE_WEST;
            default -> BOX_SHAPE;
        };
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }
}