package com.andersmmg.falloutstuff.item.custom;

import com.andersmmg.falloutstuff.item.ModItems;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class NukaColaItem extends Item {
    private static final int MAX_USE_TIME = 32;

    public NukaColaItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            // Give the player a bottle cap
            ItemStack itemStack = new ItemStack(ModItems.CAP);
            serverPlayerEntity.giveItemStack(itemStack);
        }
        if (user instanceof PlayerEntity && !((PlayerEntity) user).getAbilities().creativeMode) {
            stack.decrement(1);
        }
        if (!world.isClient) {
            user.clearStatusEffects();
            if (stack.isEmpty()) {
                return new ItemStack(ModItems.NUKA_COLA_BOTTLE);
            } else {
                if (user instanceof ServerPlayerEntity serverPlayerEntity) {
                    Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
                    serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                    serverPlayerEntity.giveItemStack(new ItemStack(ModItems.NUKA_COLA_BOTTLE));
                    user.heal(10);
                }
            }
        }
        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return MAX_USE_TIME;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
