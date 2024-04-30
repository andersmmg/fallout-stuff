package com.andersmmg.falloutstuff.block.custom;

import com.andersmmg.falloutstuff.util.VoxelUtils;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class VaultPosterBlock extends FacingBlock{
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public VaultPosterBlock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState) state.with(FACING, rotation.rotate((Direction) state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction) state.get(FACING)));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isSideSolidFullSquare(world, blockPos, direction);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
                                                WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return state;
    }

    protected static Direction getDirection(BlockState state) {
        return state.get(FACING);
    }

//    private static final VoxelShape POSTER_SHAPE = Block.createCuboidShape(0, 0, 15.5, 16, 16, 16);
    private static final VoxelShape POSTER_SHAPE = Block.createCuboidShape(0, -2, 15.9, 16, 19, 16);
    private static final VoxelShape POSTER_SHAPE_SOUTH = VoxelUtils.rotateShape(Direction.NORTH, Direction.SOUTH, POSTER_SHAPE);
    private static final VoxelShape POSTER_SHAPE_EAST = VoxelUtils.rotateShape(Direction.NORTH, Direction.EAST, POSTER_SHAPE);
    private static final VoxelShape POSTER_SHAPE_WEST = VoxelUtils.rotateShape(Direction.NORTH, Direction.WEST, POSTER_SHAPE);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = getDirection(state);
        return switch (facing) {
            case SOUTH -> POSTER_SHAPE_SOUTH;
            case EAST -> POSTER_SHAPE_EAST;
            case WEST -> POSTER_SHAPE_WEST;
            default -> POSTER_SHAPE;
        };
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
