package com.andersmmg.falloutstuff.item;

import com.andersmmg.falloutstuff.FalloutStuff;
import com.andersmmg.falloutstuff.block.ModBlocks;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;

public class ModItemGroups {
    public static final OwoItemGroup FALLOUT_GROUP = OwoItemGroup
            .builder(FalloutStuff.id("fallout_stuff"), () -> Icon.of(ModItems.CAP))

            .build();

    public static void registerItemGroups() {
        FalloutStuff.LOGGER.info("Registering Mod Item Groups for " + FalloutStuff.MOD_ID);
        FALLOUT_GROUP.addCustomTab(Icon.of(ModItems.CAP), "general", (displayContext, entries) -> {
            entries.add(ModItems.CAP);
            entries.add(ModItems.STIMPAK);
            entries.add(ModItems.POWER_ARMOR_HELMET);
//            entries.add(ModItems.POWER_ARMOR_CHESTPLATE);
//            entries.add(ModItems.POWER_ARMOR_LEGGINGS);
//            entries.add(ModItems.POWER_ARMOR_BOOTS);

            FalloutStuff.LOGGER.info(FALLOUT_GROUP.getSearchTabStacks() + "");
        }, false);
        FALLOUT_GROUP.addCustomTab(Icon.of(ModBlocks.VAULT_POSTER_BLOCK_1), "decoration", (displayContext, entries) -> {
            entries.add(ModBlocks.VAULT_POSTER_BLOCK_1);
            entries.add(ModBlocks.VAULT_POSTER_BLOCK_2);
            entries.add(ModBlocks.VAULT_POSTER_BLOCK_3);
            entries.add(ModBlocks.VAULT_POSTER_BLOCK_4);
            entries.add(ModBlocks.VAULT_POSTER_BLOCK_5);
            entries.add(ModBlocks.MR_PEBBLES_POSTER_BLOCK);
            entries.add(ModBlocks.NUKA_GIRL_POSTER_BLOCK);
            entries.add(ModBlocks.VAULT_BOY_STATUE);
            entries.add(ModBlocks.DANGER_IRON_BLOCK);
            entries.add(ModBlocks.DANGER_STRIPES_BLOCK);
            entries.add(ModBlocks.PLAIN_DARK_WOOD_DOOR);
            entries.add(ModBlocks.VAULT_SIGN_BLOCK);
        }, false);
        FALLOUT_GROUP.addCustomTab(Icon.of(ModItems.NUKA_COLA), "food", (displayContext, entries) -> {
            entries.add(ModItems.SNACK_CAKES);
            entries.add(ModItems.NUKA_COLA);
            entries.add(ModItems.NUKA_COLA_BOTTLE);
            entries.add(ModItems.CRAM_ITEM);
        }, false);
        FALLOUT_GROUP.addCustomTab(Icon.of(ModBlocks.FILE_CABINET_BLOCK), "functional", (displayContext, entries) -> {
            entries.add(ModBlocks.FILE_CABINET_BLOCK);
            entries.add(ModBlocks.FILE_CABINET_DARK_BLOCK);
            entries.add(ModBlocks.VAULT_CRATE_BLOCK);
            entries.add(ModBlocks.BOX_BLOCK);
            entries.add(ModBlocks.LARGE_BOX_BLOCK);
            entries.add(ModBlocks.NUKA_VENDING_BLOCK);
            entries.add(ModBlocks.CASH_REGISTER);

            entries.add(ModBlocks.ALARM_BLOCK);
            entries.add(ModBlocks.RED_BUTTON);
            entries.add(ModBlocks.VAULT_LIGHT_BLOCK);
            entries.add(ModBlocks.LARGE_VAULT_LIGHT_BLOCK);
        }, false);
        FALLOUT_GROUP.initialize();
    }
}
