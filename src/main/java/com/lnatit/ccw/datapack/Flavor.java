package com.lnatit.ccw.datapack;

import com.lnatit.ccw.CandyWorkshop;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Optional;

public record Flavor(ResourceLocation name, int color, Ingredient ingredient, Optional<ResourceLocation> modifier)
{
    public static final ResourceKey<Registry<Flavor>> KEY = ResourceKey.createRegistryKey(CandyWorkshop.id("flavor"));

    public static final Codec<Flavor> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(ResourceLocation.CODEC.fieldOf("name").forGetter(Flavor::name),
                                       Codec.INT.fieldOf("color").forGetter(Flavor::color),
                                       Ingredient.CODEC.fieldOf("ingredient").forGetter(Flavor::ingredient),
                                       ResourceLocation.CODEC.optionalFieldOf("modifier").forGetter(Flavor::modifier)
            ).apply(instance, Flavor::new));

    public static final ResourceKey<Flavor> ORIGINAL = DataPackRegistry.of(KEY, "original");
}
