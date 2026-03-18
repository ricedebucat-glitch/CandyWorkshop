package com.lnatit.ccw.item.component;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;

import java.util.List;

public interface GummyContents<T extends GummyContents<T>> {
    Tier tier();

    List<ItemStack> stacks();

    T upgrade();


    enum Tier implements StringRepresentable {
        PRIMARY("primary"),
        NETHER("nether"),
        ENDER("ender");

        public static final Codec<Tier> CODEC = StringRepresentable.fromEnum(Tier::values);
        public static final StreamCodec<RegistryFriendlyByteBuf, Tier> STREAM_CODEC = NeoForgeStreamCodecs.enumCodec(Tier.class);

        private final String name;

        Tier(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }

    class Mutable<I extends GummyContents> extends ItemStackHandler {
        public Mutable(I contents, int size) {
            super(size);
        }
    }
}
