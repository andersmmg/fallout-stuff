package com.andersmmg.falloutstuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

// import com.andersmmg.falloutstuff.block.ModBlocks;
import com.andersmmg.falloutstuff.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.VAULT_LIGHT_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.SNACK_CAKES, Models.GENERATED);
        itemModelGenerator.register(ModItems.STIMPAK, Models.GENERATED);
        itemModelGenerator.register(ModItems.NUKA_COLA, Models.GENERATED);
        itemModelGenerator.register(ModItems.NUKA_COLA_BOTTLE, Models.GENERATED);
    }
}