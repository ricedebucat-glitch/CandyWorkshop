package com.lnatit.ccw.item.sugaring.modifier;

import com.lnatit.ccw.datapack.Effect;
import com.lnatit.ccw.misc.RegRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IModifier
{
    Codec<Holder<IModifier>> CODEC = RegRegistry.MODIFIER.holderByNameCodec();
    StreamCodec<RegistryFriendlyByteBuf, Holder<IModifier>> STREAM_CODEC = ByteBufCodecs.holderRegistry(RegRegistry.MODIFIER_KEY);

    IModifier EMPTY = new IModifier() {};

    default ItemStack onApply(ItemStack stack) {
        return stack;
    }

    default ItemStack onRemove(ItemStack stack) {
        return stack;
    }

    default void preConsume(LivingEntity entity, List<Effect> effectsToApply) {}

    default void postConsume(LivingEntity entity, List<Effect> appliedEffects) {}
}
