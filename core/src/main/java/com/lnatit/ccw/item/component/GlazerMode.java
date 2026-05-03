package com.lnatit.ccw.item.component;

import com.lnatit.ccw.item.ItemRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;

import java.util.function.Consumer;

public enum GlazerMode implements StringRepresentable
{
    SAVE, EXTEND;

    public static final Codec<GlazerMode> CODEC = StringRepresentable.fromEnum(GlazerMode::values);
    public static final StreamCodec<RegistryFriendlyByteBuf, GlazerMode> STREAM_CODEC =
            NeoForgeStreamCodecs.enumCodec(GlazerMode.class);
    public static final String DESC_MODE_SELECTION_KEY = "item.ccw.gummy_glazer.mode_selection";
    public static final String DESC_MODE_SAVE_KEY = "item.ccw.gummy_glazer.mode_save";
    public static final String DESC_MODE_EXTEND_KEY = "item.ccw.gummy_glazer.mode_extend";
    public static final Component DESC_MODE_SAVE_D = Component.translatable(DESC_MODE_SAVE_KEY);
    public static final Component DESC_MODE_EXTEND_D = Component.translatable(DESC_MODE_EXTEND_KEY);
    public static final Component DESC_MODE_SAVE_A = DESC_MODE_SAVE_D.copy().withStyle(ChatFormatting.GOLD);
    public static final Component DESC_MODE_EXTEND_A = DESC_MODE_EXTEND_D.copy().withStyle(ChatFormatting.GOLD);

    @Override
    public String getSerializedName() {
        return this.name().toLowerCase();
    }

    public void addGlazerTooltip(Consumer<Component> tooltipAdder) {
        switch (this) {
            case SAVE -> tooltipAdder.accept(Component.translatable(GlazerMode.DESC_MODE_SELECTION_KEY,
                                                                    DESC_MODE_SAVE_A, DESC_MODE_EXTEND_D)
                                                      .withStyle(ChatFormatting.GRAY));
            case EXTEND -> tooltipAdder.accept(Component.translatable(GlazerMode.DESC_MODE_SELECTION_KEY,
                                                                      DESC_MODE_SAVE_D, DESC_MODE_EXTEND_A)
                                                        .withStyle(ChatFormatting.GRAY));
        }
    }

    public static GlazerMode getOrDefault(ItemStack glazer) {
        GlazerMode mode = glazer.get(ItemRegistry.GLAZER_MODE_DCTYPE);
        if (mode == null) {
            glazer.set(ItemRegistry.GLAZER_MODE_DCTYPE, GlazerMode.SAVE);
            return GlazerMode.SAVE;
        }
        return mode;
    }
}
