package com.andersmmg.falloutstuff;

import com.andersmmg.falloutstuff.block.entity.ModBlockEntities;
import com.andersmmg.falloutstuff.screen.ModScreenHandlers;
import com.andersmmg.falloutstuff.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andersmmg.falloutstuff.block.ModBlocks;
import com.andersmmg.falloutstuff.item.ModItemGroups;
import com.andersmmg.falloutstuff.item.ModItems;

public class FalloutStuff implements ModInitializer {
	public static final String MOD_ID = "fallout-stuff";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();

		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModSounds.registerSounds();
	}
}