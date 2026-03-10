package com.lnatit.ccw.item.sugaring.flavor;

import com.lnatit.ccw.datapack.Effect;
import com.lnatit.ccw.item.ItemRegistry;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Style;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MilkyFlavor extends Flavor {
    public MilkyFlavor() {
        super(Ingredient.of(ItemRegistry.MILK_GELATIN));
    }

    @Override
    public Style style() {
        return super.style().withColor(0xFFFFFF);
    }

    @Override
    public @Nullable Holder<Flavor> proxy() {
        return Flavors.ORIGINAL;
    }

    @Override
    public void preConsume(LivingEntity entity, List<Effect> effectsToApply, List<Effect> formulaEffects) {
        List<Holder<MobEffect>> toRemove = new ArrayList<>();
        for (Holder<MobEffect> effect : entity.getActiveEffectsMap().keySet()) {
            if (effectsToApply.stream().noneMatch(inst -> inst.is(effect))) {
                toRemove.add(effect);
            }
        }
        toRemove.forEach(entity::removeEffect);
    }
}
