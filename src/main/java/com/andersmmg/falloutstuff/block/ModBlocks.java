package com.andersmmg.falloutstuff.block;

import com.andersmmg.falloutstuff.FalloutStuff;
import com.andersmmg.falloutstuff.block.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block VAULT_LIGHT_BLOCK = registerBlock("vault_light",
            new VaultLightBlock(FabricBlockSettings.copyOf(Blocks.GLASS).nonOpaque()));
    public static final Block LARGE_VAULT_LIGHT_BLOCK = registerBlock("large_vault_light",
            new LargeVaultLightBlock(FabricBlockSettings.copyOf(Blocks.GLASS).nonOpaque()));

    public static final Block BOX_BLOCK = registerBlockOnly("box",
            new BoxBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(0.8f).sounds(BlockSoundGroup.WOOL).nonOpaque()));
    public static final Block LARGE_BOX_BLOCK = registerBlockOnly("large_box",
            new LargeBoxBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(0.8f).sounds(BlockSoundGroup.WOOL).nonOpaque()));
    public static final Block VAULT_CRATE_BLOCK = registerBlock("vault_crate",
            new VaultCrateBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));
    public static final Block FILE_CABINET_BLOCK = registerBlock("file_cabinet",
            new FileCabinetBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));
    public static final Block FILE_CABINET_DARK_BLOCK = registerBlock("file_cabinet_dark",
            new FileCabinetBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));
    public static final Block NUKA_VENDING_BLOCK = registerBlock("nuka_vending_machine",
            new NukaVendingBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    public static final Block VAULT_SIGN_BLOCK = registerBlock("vault_sign",
            new VaultSignBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    public static final Block DANGER_IRON_BLOCK = registerBlock("danger_iron",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block DANGER_STRIPES_BLOCK = registerBlock("danger_stripes",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    public static final Block ALARM_BLOCK = registerBlock("alarm_block",
            new AlarmBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));

    public static final Block POWER_ARMOR_HELMET_BLOCK = registerBlockOnly("power_armor_helmet",
            new PowerArmorHelmetBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.METAL).nonOpaque()));

    public static final Block RED_BUTTON = registerBlock("red_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_BUTTON), BlockSetType.IRON, 10, false));
    public static final Block PLAIN_DARK_WOOD_DOOR = registerBlock("plain_dark_wood_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.DARK_OAK_DOOR), BlockSetType.DARK_OAK));

    public static final Block VAULT_POSTER_BLOCK_1 = registerBlock("vault_poster_1",
            new VaultPosterBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON).nonOpaque()));
    public static final Block VAULT_POSTER_BLOCK_2 = registerBlock("vault_poster_2",
            new VaultPosterBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON).nonOpaque()));
    public static final Block VAULT_POSTER_BLOCK_3 = registerBlock("vault_poster_3",
            new VaultPosterBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON).nonOpaque()));
    public static final Block VAULT_POSTER_BLOCK_4 = registerBlock("vault_poster_4",
            new VaultPosterBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON).nonOpaque()));
    public static final Block VAULT_POSTER_BLOCK_5 = registerBlock("vault_poster_5",
            new VaultPosterBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON).nonOpaque()));
    public static final Block MR_PEBBLES_POSTER_BLOCK = registerBlock("mr_pebbles_poster",
            new VaultPosterBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON).nonOpaque()));
    public static final Block NUKA_GIRL_POSTER_BLOCK = registerBlock("nuka_girl_poster",
            new VaultPosterBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON).nonOpaque()));

    public static final Block VAULT_BOY_STATUE = registerBlock("vault_boy_statue",
            new StatueBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON).nonOpaque()));
    public static final Block CRAM_BLOCK = registerBlockOnly("cram",
            new CramBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON).nonOpaque()));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(FalloutStuff.MOD_ID, name), block);
    }

    private static Block registerBlockOnly(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(FalloutStuff.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(FalloutStuff.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        FalloutStuff.LOGGER.info("Registering ModBlocks for " + FalloutStuff.MOD_ID);
    }
}