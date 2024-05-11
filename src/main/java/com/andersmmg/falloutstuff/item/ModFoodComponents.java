package com.andersmmg.falloutstuff.item;

import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent SNACK_CAKES = new FoodComponent.Builder().hunger(3).saturationModifier(0.25f).build();
    public static final FoodComponent CRAM = new FoodComponent.Builder().hunger(4).saturationModifier(0.25f).build();
    public static final FoodComponent NUKA_COLA = new FoodComponent.Builder().hunger(6).saturationModifier(0.1f).build();
}
