package com.andersmmg.falloutstuff.block.custom;

import com.andersmmg.falloutstuff.util.VoxelUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class CramBlock extends AbstractFloorFacingBlock {

    public CramBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    private static final VoxelShape VOXEL_SHAPE = Block.createCuboidShape(4, 0, 5, 12, 7, 10);
    private static final VoxelShape VOXEL_SHAPE_SOUTH = VoxelUtils.rotateShape(Direction.NORTH, Direction.SOUTH, VOXEL_SHAPE);
    private static final VoxelShape VOXEL_SHAPE_EAST = VoxelUtils.rotateShape(Direction.NORTH, Direction.EAST, VOXEL_SHAPE);
    private static final VoxelShape VOXEL_SHAPE_WEST = VoxelUtils.rotateShape(Direction.NORTH, Direction.WEST, VOXEL_SHAPE);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = getDirection(state);
        return switch (facing) {
            case SOUTH -> VOXEL_SHAPE_SOUTH;
            case EAST -> VOXEL_SHAPE_EAST;
            case WEST -> VOXEL_SHAPE_WEST;
            default -> VOXEL_SHAPE;
        };
    }
}