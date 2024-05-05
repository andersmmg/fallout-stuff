package com.andersmmg.falloutstuff;

import com.andersmmg.falloutstuff.block.ModBlocks;
import com.andersmmg.falloutstuff.block.entity.ModBlockEntities;
import com.andersmmg.falloutstuff.config.ModConfig;
import com.andersmmg.falloutstuff.item.ModItemGroups;
import com.andersmmg.falloutstuff.item.ModItems;
import com.andersmmg.falloutstuff.screen.ModScreenHandlers;
import com.andersmmg.falloutstuff.sound.ModSounds;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FalloutStuff implements ModInitializer {
	public static final String MOD_ID = "fallout-stuff";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);

		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();

		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModSounds.registerSounds();
	}
}