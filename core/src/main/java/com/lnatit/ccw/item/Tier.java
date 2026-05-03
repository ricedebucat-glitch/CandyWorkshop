package com.lnatit.ccw.item;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;

public enum Tier implements StringRepresentable
{
    PRIMARY,
    NETHER,
    ENDER;

    public static final Codec<Tier> CODEC = StringRepresentable.fromEnum(Tier::values);
    public static final StreamCodec<RegistryFriendlyByteBuf, Tier> STREAM_CODEC =
            NeoForgeStreamCodecs.enumCodec(Tier.class);

    @Override
    public String getSerializedName() {
        return this.name().toLowerCase();
    }
}
