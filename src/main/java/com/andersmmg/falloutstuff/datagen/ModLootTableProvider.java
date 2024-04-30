package com.andersmmg.falloutstuff.datagen;

import com.andersmmg.falloutstuff.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
         addDrop(ModBlocks.DANGER_IRON_BLOCK);
         addDrop(ModBlocks.DANGER_STRIPES_BLOCK);
         addDrop(ModBlocks.VAULT_LIGHT_BLOCK);
         addDrop(ModBlocks.VAULT_CRATE_BLOCK);
         addDrop(ModBlocks.BOX_BLOCK);
         addDrop(ModBlocks.RED_BUTTON);
         addDrop(ModBlocks.PLAIN_DARK_WOOD_DOOR, doorDrops(ModBlocks.PLAIN_DARK_WOOD_DOOR));

         addDrop(ModBlocks.VAULT_POSTER_BLOCK_1);
         addDrop(ModBlocks.VAULT_POSTER_BLOCK_2);
         addDrop(ModBlocks.VAULT_POSTER_BLOCK_3);
         addDrop(ModBlocks.VAULT_POSTER_BLOCK_4);
    }

}