package com.andersmmg.falloutstuff.client.screen;

import com.andersmmg.falloutstuff.FalloutStuff;
import net.minecraft.client.gui.screen.ingame.Generic3x3ContainerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers {
    public static final ScreenHandlerType<BagScreenHandler> BAG_SCREEN_HANDLER = new ScreenHandlerType<>(BagScreenHandler::new, FeatureFlags.VANILLA_FEATURES);

    public static void registerScreenHandlers() {
        FalloutStuff.LOGGER.info("Registering Screen Handlers for " + FalloutStuff.MOD_ID);
        Registry.register(Registries.SCREEN_HANDLER, FalloutStuff.id("bag"), BAG_SCREEN_HANDLER);
        HandledScreens.register(BAG_SCREEN_HANDLER, Generic3x3ContainerScreen::new);
    }
}