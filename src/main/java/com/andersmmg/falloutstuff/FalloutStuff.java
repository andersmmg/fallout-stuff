package com.andersmmg.falloutstuff;

import com.andersmmg.falloutstuff.block.ModBlocks;
import com.andersmmg.falloutstuff.block.entity.ModBlockEntities;
import com.andersmmg.falloutstuff.block.entity.VaultSignBlockEntity;
import com.andersmmg.falloutstuff.client.screen.ModScreenHandlers;
import com.andersmmg.falloutstuff.config.ModConfig;
import com.andersmmg.falloutstuff.item.ModItemGroups;
import com.andersmmg.falloutstuff.item.ModItems;
import com.andersmmg.falloutstuff.record.SignUpdatePacket;
import com.andersmmg.falloutstuff.sound.ModSounds;
import io.wispforest.owo.network.OwoNetChannel;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FalloutStuff implements ModInitializer {
	public static final String MOD_ID = "fallout-stuff";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ModConfig CONFIG = ModConfig.createAndLoad();

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

    public static final OwoNetChannel SIGN_UPDATE_CHANNEL = OwoNetChannel.create(id("sign_update_channel"));

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();

		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModSounds.registerSounds();

        // Register sign update packet
        SIGN_UPDATE_CHANNEL.registerServerbound(SignUpdatePacket.class, (message, access) -> {
            World world = access.player().getServerWorld();
            BlockEntity blockEntity = world.getBlockEntity(message.pos());
            if (blockEntity instanceof VaultSignBlockEntity vaultSignBlockEntity) {
                vaultSignBlockEntity.setText(message.text());
            }
        });
	}
}