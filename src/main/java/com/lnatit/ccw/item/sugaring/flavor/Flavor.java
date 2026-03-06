package com.lnatit.ccw.item.sugaring.flavor;

import com.lnatit.ccw.item.sugaring.Effect;
import com.lnatit.ccw.item.sugaring.Sugar;
import com.lnatit.ccw.item.sugaring.formula.Modifier;
import com.lnatit.ccw.misc.RegRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class Flavor<T extends Modifier> implements ItemLike
{
    public static final Codec<Holder<Flavor>> CODEC = RegRegistry.FLAVOR.holderByNameCodec();
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<Flavor>> STREAM_CODEC = ByteBufCodecs.holderRegistry(
            RegRegistry.FLAVOR_KEY);
    public final String name;
    public final Style style;

    public Flavor(String name) {
        this.name = name;
        this.style = Style.EMPTY;
    }

    public Flavor(String name, int color) {
        this.name = name;
        this.style = Style.EMPTY.withColor(color);
    }

    // TODO: maybe we need to make it a list and data-driven?
    @Override
    public Item asItem() {
        return Items.AIR;
    }

    public Component prefix() {
        return Component.translatable("item.ccw.gummy." + name + ".prefix").withStyle(style);
    }

    public Component description() {
        return Component.translatable("item.ccw.gummy." + name + ".desc").withStyle(style);
    }

    public ItemStack createSugar(Holder<Sugar> sugar) {
        return Sugar
    }

    public List<Effect> getEffectsOf(Holder<Sugar> sugar) {
        return sugar.value().getEffects();
    }

    public void preConsume(LivingEntity entity, List<Effect> effectsToApply) {
    }

    public final void preConsume(LivingEntity entity, Holder<Sugar> sugar) {
        this.preConsume(entity, this.getEffectsOf(sugar));
    }
}
