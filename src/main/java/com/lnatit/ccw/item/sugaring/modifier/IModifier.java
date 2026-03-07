package com.lnatit.ccw.item.sugaring.modifier;

import com.lnatit.ccw.item.sugaring.Effect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IModifier
{
    default ItemStack onApply(ItemStack stack) {
        return stack;
    }

    default ItemStack onRemove(ItemStack stack) {
        return stack;
    }

    default void preConsume(LivingEntity entity, List<Effect> effectsToApply) {}

    default void postConsume(LivingEntity entity, List<Effect> appliedEffects) {}
}
