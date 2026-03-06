package com.lnatit.ccw.item.sugaring;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

import java.util.ArrayList;
import java.util.List;

public class UniSugar extends Sugar
{
    private final List<Effect> effects;

    private UniSugar(String name, List<Effect> effects) {
        super(name, true, true);
        if (effects.isEmpty()) {
            throw new IllegalArgumentException("UniSugar must have at least one effect");
        }
        this.effects = effects;
    }

    @Override
    public List<Effect> getEffects() {
        return new ArrayList<>(effects);
    }

    public static Builder builder(String name) {
        return new Builder(name);
    }

    public static class Builder
    {
        public static final int DEFAULT_DURATION = 600;
        public static final int DEFAULT_AMPLIFIER = 0;

        public String name;
        public List<Effect> effects = new ArrayList<>();

        public Builder(String name) {
            this.name = name;
        }

        private void removeDuplicatedEffect(Holder<MobEffect> effect) {
            effects.removeIf(e -> e.is(effect));
        }

        public Builder addEffect(Holder<MobEffect> effect)
        {
            removeDuplicatedEffect(effect);
            effects.add(new Effect(effect, DEFAULT_DURATION, DEFAULT_AMPLIFIER));
            return this;
        }

        public Builder addEffect(Holder<MobEffect> effect, int duration, int amplifier)
        {
            removeDuplicatedEffect(effect);
            effects.add(new Effect(effect, duration, amplifier));
            return this;
        }

        // I'm just tired of currying it...
        public UniSugar build() {
            // make effects immutable and validate them
            return new UniSugar(name, List.copyOf(effects));
        }
    }
}
