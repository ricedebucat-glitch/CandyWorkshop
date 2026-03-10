package com.lnatit.ccw.item.sugaring;

import com.lnatit.ccw.item.sugaring.flavor.Flavors;
import com.lnatit.ccw.item.ItemRegistry;
import com.lnatit.ccw.item.sugaring.flavor.Flavor;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public class SugarRefining
{
    public static final int REFINE_TIME = 160;

    public static ItemStack createSugar(Holder<Sugar> sugar, Holder<Flavor> flavor) {
        ItemStack itemStack = ItemRegistry.GUMMY_ITEM.toStack();
        flavor.value().onApply(itemStack);
        itemStack.set(ItemRegistry.SUGAR_CONTENTS_DCTYPE, new SugarContents(sugar, flavor));
        return itemStack;
    }

    public static ItemStack createOriginalSugar(Holder<Sugar> sugar) {
        return createSugar(sugar, Flavors.ORIGINAL);
    }


}
