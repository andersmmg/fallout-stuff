package com.andersmmg.falloutstuff;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andersmmg.falloutstuff.block.ModBlocks;
import com.andersmmg.falloutstuff.item.ModItemGroups;
import com.andersmmg.falloutstuff.item.ModItems;

public class FalloutStuff implements ModInitializer {
	public static final String MOD_ID = "fallout-stuff";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
	}
}