package com.lnatit.ccw.item.sugaring.flavor;

import com.lnatit.ccw.item.sugaring.Effect;
import com.lnatit.ccw.item.sugaring.Sugar;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Map;

public class ExcitedFlavor extends Flavor
{
    private final Map<Sugar, IntList> amplifierModifiers = Map.of();

    public ExcitedFlavor(String name, int color) {
        super(name, color);
    }

    @Override
    public Item asItem() {
        return Items.COCOA_BEANS;
    }

    @Override
    public List<Effect> getEffectsOf(Holder<Sugar> sugarHolder) {
        Sugar sugar = sugarHolder.value();
        List<Effect> effects = sugar.getEffects();
        if (this.amplifierModifiers.containsKey(sugar)) {
            List<Integer> modifier = this.amplifierModifiers.get(sugar);
            for (int i = 0; i < Math.min(effects.size(), modifier.size()); i++) {
                effects.set(i, effects.get(i).withAmplifier(modifier.get(i)));
            }
        }
        else {
            effects.replaceAll(Effect::enhanceAmplifier);
        }
        return effects;
    }
}
