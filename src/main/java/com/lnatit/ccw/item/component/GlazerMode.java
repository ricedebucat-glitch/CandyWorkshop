package com.lnatit.ccw.item.component;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;

public enum GlazerMode implements StringRepresentable
{
    SAVE, EXTEND;

    public static final Codec<GlazerMode> CODEC = StringRepresentable.fromEnum(GlazerMode::values);
    public static final StreamCodec<RegistryFriendlyByteBuf, GlazerMode> STREAM_CODEC =
            NeoForgeStreamCodecs.enumCodec(GlazerMode.class);

    @Override
    public String getSerializedName() {
        return this.name().toLowerCase();
    }
}
