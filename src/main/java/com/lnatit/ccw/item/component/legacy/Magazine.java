package com.lnatit.ccw.item.component.legacy;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class Magazine extends GummyContents {
    public static final Codec<Magazine> CODEC = codec(Magazine::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, Magazine> STREAM_CODEC = streamCodec(Magazine::new);
    public static final int SLOT_SIZE = 6;
    public static final int TIER_MARCH = 2;

    public Magazine() {
        super(SLOT_SIZE);
    }

    private Magazine(List<ItemStack> stacks, Tier tier) {
        super(stacks.subList(0, SLOT_SIZE), tier);
    }

    @Override
    protected int getTierMarch() {
        return TIER_MARCH;
    }
}
