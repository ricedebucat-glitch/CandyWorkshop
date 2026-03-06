package com.lnatit.ccw.item.sugaring.flavor;

import com.lnatit.ccw.item.ItemRegistry;
import com.lnatit.ccw.item.sugaring.Effect;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class MilkyFlavor extends Flavor
{
    public MilkyFlavor(String name, int color) {
        super(name, color);
    }

    @Override
    public Item asItem() {
        return ItemRegistry.MILK_GELATIN.asItem();
    }

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
