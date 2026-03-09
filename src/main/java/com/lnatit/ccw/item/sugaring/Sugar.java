package com.lnatit.ccw.item.sugaring;

import com.lnatit.ccw.misc.RegRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public record Sugar(Type type, Ingredient ingredient)
{
    public static final Codec<Holder<Sugar>> CODEC = RegRegistry.SUGAR.holderByNameCodec();
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<Sugar>> STREAM_CODEC = ByteBufCodecs.holderRegistry(
            RegRegistry.SUGAR_KEY);

    public static ResourceLocation getItemModel(Holder<Sugar> sugar) {
        return sugar.getKey().location().withSuffix("_gummy");
    }

    public static ResourceLocation getModelId(Holder<Sugar> sugar) {
        return getItemModel(sugar).withPrefix("item/");
    }

    public enum Type
    {
        OVERWORLD,
        NETHER,
        END
    }
}
