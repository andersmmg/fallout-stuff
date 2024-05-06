package com.andersmmg.falloutstuff.item.custom;

import com.andersmmg.falloutstuff.FalloutStuff;
import com.andersmmg.falloutstuff.sound.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class StimpakItem extends Item {

    public StimpakItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            return TypedActionResult.success(user.getStackInHand(hand));
        }
        ItemStack itemStack = user.getStackInHand(hand);

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        FalloutStuff.LOGGER.info(FalloutStuff.CONFIG.stimpakHealAmt() + " heal amount");

        if (user.getHealth() < user.getMaxHealth()) {
            user.heal(FalloutStuff.CONFIG.stimpakHealAmt());
            world.playSound(user, user.getBlockPos(), ModSounds.STIMPAK_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

            if (!user.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

}
