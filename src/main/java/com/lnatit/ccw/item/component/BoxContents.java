package com.lnatit.ccw.item.component;

import com.lnatit.ccw.item.ItemRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.List;

public class BoxContents extends GummyContents
{
    public static final int SIZE = 6;
    public static final int TIER_MARCH = 2;
    public static final Codec<BoxContents> CODEC = RecordCodecBuilder.create(instance -> instance
            .group(ItemStack.CODEC.listOf().fieldOf("contents").forGetter(c -> c.contents))
            .apply(instance, BoxContents::of));
    public static final StreamCodec<RegistryFriendlyByteBuf, BoxContents> STREAM_CODEC = StreamCodec.composite(
            NonNullList.,
            BoxContents::contents,
            BoxContents::of);

    private BoxContents() {
        super(SIZE);
    }

    public static BoxContents of() {
        return of(List.of());
    }

    public static BoxContents of(List<ItemStack> contents) {
        BoxContents result = new BoxContents();
        result.updateContents(contents);
        return result;
    }

    @Override
    protected int getTierMarch() {
        return TIER_MARCH;
    }

    public static IItemHandler access(ItemStack stack) {
        if (!stack.has(ItemRegistry.BOX_CONTENTS_DCTYPE)) {
            stack.set(ItemRegistry.BOX_CONTENTS_DCTYPE, BoxContents.of());
        }
        BoxContents contents = stack.get(ItemRegistry.BOX_CONTENTS_DCTYPE);
        return contents.new GummyItemHandler();
    }
}
