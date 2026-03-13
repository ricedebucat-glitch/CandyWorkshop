package com.lnatit.ccw.item.component;

import com.mojang.serialization.Codec;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public class Magazine extends GummyContents {
    public static final Codec<Magazine> CODEC = codec(Magazine::new);
    public static final StreamCodec<FriendlyByteBuf, Magazine> STREAM_CODEC = streamCodec(Magazine::new);
    public static final int SLOT_SIZE = 6;
    public static final int TIER_MARCH = 2;

    public Magazine() {
        super(SLOT_SIZE);
    }

    private Magazine(NonNullList<ItemStack> stacks, Tier tier) {
        super(stacks.subList(0, SLOT_SIZE), tier);
    }

    @Override
    protected int getTierMarch() {
        return TIER_MARCH;
    }
}
