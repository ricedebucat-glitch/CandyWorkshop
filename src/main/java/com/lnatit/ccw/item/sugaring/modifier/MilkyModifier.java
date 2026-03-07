package com.lnatit.ccw.item.sugaring.modifier;

import com.lnatit.ccw.datapack.Effect;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class MilkyModifier implements IModifier
{
    @Override
    public void preConsume(LivingEntity entity, List<Effect> effectsToApply) {
        List<Holder<MobEffect>> toRemove = new ArrayList<>();
        for (Holder<MobEffect> effect : entity.getActiveEffectsMap().keySet()) {
            if (effectsToApply.stream().noneMatch(inst -> inst.is(effect))) {
                toRemove.add(effect);
            }
        }
        toRemove.forEach(entity::removeEffect);
    }
}
