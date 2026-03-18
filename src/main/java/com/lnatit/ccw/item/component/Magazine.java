package com.lnatit.ccw.item.component;

import com.google.common.collect.ImmutableList;
import com.lnatit.ccw.item.ItemRegistry;
import net.minecraft.world.item.ItemStack;

public record Magazine(ImmutableList<ItemStack> stacks, Tier tier) implements GummyContents<Magazine> {
    public static final int SLOTS = 6;
    public static final Magazine DEFAULT_MAGAZINE = new Magazine(ImmutableList.of(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY), Tier.PRIMARY);


    public static Mutable<Magazine> getMagazine(ItemStack stack) {
        if (stack.isEmpty())
            return null;
        if (!stack.has(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE))
            stack.set(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE, DEFAULT_MAGAZINE);
        Magazine magazine = stack.get(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE);
        return new Mutable<>(magazine, SLOTS);
    }

    @Override
    public Magazine upgrade() {
        return new Magazine(stacks, this.tier.);
    }
}
