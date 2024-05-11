package com.andersmmg.falloutstuff.block.custom;

import com.andersmmg.falloutstuff.util.VoxelUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class PowerArmorHelmetBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public PowerArmorHelmetBlock(Settings settings) {
        super(settings);
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

    private static final VoxelShape HELMET_SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);
    private static final VoxelShape HELMET_SHAPE_SOUTH = VoxelUtils.rotateShape(Direction.NORTH, Direction.SOUTH, HELMET_SHAPE);
    private static final VoxelShape HELMET_SHAPE_EAST = VoxelUtils.rotateShape(Direction.NORTH, Direction.EAST, HELMET_SHAPE);
    private static final VoxelShape HELMET_SHAPE_WEST = VoxelUtils.rotateShape(Direction.NORTH, Direction.WEST, HELMET_SHAPE);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = getDirection(state);
        return switch (facing) {
            case SOUTH -> HELMET_SHAPE_SOUTH;
            case EAST -> HELMET_SHAPE_EAST;
            case WEST -> HELMET_SHAPE_WEST;
            default -> HELMET_SHAPE;
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
