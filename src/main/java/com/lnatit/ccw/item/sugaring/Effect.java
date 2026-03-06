package com.lnatit.ccw.item.sugaring;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import net.minecraft.util.StringUtil;
import net.minecraft.world.effect.MobEffect;

public record Effect(Holder<MobEffect> effect, int duration, int amplifier)
{
    public Effect withDuration(int duration) {
        return new Effect(this.effect, duration, this.amplifier);
    }

    public Effect doubleDuration() {
        if (this.effect.value().isInstantenous())
            return this;
        return this.withDuration(this.duration * 2);
    }

    public Effect withAmplifier(int amplifier) {
        return new Effect(this.effect, this.duration, amplifier);
    }

    public Effect enhanceAmplifier() {
        return this.withAmplifier(this.amplifier + 1);
    }

    public boolean is(Holder<MobEffect> effect) {
        return this.effect.is(effect);
    }

    public Component getDescription(float ticksPerSecond)
    {
        MutableComponent mutablecomponent = Component.translatable(effect.value().getDescriptionId());

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

        return mutablecomponent.withStyle(effect.value().getCategory().getTooltipFormatting());
    }
}
