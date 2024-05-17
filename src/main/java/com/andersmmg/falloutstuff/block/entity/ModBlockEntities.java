package com.andersmmg.falloutstuff.block.entity;

import com.andersmmg.falloutstuff.FalloutStuff;
import com.andersmmg.falloutstuff.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<BoxBlockEntity> BOX_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(FalloutStuff.MOD_ID, "box_be"),
                    FabricBlockEntityTypeBuilder.create(BoxBlockEntity::new,
                            ModBlocks.BOX_BLOCK, ModBlocks.LARGE_BOX_BLOCK).build());
    public static final BlockEntityType<VaultCrateBlockEntity> VAULT_CRATE_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(FalloutStuff.MOD_ID, "vault_crate_be"),
                    FabricBlockEntityTypeBuilder.create(VaultCrateBlockEntity::new,
                            ModBlocks.VAULT_CRATE_BLOCK).build());
    public static final BlockEntityType<FileCabinetBlockEntity> FILE_CABINET_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(FalloutStuff.MOD_ID, "file_cabinet_be"),
                    FabricBlockEntityTypeBuilder.create(FileCabinetBlockEntity::new,
                            ModBlocks.FILE_CABINET_BLOCK, ModBlocks.FILE_CABINET_DARK_BLOCK).build());
    public static final BlockEntityType<NukaVendingBlockEntity> NUKA_VENDING_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(FalloutStuff.MOD_ID, "nuka_vending_be"),
                    FabricBlockEntityTypeBuilder.create(NukaVendingBlockEntity::new,
                            ModBlocks.NUKA_VENDING_BLOCK).build());
    public static final BlockEntityType<CashRegisterBlockEntity> CASH_REGISTER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(FalloutStuff.MOD_ID, "cash_register_be"),
                    FabricBlockEntityTypeBuilder.create(CashRegisterBlockEntity::new,
                            ModBlocks.CASH_REGISTER).build());
    public static final BlockEntityType<AlarmBlockEntity> ALARM_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(FalloutStuff.MOD_ID, "alarm_be"),
                    FabricBlockEntityTypeBuilder.create(AlarmBlockEntity::new,
                            ModBlocks.ALARM_BLOCK).build());
    public static final BlockEntityType<VaultSignBlockEntity> VAULT_SIGN_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(FalloutStuff.MOD_ID, "vault_sign_be"),
                    FabricBlockEntityTypeBuilder.create(VaultSignBlockEntity::new,
                            ModBlocks.VAULT_SIGN_BLOCK).build());
    public static final BlockEntityType<ToolboxBlockEntity> TOOLBOX_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(FalloutStuff.MOD_ID, "toolbox_be"),
                    FabricBlockEntityTypeBuilder.create(ToolboxBlockEntity::new,
                            ModBlocks.TOOLBOX).build());

    public static void registerBlockEntities() {
        FalloutStuff.LOGGER.info("Registering Block Entities for " + FalloutStuff.MOD_ID);
    }
}
