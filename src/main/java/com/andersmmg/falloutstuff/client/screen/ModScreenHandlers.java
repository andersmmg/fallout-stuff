package com.andersmmg.falloutstuff.client.screen;

import com.andersmmg.falloutstuff.FalloutStuff;
import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers implements AutoRegistryContainer<ScreenHandlerType<?>> {
    @Override
    public Registry<ScreenHandlerType<?>> getRegistry() {
        return Registries.SCREEN_HANDLER;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<ScreenHandlerType<?>> getTargetFieldType() {
        return (Class<ScreenHandlerType<?>>) (Object) ScreenHandlerType.class;
    }

    public static void registerScreenHandlers() {
        FalloutStuff.LOGGER.info("Registering Screen Handlers for " + FalloutStuff.MOD_ID);
    }
}