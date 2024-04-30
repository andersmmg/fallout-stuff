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
                    .icon(() -> new ItemStack(ModItems.CAP)).entries((displayContext, entries) -> {
                        entries.add(ModItems.CAP);
                        entries.add(ModItems.SNACK_CAKES);
                        entries.add(ModItems.STIMPAK);
                        entries.add(ModItems.NUKA_COLA);
                        entries.add(ModItems.NUKA_COLA_BOTTLE);

                        entries.add(ModBlocks.VAULT_LIGHT_BLOCK);
                        entries.add(ModBlocks.VAULT_CRATE_BLOCK);
                        entries.add(ModBlocks.BOX_BLOCK);
                        entries.add(ModBlocks.DANGER_IRON_BLOCK);
                        entries.add(ModBlocks.DANGER_STRIPES_BLOCK);
                        entries.add(ModBlocks.RED_BUTTON);
                        entries.add(ModBlocks.PLAIN_DARK_WOOD_DOOR);
                        entries.add(ModBlocks.FILE_CABINET_BLOCK);

                        entries.add(ModBlocks.VAULT_POSTER_BLOCK_1);
                        entries.add(ModBlocks.VAULT_POSTER_BLOCK_2);
                        entries.add(ModBlocks.VAULT_POSTER_BLOCK_3);
                        entries.add(ModBlocks.VAULT_POSTER_BLOCK_4);
                    }).build());

    public static void registerItemGroups() {
        FalloutStuff.LOGGER.info("Registering Mod Item Groups for " + FalloutStuff.MOD_ID);
    }
}
