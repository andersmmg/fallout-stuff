package com.andersmmg.falloutstuff.item;

import com.andersmmg.falloutstuff.FalloutStuff;
import com.andersmmg.falloutstuff.block.ModBlocks;
import com.andersmmg.falloutstuff.item.custom.NukaColaItem;
import com.andersmmg.falloutstuff.item.custom.StimpakItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CAP = registerItem("cap", new Item(new FabricItemSettings()));
    public static final Item SNACK_CAKES = registerItem("snack_cakes", new Item(new FabricItemSettings().food(ModFoodComponents.SNACK_CAKES)));
    public static final Item STIMPAK = registerItem("stimpak", new StimpakItem(new FabricItemSettings()));
    public static final Item NUKA_COLA = registerItem("nuka_cola", new NukaColaItem(new FabricItemSettings().recipeRemainder(ModItems.NUKA_COLA_BOTTLE).food(ModFoodComponents.NUKA_COLA).maxCount(16)));
    public static final Item NUKA_COLA_BOTTLE = registerItem("nuka_cola_bottle", new Item(new FabricItemSettings()));

    public static final Item BOX_BLOCK = Items.register(new BlockItem(ModBlocks.BOX_BLOCK, new Item.Settings().maxCount(1)));
    public static final Item CRAM_ITEM = Items.register(new BlockItem(ModBlocks.CRAM_BLOCK, new Item.Settings().food(ModFoodComponents.CRAM).maxCount(16)));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(CAP);
    }

    private static void addItemsToFoodItemGroup(FabricItemGroupEntries entries) {
        entries.add(SNACK_CAKES);
        entries.add(NUKA_COLA);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(FalloutStuff.MOD_ID, name), item);
    }

    public static void registerModItems() {
        FalloutStuff.LOGGER.info("Registering Mod Items for " + FalloutStuff.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemsToFoodItemGroup);
    }
}
