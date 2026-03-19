package com.lnatit.ccw.item.component;

import com.google.common.collect.ImmutableList;
import com.lnatit.ccw.item.ItemRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record GummyContents(ImmutableList<ItemStack> stacks, Tier tier, Type type) implements IContents {
    public static final Codec<GummyContents> CODEC = IContents.codec(GummyContents::of);
    public static final StreamCodec<RegistryFriendlyByteBuf, GummyContents> STREAM_CODEC = IContents.streamCodec(GummyContents::of);

    public static final GummyContents DEFAULT_6 = new GummyContents(ImmutableList.of(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY), Tier.PRIMARY, Type.MAGAZINE);
    public static final MutableContents DEFAULT_MUTABLE_6 = new MutableContents(DEFAULT_6);

    public static MutableContents get6(ItemStack stack) {
        if (!stack.has(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE))
            stack.set(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE, DEFAULT_6);
        GummyContents gummyContents = stack.get(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE);
        assert gummyContents != null;
        return new MutableContents(gummyContents);
    }

    private static GummyContents of(List<ItemStack> stacks, Tier tier, Type type) {
        // TODO check list size & contents
        return new GummyContents(ImmutableList.copyOf(stacks), tier, type);
    }

    public static void set(ItemStack stack, MutableContents mutable) {
        if (stack.isEmpty())
            return;
        stack.set(mutable.type().supplier, of(mutable.stacks(), mutable.tier(), mutable.type()));
    }
}
