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
    public static final SoundEvent BOX_OPEN = registerSoundEvent("box_open");
    public static final SoundEvent BOX_CLOSE = registerSoundEvent("box_close");
    public static final SoundEvent ALARM = registerSoundEvent("alarm");
    public static final SoundEvent NUKA_VENDING_OPEN = registerSoundEvent("nuka_vending_open");
    public static final SoundEvent NUKA_VENDING_CLOSE = registerSoundEvent("nuka_vending_close");
    public static final SoundEvent CASH_REGISTER_OPEN = registerSoundEvent("cash_register_open");
    public static final SoundEvent CASH_REGISTER_CLOSE = registerSoundEvent("cash_register_close");
    public static final SoundEvent TOOLBOX_OPEN = registerSoundEvent("toolbox_open");
    public static final SoundEvent TOOLBOX_CLOSE = registerSoundEvent("toolbox_close");
    public static final SoundEvent SLIDING_DOOR_OPEN = registerSoundEvent("sliding_door_open");
    public static final SoundEvent SLIDING_DOOR_CLOSE = registerSoundEvent("sliding_door_close");
    public static final SoundEvent TOGGLE_CLICK = registerSoundEvent("toggle_click");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(FalloutStuff.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        FalloutStuff.LOGGER.info("Registering Mod Sounds for " + FalloutStuff.MOD_ID);
    }
}
