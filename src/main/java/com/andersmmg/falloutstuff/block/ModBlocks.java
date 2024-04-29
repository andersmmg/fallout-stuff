package com.andersmmg.falloutstuff.block;

import com.andersmmg.falloutstuff.FalloutStuff;
import com.andersmmg.falloutstuff.block.custom.BoxBlock;
import com.andersmmg.falloutstuff.block.custom.FileCabinetBlock;
import com.andersmmg.falloutstuff.block.custom.VaultCrateBlock;
import com.andersmmg.falloutstuff.block.custom.VaultLightBlock;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block VAULT_LIGHT_BLOCK = registerBlock("vault_light",
            new VaultLightBlock(FabricBlockSettings.copyOf(Blocks.GLASS).nonOpaque()));

    public static final Block BOX_BLOCK = registerBlock("box",
            new BoxBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).nonOpaque()));
    public static final Block VAULT_CRATE_BLOCK = registerBlock("vault_crate",
            new VaultCrateBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));
    public static final Block FILE_CABINET_BLOCK = registerBlock("file_cabinet",
            new FileCabinetBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    public static final Block DANGER_IRON_BLOCK = registerBlock("danger_iron",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block DANGER_STRIPES_BLOCK = registerBlock("danger_stripes",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    public static final Block RED_BUTTON = registerBlock("red_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_BUTTON), BlockSetType.IRON, 10, false));
    public static final Block PLAIN_DARK_WOOD_DOOR = registerBlock("plain_dark_wood_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.DARK_OAK_DOOR), BlockSetType.DARK_OAK));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
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