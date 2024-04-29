package com.andersmmg.falloutstuff.datagen;

import com.andersmmg.falloutstuff.FalloutStuff;
import com.andersmmg.falloutstuff.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

// import com.andersmmg.falloutstuff.block.ModBlocks;
import com.andersmmg.falloutstuff.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DANGER_IRON_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DANGER_STRIPES_BLOCK);

        blockStateModelGenerator.registerDoor(ModBlocks.PLAIN_DARK_WOOD_DOOR);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
//        itemModelGenerator.register(ModItems.CAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.SNACK_CAKES, Models.GENERATED);
        itemModelGenerator.register(ModItems.STIMPAK, Models.GENERATED);
        itemModelGenerator.register(ModItems.NUKA_COLA, Models.GENERATED);
        itemModelGenerator.register(ModItems.NUKA_COLA_BOTTLE, Models.GENERATED);

    }
}