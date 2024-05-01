package com.andersmmg.falloutstuff.sound;

import com.andersmmg.falloutstuff.FalloutStuff;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent STIMPAK_USE = registerSoundEvent("stimpak_use");
    public static final SoundEvent FILECABINET_OPEN = registerSoundEvent("filecabinet_open");
    public static final SoundEvent FILECABINET_CLOSE = registerSoundEvent("filecabinet_close");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(FalloutStuff.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        FalloutStuff.LOGGER.info("Registering Mod Sounds for " + FalloutStuff.MOD_ID);
    }
}
