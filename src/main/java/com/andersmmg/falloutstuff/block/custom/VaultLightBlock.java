package com.andersmmg.falloutstuff.block.custom;

import org.jetbrains.annotations.Nullable;

import com.andersmmg.falloutstuff.util.VoxelUtils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.util.math.Direction;

public class VaultLightBlock extends FacingBlock {
    public static final BooleanProperty LIT;
    public static final DirectionProperty FACING;

    public VaultLightBlock(Settings settings) {
        super(settings
                .luminance((state) -> state.get(LIT) ? 15 : 0)
                .emissiveLighting((state, world, pos) -> state.get(LIT)));
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(LIT, false));
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        boolean power = hasPower(world, pos);

        if (power) {
            world.setBlockState(pos, state.with(LIT, true));
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos,
            boolean notify) {
        if (!world.isClient) {
            boolean lit = state.get(LIT);

            if (lit != hasPower(world, pos)) {
                if (lit) {
                    world.scheduleBlockTick(pos, this, 4);
                } else {
                    world.setBlockState(pos, state.cycle(LIT), 2);
                }
            }
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(LIT) && !hasPower(world, pos)) {
            world.setBlockState(pos, state.cycle(LIT), 2);
        }
    }

    protected boolean hasPower(World world, BlockPos pos) {
        return world.isReceivingRedstonePower(pos);
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
        return this.getDefaultState().with(FACING, ctx.getSide());
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

    private static final VoxelShape VAULT_LIGHT = VoxelShapes.union(createCuboidShape(5, 5, 14, 11, 11, 16));
    private static final VoxelShape VAULT_LIGHT_SOUTH = VoxelUtils.rotateShape(Direction.NORTH, Direction.SOUTH, VAULT_LIGHT);
    private static final VoxelShape VAULT_LIGHT_EAST = VoxelUtils.rotateShape(Direction.NORTH, Direction.EAST, VAULT_LIGHT);
    private static final VoxelShape VAULT_LIGHT_WEST = VoxelUtils.rotateShape(Direction.NORTH, Direction.WEST, VAULT_LIGHT);
    private static final VoxelShape VAULT_LIGHT_UP = VoxelShapes.union(createCuboidShape(5, 0, 5, 11, 2, 11));
    private static final VoxelShape VAULT_LIGHT_DOWN = VoxelShapes.union(createCuboidShape(5, 14, 5, 11, 16, 11));
    
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = getDirection(state);
        switch (facing) {
            case SOUTH: {
                return VAULT_LIGHT_SOUTH;
            }
            case EAST: {
                return VAULT_LIGHT_EAST;
            }
            case WEST: {
                return VAULT_LIGHT_WEST;
            }
            case UP: {
                return VAULT_LIGHT_UP;
            }
            case DOWN: {
                return VAULT_LIGHT_DOWN;
            }
            default: {
                return VAULT_LIGHT;
            }
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
        builder.add(FACING);
    }

    static {
        FACING = Properties.FACING;
        LIT = Properties.LIT;
    }

}
