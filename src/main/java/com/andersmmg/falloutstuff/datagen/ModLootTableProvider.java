package com.andersmmg.falloutstuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
// import com.andersmmg.falloutstuff.block.ModBlocks;
// import com.andersmmg.falloutstuff.item.ModItems;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        // addDrop(ModBlocks.RUBY_BLOCK);
    }

}