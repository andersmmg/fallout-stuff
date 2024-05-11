package com.andersmmg.falloutstuff.datagen.custom;

import com.andersmmg.falloutstuff.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class PowerArmorModelGenerator extends FabricModelProvider {
    public PowerArmorModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(ModBlocks.POWER_ARMOR_HELMET_BLOCK)
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.NORTH),
                        BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R0)));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
