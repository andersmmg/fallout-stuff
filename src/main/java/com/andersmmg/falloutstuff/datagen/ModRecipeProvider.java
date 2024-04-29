package com.andersmmg.falloutstuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
// import com.andersmmg.falloutstuff.block.ModBlocks;
// import com.andersmmg.falloutstuff.item.ModItems;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        
    }
}