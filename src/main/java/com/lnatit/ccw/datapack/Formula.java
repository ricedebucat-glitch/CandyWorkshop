package com.lnatit.ccw.datapack;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.item.sugaring.Sugar;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

public record Formula(Holder<Sugar> sugar, ResourceLocation flavor, List<Effect> effects)
{
    public static final ResourceKey<Registry<Formula>> KEY = ResourceKey.createRegistryKey(CandyWorkshop.id("formula"));

    public static final Codec<Formula> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Sugar.CODEC.fieldOf("sugar").forGetter(Formula::sugar),
                    ResourceLocation.CODEC.fieldOf("flavor").forGetter(Formula::flavor),
                    Effect.CODEC.listOf().fieldOf("effects").forGetter(Formula::effects)
            ).apply(instance, Formula::new));

    public static Effect effect(Holder<MobEffect> effect, int duration, int amplifier) {
        return new Effect(effect, duration, amplifier);
    }
}
