package com.lnatit.ccw.item.sugaring;

import com.lnatit.ccw.item.ItemRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;

import javax.annotation.Nullable;

public enum Flavor
{
    ORIGINAL("original", null),
    EXCITED("excited", ChatFormatting.DARK_GREEN),
    BOLD("bold", ChatFormatting.GOLD),
    MILKY("milky", ChatFormatting.WHITE);

    public static final Codec<Flavor> CODEC = Codec.stringResolver(Flavor::toName, Flavor::fromName);
    public static final StreamCodec<FriendlyByteBuf, Flavor> STREAM_CODEC = NeoForgeStreamCodecs.enumCodec(
            Flavor.class);

    public final String name;
    @Nullable
    public final ChatFormatting formatting;

    Flavor(String name, @Nullable ChatFormatting formatting) {
        this.name = name;
        this.formatting = formatting;
    }

    public static Flavor fromExtra(ItemStack extra) {
        if (extra.is(Items.COCOA_BEANS)) {
            return EXCITED;
        }
        if (extra.is(Items.HONEY_BOTTLE)) {
            return BOLD;
        }
        if (extra.is(ItemRegistry.MILK_GELATIN)) {
            return MILKY;
        }
        return ORIGINAL;
    }

    public static ItemStack toExtra(Flavor flavor) {
        return switch (flavor) {
            case EXCITED -> new ItemStack(Items.COCOA_BEANS);
            case BOLD -> new ItemStack(Items.HONEY_BOTTLE);
            case MILKY -> new ItemStack(ItemRegistry.MILK_GELATIN.asItem());
            default -> ItemStack.EMPTY;
        };
    }

    @Nullable
    public static MutableComponent nameOf(Flavor flavor) {
        return flavor == Flavor.ORIGINAL ? null :
                Component.translatable("item.ccw.gummy.".concat(flavor.name).concat(".prefix")).withStyle(
                        flavor.formatting);
    }

    @Nullable
    public static Component descriptionOf(Flavor flavor) {
        return flavor == Flavor.ORIGINAL ? null :
                Component.translatable("item.ccw.gummy.".concat(flavor.name).concat(".desc")).withStyle(
                        flavor.formatting);
    }

    static String toName(Flavor flavor) {
        return flavor.name;
    }

    @Nullable
    static Flavor fromName(String name) {
        return switch (name) {
            case "original" -> ORIGINAL;
            case "excited" -> EXCITED;
            case "bold" -> BOLD;
            case "milky" -> MILKY;
            default -> null;
        };
    }
}
