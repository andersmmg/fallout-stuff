package com.andersmmg.falloutstuff.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "fallout-stuff")
public class ModConfig implements ConfigData {
    public int stimpakHealAmt = 5;
}