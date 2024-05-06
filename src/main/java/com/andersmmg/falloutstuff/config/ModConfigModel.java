package com.andersmmg.falloutstuff.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RangeConstraint;

@Modmenu(modId = "fallout-stuff")
@Config(name = "fallout-stuff", wrapperName = "ModConfig")
public class ModConfigModel {
    @RangeConstraint(min = 1, max = 20)
    public int stimpakHealAmt = 5;

    @RangeConstraint(min = 0.5f, max = 2.0f)
    public float alarmVolume = 1.0f;
}