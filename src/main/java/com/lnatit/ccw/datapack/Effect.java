package com.lnatit.ccw.datapack;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

public record Effect(Holder<MobEffect> mobEffect, int duration, int amplifier)
{
    public static final Codec<Holder<MobEffect>> MOB_EFFECT_CODEC = ResourceLocation.CODEC.xmap(
            rl -> BuiltInRegistries.MOB_EFFECT.getHolder(rl).orElseThrow(
                    () -> new IllegalStateException("MobEffect not found: " + rl)),
            holder -> holder.getKey().location()
    );
    public static final Codec<Effect> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    MOB_EFFECT_CODEC.fieldOf("mob_effect").forGetter(Effect::mobEffect),
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
            throw new IllegalArgumentException("MobEffect is not instantenous: " + mobEffect.value());
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
}
