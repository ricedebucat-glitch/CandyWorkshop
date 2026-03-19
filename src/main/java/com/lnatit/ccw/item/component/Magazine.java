package com.lnatit.ccw.item.component;

import com.google.common.collect.ImmutableList;
import com.lnatit.ccw.item.ItemRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record Magazine(ImmutableList<ItemStack> stacks, Tier tier) implements GummyContents {
    public static final Codec<Magazine> CODEC = GummyContents.codec(Magazine::of);
    public static final StreamCodec<RegistryFriendlyByteBuf, Magazine> STREAM_CODEC = GummyContents.streamCodec(Magazine::of);

    public static final int SLOTS = 6;
    public static final int TIER_MARCH = 2;
    public static final Magazine DEFAULT_MAGAZINE = new Magazine(ImmutableList.of(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY), Tier.PRIMARY);

    public static Mutable<Magazine> getMagazine(ItemStack stack) {
        if (!stack.has(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE))
            stack.set(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE, DEFAULT_MAGAZINE);
        Magazine magazine = stack.get(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE);
        assert magazine != null;
        return new Mutable<>(magazine, SLOTS);
    }

    private static Magazine of(List<ItemStack> stacks, Tier tier) {
        // TODO check list size & contents
        return new Magazine(ImmutableList.copyOf(stacks), tier);
    }

    public static void setMagazine(ItemStack stack, Mutable<Magazine> magazine) {
        if (stack.isEmpty())
            return;
        stack.set(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE, of(magazine.stacks(), magazine.tier()));
    }

    @Override
    public int getTierMarch() {
        return TIER_MARCH;
    }
}
