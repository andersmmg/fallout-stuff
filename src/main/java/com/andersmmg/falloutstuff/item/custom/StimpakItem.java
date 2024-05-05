package com.andersmmg.falloutstuff.item.custom;

import com.andersmmg.falloutstuff.config.ModConfig;
import com.andersmmg.falloutstuff.sound.ModSounds;
import me.shedaniel.autoconfig.AutoConfig;
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
        ItemStack itemStack = user.getStackInHand(hand);
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        user.incrementStat(Stats.USED.getOrCreateStat(this));

        if (user.getHealth() < user.getMaxHealth()) {
            user.heal(config.stimpakHealAmt);
            world.playSound(user, user.getBlockPos(), ModSounds.STIMPAK_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

            if (!user.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

}
