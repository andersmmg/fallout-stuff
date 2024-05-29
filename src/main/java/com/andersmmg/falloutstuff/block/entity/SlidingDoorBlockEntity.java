package com.andersmmg.falloutstuff.block.entity;

import com.andersmmg.falloutstuff.block.custom.SlidingDoorBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class SlidingDoorBlockEntity extends BlockEntity {
    private AnimationStage animationStage;
    private float animationProgress;
    private float prevAnimationProgress;

    public SlidingDoorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SLIDING_DOOR_BLOCK_ENTITY, pos, state);
        this.animationStage = AnimationStage.CLOSED;
    }

    public static void tick(World world, BlockPos pos, BlockState state, SlidingDoorBlockEntity blockEntity) {
        if (world.isClient) {
            blockEntity.updateAnimation(world, pos, state);
        }
    }

    @Environment(EnvType.CLIENT)
    private void updateAnimation(World world, BlockPos pos, BlockState state) {
        this.prevAnimationProgress = this.animationProgress;
        switch (this.animationStage) {
            case CLOSED:
                this.animationProgress = 0.0F;
                if (state.get(SlidingDoorBlock.OPEN)) {
                    this.animationStage = AnimationStage.OPENING;
                }
                break;
            case OPENING:
                this.animationProgress += 0.1F;
                if (this.animationProgress >= 1.0F) {
                    this.animationStage = AnimationStage.OPENED;
                    this.animationProgress = 1.0F;
                } else if (!state.get(SlidingDoorBlock.OPEN)) {
                    this.animationStage = AnimationStage.CLOSING;
                }
                break;
            case CLOSING:
                this.animationProgress -= 0.1F;
                if (this.animationProgress <= 0.0F) {
                    this.animationStage = AnimationStage.CLOSED;
                    this.animationProgress = 0.0F;
                } else if (state.get(SlidingDoorBlock.OPEN)) {
                    this.animationStage = AnimationStage.OPENING;
                }
                break;
            case OPENED:
                this.animationProgress = 1.0F;
                if (!state.get(SlidingDoorBlock.OPEN)) {
                    this.animationStage = AnimationStage.CLOSING;
                }
        }

    }

    public float getAnimationProgress(float delta) {
        return MathHelper.lerp(delta, this.prevAnimationProgress, this.animationProgress);
    }

    public AnimationStage getAnimationStage() {
        return this.animationStage;
    }

    public static enum AnimationStage {
        CLOSED,
        OPENING,
        OPENED,
        CLOSING;

        private AnimationStage() {
        }
    }
}
