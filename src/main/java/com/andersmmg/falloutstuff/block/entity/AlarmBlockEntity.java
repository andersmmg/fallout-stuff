package com.andersmmg.falloutstuff.block.entity;

import com.andersmmg.falloutstuff.block.custom.AlarmBlock;
import com.andersmmg.falloutstuff.sound.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AlarmBlockEntity extends BlockEntity {
    private static final int SOUND_LENGTH = 35;
    private long nextSoundTime = -1;

    public AlarmBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALARM_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, AlarmBlockEntity blockEntity) {
        if (world.isClient) {
            return;
        }

        boolean isPowered = state.get(AlarmBlock.POWERED);
        long currentTime = world.getTime();

        if (isPowered && blockEntity.nextSoundTime != -1 && currentTime >= blockEntity.nextSoundTime) {
            blockEntity.playSound();
            blockEntity.scheduleNextSound(currentTime);
        } else if (isPowered && blockEntity.nextSoundTime == -1) {
            blockEntity.playSound();
            blockEntity.scheduleNextSound(currentTime);
        } else if (!isPowered && blockEntity.nextSoundTime != -1) {
            blockEntity.nextSoundTime = -1;
        }
    }

    private void scheduleNextSound(long currentTime) {
        nextSoundTime = currentTime + SOUND_LENGTH;
    }

    private void playSound() {
        world.playSound(null, pos, ModSounds.ALARM, SoundCategory.BLOCKS, 1f, 1f);
    }
}
