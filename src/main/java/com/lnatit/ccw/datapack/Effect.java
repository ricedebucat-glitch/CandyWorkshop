package com.lnatit.ccw.datapack;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import net.minecraft.util.StringUtil;
import net.minecraft.world.effect.MobEffect;

public record Effect(Holder<MobEffect> mobEffect, int duration, int amplifier)
{
    public static final Codec<Effect> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    MobEffect.CODEC.fieldOf("mob_effect").forGetter(Effect::mobEffect),
                    Codec.INT.fieldOf("duration").forGetter(Effect::duration),
                    Codec.INT.fieldOf("amplifier").forGetter(Effect::amplifier)
            ).apply(instance, Effect::new));

    public static final int DEFAULT_DURATION = 600;
    public static final int DEFAULT_AMPLIFIER = 0;

    public static Effect simple(Holder<MobEffect> mobEffect) {
        return new Effect(mobEffect, DEFAULT_DURATION, DEFAULT_AMPLIFIER);
    }

    public static Effect instant(Holder<MobEffect> mobEffect) {
        if (!mobEffect.value().isInstantenous()) {
            throw new IllegalArgumentException("MobEffect is not instant: " + mobEffect.value());
        }
        return new Effect(mobEffect, 1, DEFAULT_AMPLIFIER);
    }

    public Effect withAmplifier(int amplifier) {
        return new Effect(this.mobEffect, this.duration, amplifier);
    }

    public Effect enhanceAmplifier() {
        return this.withAmplifier(this.amplifier + 1);
    }

    public Effect withDuration(int duration) {
        return new Effect(this.mobEffect, duration, this.amplifier);
    }

    public Effect doubleDuration() {
        if (this.mobEffect.value().isInstantenous()) {
            return this;
        }
        return this.withDuration(this.duration * 2);
    }

    public boolean is(Holder<MobEffect> effect) {
        return this.mobEffect.is(effect);
    }

    public Component getDescription(float ticksPerSecond)
    {
        MutableComponent mutablecomponent = Component.translatable(mobEffect.value().getDescriptionId());

        if (amplifier > 0) {
            mutablecomponent = Component.translatable(
                    "potion.withAmplifier", mutablecomponent,
                    Component.translatable("potion.potency." + amplifier)
            );
        }

        if (duration > 20) {
            int i = Mth.floor((float) duration);
            Component result = Component.literal(StringUtil.formatTickDuration(i, ticksPerSecond));
            mutablecomponent = Component.translatable(
                    "sugar.withDuration", mutablecomponent,
                    result
            );
        }

        return mutablecomponent.withStyle(mobEffect.value().getCategory().getTooltipFormatting());
    }
}
