package com.andersmmg.falloutstuff.block.custom;

import com.andersmmg.falloutstuff.block.entity.VaultSignBlockEntity;
import com.andersmmg.falloutstuff.client.screen.VaultSignScreen;
import com.andersmmg.falloutstuff.util.VoxelUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class VaultSignBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<BlockHalf> HALF = Properties.BLOCK_HALF;

    public VaultSignBlock(AbstractBlock.Settings settings) {
        super(settings.luminance(state -> 5));
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(HALF, BlockHalf.TOP));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof VaultSignBlockEntity blockEntity1) {
            if (world.isClient) {
                VaultSignScreen screen = new VaultSignScreen(blockEntity1);
                MinecraftClient.getInstance().setScreen(screen);
            }

        }
        return ActionResult.CONSUME;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            return;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof VaultSignBlockEntity) {
            world.updateComparators(pos, state.getBlock());
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof VaultSignBlockEntity) {
            ((VaultSignBlockEntity) blockEntity).tick();
        }
    }

    @Override
    @Nullable
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new VaultSignBlockEntity(pos, state);
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

    private static final VoxelShape VOXEL_SHAPE = Block.createCuboidShape(1, 11.5, 13.75, 15, 16, 16.5);
    private static final VoxelShape VOXEL_SHAPE_DOWN = Block.createCuboidShape(1, 0.5, 13.75, 15, 5, 16.5);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelUtils.rotateShape(getDirection(state), state.get(HALF) == BlockHalf.TOP ? VOXEL_SHAPE : VOXEL_SHAPE_DOWN);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {

        Direction direction = Direction.DOWN;
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isSideSolidFullSquare(world, blockPos, direction)) {
            return true;
        }
        direction = state.get(FACING);
        blockPos = pos.offset(direction.getOpposite());
        blockState = world.getBlockState(blockPos);
        return blockState.isSideSolidFullSquare(world, blockPos, direction);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();
        BlockPos blockPos = ctx.getBlockPos();
        BlockHalf blockHalf;
        if (direction == Direction.DOWN) {
            blockHalf = BlockHalf.TOP;
        } else if ((ctx.getHitPos().y - (double) blockPos.getY() > 0.5)) {
            blockHalf = BlockHalf.TOP;
        } else {
            blockHalf = BlockHalf.BOTTOM;
        }
//        return direction != Direction.DOWN && (direction == Direction.UP || !(ctx.getHitPos().y - (double)blockPos.getY() > 0.5)) ? blockState2 : (BlockState)blockState2.with(TYPE, SlabType.TOP);
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(HALF, blockHalf);
    }
}