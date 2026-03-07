package com.lnatit.ccw.datapack;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.item.sugaring.Sugar;
import com.lnatit.ccw.misc.RegRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

public record Formula(Holder<Sugar> sugar, ResourceLocation flavor, List<com.lnatit.ccw.datapack.Effect> effects)
{
    public static final ResourceKey<Registry<Formula>> KEY = ResourceKey.createRegistryKey(CandyWorkshop.id("formula"));

    public static final Codec<Holder<Sugar>> SUGAR_CODEC = ResourceLocation.CODEC.xmap(
            rl -> RegRegistry.SUGAR.getHolder(rl).orElseThrow(() -> new IllegalStateException("Sugar not found: " + rl)),
            holder -> holder.getKey().location()
    );

    public static final Codec<Formula> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    SUGAR_CODEC.fieldOf("sugar").forGetter(Formula::sugar),
                    ResourceLocation.CODEC.fieldOf("flavor").forGetter(Formula::flavor),
                    com.lnatit.ccw.datapack.Effect.CODEC.listOf().fieldOf("effects").forGetter(Formula::effects)
            ).apply(instance, Formula::new));

    public static com.lnatit.ccw.datapack.Effect effect(Holder<MobEffect> effect, int duration, int amplifier) {
        return new Effect(effect, duration, amplifier);
    }
}
