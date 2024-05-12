package com.andersmmg.falloutstuff.datagen;

import com.andersmmg.falloutstuff.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {


        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.FILE_CABINET_BLOCK)
                .add(ModBlocks.FILE_CABINET_DARK_BLOCK)
                .add(ModBlocks.VAULT_CRATE_BLOCK)
                .add(ModBlocks.DANGER_IRON_BLOCK)
                .add(ModBlocks.VAULT_SIGN_BLOCK)
                .add(ModBlocks.NUKA_VENDING_BLOCK)
                .add(ModBlocks.DANGER_STRIPES_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.FILE_CABINET_BLOCK)
                .add(ModBlocks.FILE_CABINET_DARK_BLOCK)
                .add(ModBlocks.VAULT_CRATE_BLOCK)
                .add(ModBlocks.DANGER_IRON_BLOCK)
                .add(ModBlocks.VAULT_SIGN_BLOCK)
                .add(ModBlocks.NUKA_VENDING_BLOCK)
                .add(ModBlocks.DANGER_STRIPES_BLOCK);
    }
}