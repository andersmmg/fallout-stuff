package com.andersmmg.falloutstuff.datagen;

import com.andersmmg.falloutstuff.block.ModBlocks;
import com.andersmmg.falloutstuff.block.custom.BoxBlock;
import com.andersmmg.falloutstuff.block.entity.ModBlockEntities;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.DynamicEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.CopyNameLootFunction;
import net.minecraft.loot.function.CopyNbtLootFunction;
import net.minecraft.loot.function.SetContentsLootFunction;
import net.minecraft.loot.provider.nbt.ContextLootNbtProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.DANGER_IRON_BLOCK);
        addDrop(ModBlocks.DANGER_STRIPES_BLOCK);
        addDrop(ModBlocks.VAULT_LIGHT_BLOCK);
        addDrop(ModBlocks.LARGE_VAULT_LIGHT_BLOCK);

        addDrop(ModBlocks.VAULT_CRATE_BLOCK);
        addDrop(ModBlocks.BOX_BLOCK, boxBlockDrops(ModBlocks.BOX_BLOCK));
        addDrop(ModBlocks.LARGE_BOX_BLOCK, boxBlockDrops(ModBlocks.LARGE_BOX_BLOCK));
        addDrop(ModBlocks.FILE_CABINET_BLOCK);
        addDrop(ModBlocks.FILE_CABINET_DARK_BLOCK);
        addDrop(ModBlocks.NUKA_VENDING_BLOCK);
        addDrop(ModBlocks.CASH_REGISTER);
        addDrop(ModBlocks.POWER_ARMOR_HELMET_BLOCK);
        addDrop(ModBlocks.ALARM_BLOCK);
        addDrop(ModBlocks.VAULT_SIGN_BLOCK);
        addDrop(ModBlocks.CRAM_BLOCK);

        addDrop(ModBlocks.RED_BUTTON);
        addDrop(ModBlocks.PLAIN_DARK_WOOD_DOOR, doorDrops(ModBlocks.PLAIN_DARK_WOOD_DOOR));

        addDrop(ModBlocks.VAULT_POSTER_BLOCK_1);
        addDrop(ModBlocks.VAULT_POSTER_BLOCK_2);
        addDrop(ModBlocks.VAULT_POSTER_BLOCK_3);
        addDrop(ModBlocks.VAULT_POSTER_BLOCK_4);
        addDrop(ModBlocks.VAULT_POSTER_BLOCK_5);
        addDrop(ModBlocks.MR_PEBBLES_POSTER_BLOCK);
        addDrop(ModBlocks.NUKA_GIRL_POSTER_BLOCK);
        addDrop(ModBlocks.VAULT_BOY_STATUE);
    }

    public LootTable.Builder boxBlockDrops(Block drop) {
        return LootTable.builder().pool(this.addSurvivesExplosionCondition(drop, LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(((LeafEntry.Builder<?>) ((LeafEntry.Builder<?>) ItemEntry.builder(drop).apply(CopyNameLootFunction.builder(CopyNameLootFunction.Source.BLOCK_ENTITY))).apply(CopyNbtLootFunction.builder(ContextLootNbtProvider.BLOCK_ENTITY).withOperation("Lock", "BlockEntityTag.Lock").withOperation("LootTable", "BlockEntityTag.LootTable").withOperation("LootTableSeed", "BlockEntityTag.LootTableSeed"))).apply(SetContentsLootFunction.builder(ModBlockEntities.BOX_ENTITY).withEntry(DynamicEntry.builder(BoxBlock.CONTENTS_DYNAMIC_DROP_ID))))));
    }

}