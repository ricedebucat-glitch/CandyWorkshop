package com.lnatit.ccw.datapack;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.item.sugaring.modifier.IModifier;
import com.lnatit.ccw.item.sugaring.modifier.Modifiers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nullable;
import java.util.Optional;

// TODO impl explicit flavor definitions
public record Flavor(int color, Ingredient ingredient, Holder<IModifier> modifier, boolean explicit)
{
    public static final ResourceKey<Registry<Flavor>> KEY = ResourceKey.createRegistryKey(CandyWorkshop.id("flavor"));

    public static final Codec<Flavor> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance
            .group(Codec.INT.fieldOf("color").forGetter(Flavor::color),
                   Ingredient.CODEC.fieldOf("ingredient").forGetter(Flavor::ingredient),
                   IModifier.CODEC.fieldOf("modifier").forGetter(Flavor::modifier))
            .apply(instance, Flavor::auto));
    public static final Codec<Holder<Flavor>> CODEC = RegistryFileCodec.create(KEY, DIRECT_CODEC);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<Flavor>> STREAM_CODEC = ByteBufCodecs.holderRegistry(
            KEY);

    public static final ResourceKey<Flavor> ORIGINAL = DataPackRegistry.of(KEY, "original");

    @Nullable
    public static Registry<Flavor> REGISTRIES() {
        return (Registry<Flavor>) BuiltInRegistries.REGISTRY.get(KEY.location());
    }

    public static Optional<Holder.Reference<Flavor>> getFlavorUnsafe(ResourceLocation name) {
        var r = REGISTRIES();
        if (r == null) {
            throw new IllegalStateException("Flavor registry not found!");
        }
        return r.getHolder(name);
    }

    public static Holder<Flavor> getFlavor(ResourceLocation name) {
        return getFlavorUnsafe(name).orElse(getFlavorUnsafe(ORIGINAL.location()).get());
    }

    public static Flavor explicit(int color, Ingredient ingredient, Holder<IModifier> modifier) {
        return new Flavor(color, ingredient, modifier, true);
    }

    public static Flavor auto(int color, Ingredient ingredient, Holder<IModifier> modifier) {
        return new Flavor(color, ingredient, modifier, modifier == Modifiers.EMPTY);
    }

    public static Holder<Flavor> fromExtra(ItemStack extra) {
        var registry = REGISTRIES();
        if (registry == null) {
            throw new IllegalStateException("Flavor registry not found!");
        }

        if (!extra.isEmpty()) {
            Optional<Holder.Reference<Flavor>> match = registry
                    .holders()
                    .filter(holder -> holder.value().ingredient().test(extra))
                    .findFirst();
            if (match.isPresent()) {
                return match.get();
            }
        }

        return getFlavor(ORIGINAL.location());
    }

    public static MutableComponent prefix(Holder<Flavor> flavor) {
        return Component
                .translatable("item.ccw.gummy." + flavor.getKey().location() + ".prefix")
                .withStyle(Style.EMPTY.withColor(flavor.value().color()));
    }

    public static MutableComponent description(Holder<Flavor> flavor) {
        return Component
                .translatable("item.ccw.gummy." + flavor.getKey().location() + ".desc")
                .withStyle(Style.EMPTY.withColor(flavor.value().color()));
    }
}
