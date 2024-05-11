package com.andersmmg.falloutstuff.materials;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.Map;

public class PowerArmorMaterial implements ArmorMaterial {
    private static final Map<ArmorItem.Type, Integer> BASE_DURABILITY = Map.of(
            ArmorItem.Type.HELMET, 13,
            ArmorItem.Type.CHESTPLATE, 15,
            ArmorItem.Type.LEGGINGS, 16,
            ArmorItem.Type.BOOTS, 11
    );

    private static final Map<ArmorItem.Type, Integer> PROTECTION_VALUES = Map.of(
            ArmorItem.Type.HELMET, 4,
            ArmorItem.Type.CHESTPLATE, 7,
            ArmorItem.Type.LEGGINGS, 9,
            ArmorItem.Type.BOOTS, 4
    );

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY.get(type);
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return PROTECTION_VALUES.get(type);
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.IRON_INGOT);
    }

    @Override
    public String getName() {
        return "power_armor";
    }

    @Override
    public float getToughness() {
        return 3.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.1F;
    }
}