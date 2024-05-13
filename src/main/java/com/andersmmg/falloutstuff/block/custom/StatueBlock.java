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

public class StatueBlock extends AbstractFloorFacingBlock {

    private static final VoxelShape VOXEL_SHAPE = Block.createCuboidShape(5, 0, 5, 11, 10, 11);

    public StatueBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelUtils.rotateShape(getDirection(state), VOXEL_SHAPE);
    }
}