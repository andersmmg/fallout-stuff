package com.andersmmg.falloutstuff.item;

import com.andersmmg.falloutstuff.FalloutStuff;
import com.andersmmg.falloutstuff.block.ModBlocks;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup FALLOUT_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(FalloutStuff.MOD_ID, "fallout_stuff"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.fallout_stuff"))
                    .icon(() -> new ItemStack(ModItems.CAP_ITEM)).entries((displayContext, entries) -> {
                        entries.add(ModItems.CAP_ITEM);
                        entries.add(ModItems.SNACK_CAKES_ITEM);
                        entries.add(ModItems.STIMPAK_ITEM);

                        entries.add(ModBlocks.VAULT_LIGHT_BLOCK);
                    }).build());

    public static void registerItemGroups() {
        FalloutStuff.LOGGER.info("Registering Mod Item Groups for " + FalloutStuff.MOD_ID);
    }
}
