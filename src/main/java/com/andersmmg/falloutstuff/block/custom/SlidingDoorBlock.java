package com.andersmmg.falloutstuff.block.custom;

import com.andersmmg.falloutstuff.block.entity.ModBlockEntities;
import com.andersmmg.falloutstuff.block.entity.SlidingDoorBlockEntity;
import com.andersmmg.falloutstuff.sound.ModSounds;
import com.andersmmg.falloutstuff.util.VoxelUtils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class SlidingDoorBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = Properties.OPEN;
    public static final BooleanProperty POWERED = Properties.POWERED;
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    private static final VoxelShape VOXEL_SHAPE = Block.createCuboidShape(0, 0, 6, 16, 16, 10);
    private static final VoxelShape VOXEL_SHAPE_OPEN = Block.createCuboidShape(0, 13, 6, 16, 16, 10);

    public SlidingDoorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(OPEN, false).with(HALF, DoubleBlockHalf.LOWER).with(POWERED, false));
    }

    protected static Direction getDirection(BlockState state) {
        return state.get(FACING);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.SLIDING_DOOR_BLOCK_ENTITY, SlidingDoorBlockEntity::tick);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf doubleBlockHalf = state.get(HALF);
        if (direction.getAxis() == Direction.Axis.Y && doubleBlockHalf == DoubleBlockHalf.LOWER == (direction == Direction.UP)) {
            return neighborState.isOf(this) && neighborState.get(HALF) != doubleBlockHalf ? state.with(FACING, (Direction) neighborState.get(FACING)).with(OPEN, neighborState.get(OPEN)).with(POWERED, neighborState.get(POWERED)) : Blocks.AIR.getDefaultState();
        } else {
            return doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        boolean bl = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(pos.offset(state.get(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
        if (bl != state.get(POWERED)) {
            if (!this.getDefaultState().isOf(sourceBlock) && bl != state.get(OPEN)) {
                this.playOpenCloseSound(world, pos, bl);
                world.emitGameEvent(null, bl ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
                world.setBlockState(pos, state.with(POWERED, bl).with(OPEN, bl), Block.NOTIFY_ALL);
                // Open adjacent doors
                if (world.getBlockState(pos.offset(state.get(FACING).rotateYCounterclockwise())).isOf(this)) {
                    world.setBlockState(pos.offset(state.get(FACING).rotateYCounterclockwise()), world.getBlockState(pos.offset(state.get(FACING).rotateYCounterclockwise())).with(POWERED, bl).with(OPEN, bl), 3);
                }
                if (world.getBlockState(pos.offset(state.get(FACING).rotateYClockwise())).isOf(this)) {
                    world.setBlockState(pos.offset(state.get(FACING).rotateYClockwise()), world.getBlockState(pos.offset(state.get(FACING).rotateYClockwise())).with(POWERED, bl).with(OPEN, bl), 3);
                }
            }
        }

    }

    private void playOpenCloseSound(World world, BlockPos pos, boolean open) {
        world.playSound(null, pos, open ? ModSounds.SLIDING_DOOR_OPEN : ModSounds.SLIDING_DOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (state.get(HALF) == DoubleBlockHalf.LOWER)
            world.setBlockState(pos.up(), state.with(HALF, DoubleBlockHalf.UPPER), 2);
        if (state.get(HALF) == DoubleBlockHalf.UPPER)
            if (!world.getBlockState(pos.down()).isOf(this)) {
                world.removeBlock(pos, false);
            }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(HALF) == DoubleBlockHalf.LOWER) {
            world.breakBlock(pos.up(), false);
        } else {
            world.breakBlock(pos.down(), !player.isCreative());
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return switch (type) {
            case LAND, AIR -> state.get(OPEN);
            default -> false;
        };
    }

    @Override
    @Nullable
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SlidingDoorBlockEntity(pos, state);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(OPEN)) {
            return VoxelUtils.rotateShape(getDirection(state), state.get(HALF) == DoubleBlockHalf.UPPER ? VOXEL_SHAPE_OPEN : VoxelShapes.empty());
        }
        return VoxelUtils.rotateShape(getDirection(state), VOXEL_SHAPE);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, HALF, POWERED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        if (blockPos.getY() < world.getTopY() - 1 && world.getBlockState(blockPos.up()).canReplace(ctx)) {
            boolean bl = world.isReceivingRedstonePower(blockPos) || world.isReceivingRedstonePower(blockPos.up());
            return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing()).with(POWERED, bl).with(OPEN, bl).with(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }
}
