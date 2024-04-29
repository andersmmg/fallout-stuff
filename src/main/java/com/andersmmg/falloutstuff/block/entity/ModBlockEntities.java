package com.andersmmg.falloutstuff.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import com.andersmmg.falloutstuff.FalloutStuff;
import com.andersmmg.falloutstuff.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<BoxBlockEntity> BOX_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(FalloutStuff.MOD_ID, "box_be"),
                    FabricBlockEntityTypeBuilder.create(BoxBlockEntity::new,
                            ModBlocks.BOX_BLOCK).build());
    public static final BlockEntityType<VaultCrateBlockEntity> VAULT_CRATE_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(FalloutStuff.MOD_ID, "vault_crate_be"),
                    FabricBlockEntityTypeBuilder.create(VaultCrateBlockEntity::new,
                            ModBlocks.VAULT_CRATE_BLOCK).build());
    public static final BlockEntityType<FileCabinetBlockEntity> FILE_CABINET_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(FalloutStuff.MOD_ID, "file_cabinet_be"),
                    FabricBlockEntityTypeBuilder.create(FileCabinetBlockEntity::new,
                            ModBlocks.FILE_CABINET_BLOCK).build());

    public static void registerBlockEntities() {
        FalloutStuff.LOGGER.info("Registering Block Entities for " + FalloutStuff.MOD_ID);
    }
}
