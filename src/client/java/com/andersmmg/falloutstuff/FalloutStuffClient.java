package com.andersmmg.falloutstuff;

import com.andersmmg.falloutstuff.screen.ModScreenHandlers;
import com.andersmmg.falloutstuff.screen.PositionedScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class FalloutStuffClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
//		HandledScreens.register(ModScreenHandlers.BOX_SCREEN_HANDLER, PositionedScreen::new);
	}
}