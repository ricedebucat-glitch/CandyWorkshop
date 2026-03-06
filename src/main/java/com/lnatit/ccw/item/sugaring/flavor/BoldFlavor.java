package com.lnatit.ccw.item.sugaring.flavor;

import com.lnatit.ccw.item.sugaring.Effect;
import com.lnatit.ccw.item.sugaring.Sugar;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;

public class BoldFlavor extends Flavor
{
    public BoldFlavor(String name, int color) {
        super(name, color);
    }

    @Override
    public Item asItem() {
        return Items.HONEY_BOTTLE;
    }

    @Override
    public List<Effect> getEffectsOf(Holder<Sugar> sugarHolder) {
        List<Effect> effects = sugarHolder.value().getEffects();
        effects.replaceAll(Effect::doubleDuration);
        return effects;
    }
}
