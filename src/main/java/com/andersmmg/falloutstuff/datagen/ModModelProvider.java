package com.andersmmg.falloutstuff.datagen;

import com.andersmmg.falloutstuff.block.ModBlocks;
import com.andersmmg.falloutstuff.block.custom.CramBlock;
import com.andersmmg.falloutstuff.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DANGER_IRON_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DANGER_STRIPES_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ALARM_BLOCK);

        blockStateModelGenerator.registerDoor(ModBlocks.PLAIN_DARK_WOOD_DOOR);

        registerOpenableBlock(blockStateModelGenerator, ModBlocks.CASH_REGISTER);
        registerOpenableBlock(blockStateModelGenerator, ModBlocks.BOX_BLOCK);
        registerOpenableBlock(blockStateModelGenerator, ModBlocks.LARGE_BOX_BLOCK);
        registerOpenableBlock(blockStateModelGenerator, ModBlocks.FILE_CABINET_BLOCK);
        registerOpenableBlock(blockStateModelGenerator, ModBlocks.FILE_CABINET_DARK_BLOCK);
        registerOpenableBlock(blockStateModelGenerator, ModBlocks.VAULT_CRATE_BLOCK);
        registerOpenableBlock(blockStateModelGenerator, ModBlocks.NUKA_VENDING_BLOCK);

        registerHorizontalRotated(blockStateModelGenerator, ModBlocks.VAULT_SIGN_BLOCK);
        registerHorizontalRotated(blockStateModelGenerator, ModBlocks.VAULT_BOY_STATUE);
        registerHorizontalRotated(blockStateModelGenerator, ModBlocks.POWER_ARMOR_HELMET_BLOCK);

        registerHorizontalRotated(blockStateModelGenerator, ModBlocks.NUKA_GIRL_POSTER_BLOCK);
        registerHorizontalRotated(blockStateModelGenerator, ModBlocks.MR_PEBBLES_POSTER_BLOCK);
        registerHorizontalRotated(blockStateModelGenerator, ModBlocks.VAULT_POSTER_BLOCK_1);
        registerHorizontalRotated(blockStateModelGenerator, ModBlocks.VAULT_POSTER_BLOCK_2);
        registerHorizontalRotated(blockStateModelGenerator, ModBlocks.VAULT_POSTER_BLOCK_3);
        registerHorizontalRotated(blockStateModelGenerator, ModBlocks.VAULT_POSTER_BLOCK_4);
        registerHorizontalRotated(blockStateModelGenerator, ModBlocks.VAULT_POSTER_BLOCK_5);

        registerCram(blockStateModelGenerator, ModBlocks.CRAM_BLOCK);
        registerRotatableLight(blockStateModelGenerator, ModBlocks.VAULT_LIGHT_BLOCK);
        registerRotatableLight(blockStateModelGenerator, ModBlocks.LARGE_VAULT_LIGHT_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.SNACK_CAKES, Models.GENERATED);
        itemModelGenerator.register(ModItems.STIMPAK, Models.GENERATED);
        itemModelGenerator.register(ModItems.NUKA_COLA, Models.GENERATED);
        itemModelGenerator.register(ModItems.NUKA_COLA_BOTTLE, Models.GENERATED);
    }

    private void registerOpenableBlock(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        Identifier model_base = ModelIds.getBlockModelId(block);
        Identifier model_open = ModelIds.getBlockSubModelId(block, "_open");
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.OPEN, model_open, model_base)));
    }

    private void registerHorizontalRotated(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        Identifier model_base = ModelIds.getBlockModelId(block);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, model_base))
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
    }

    private void registerCram(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        Identifier model_one = ModelIds.getBlockModelId(block);
        Identifier model_two = ModelIds.getBlockSubModelId(block, "_2");
        Identifier model_three = ModelIds.getBlockSubModelId(block, "_3");
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
                .coordinate(createCramModelMap(CramBlock.COUNT, model_one, model_two, model_three)));
    }

    public static BlockStateVariantMap createCramModelMap(IntProperty property, Identifier firstModel, Identifier secondModel, Identifier thirdModel) {
        return BlockStateVariantMap.create(property)
                .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, firstModel))
                .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, secondModel))
                .register(3, BlockStateVariant.create().put(VariantSettings.MODEL, thirdModel));
    }

    private void registerRotatableLight(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        Identifier model_base = ModelIds.getBlockModelId(block);
        Identifier model_open = ModelIds.getBlockSubModelId(block, "_on");
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateModelGenerator.createNorthDefaultRotationStates())
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.LIT, model_open, model_base)));
    }
}