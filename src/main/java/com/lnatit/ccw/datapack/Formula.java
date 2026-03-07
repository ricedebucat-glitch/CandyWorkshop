package com.lnatit.ccw.datapack;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.item.sugaring.Sugar;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public record Formula(Holder<Sugar> sugar, ResourceLocation flavor, List<Effect> effects)
{
    public static final ResourceKey<Registry<Formula>> KEY = ResourceKey.createRegistryKey(CandyWorkshop.id("formula"));

    public static final Codec<Formula> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Sugar.CODEC.fieldOf("sugar").forGetter(Formula::sugar),
                    ResourceLocation.CODEC.fieldOf("flavor").forGetter(Formula::flavor),
                    Effect.CODEC.listOf().fieldOf("effects").forGetter(Formula::effects)
            ).apply(instance, Formula::new));

    public Formula withFlavor(ResourceLocation flavor) {
        return new Formula(this.sugar, flavor, this.effects);
    }

    @Nullable
    public static Registry<Formula> REGISTRIES() {
        return (Registry<Formula>) BuiltInRegistries.REGISTRY.get(KEY.location());
    }

    public static ResourceLocation formulaOf(Holder<Sugar> sugar, Holder<Flavor> flavor) {
        return formulaOf(sugar.getRegisteredName(), flavor.getRegisteredName());
    }

    public static ResourceLocation formulaOf(Holder<Sugar> sugar, ResourceLocation flavor) {
        return formulaOf(sugar.getRegisteredName(), flavor.getPath());
    }

    public static ResourceLocation formulaOf(String sugarName, String flavorName) {
        return CandyWorkshop.id(formulaNameOf(sugarName, flavorName));
    }

    public static String formulaNameOf(String sugarName, String flavorName) {
        return sugarName + "_" + flavorName;
    }

    private static Optional<Holder.Reference<Formula>> getFormulaUnsafe(Holder<Sugar> sugar, ResourceLocation flavor) {
        var r = REGISTRIES();
        if (r == null) {
            throw new IllegalStateException("Formula registry not found!");
        }
        return r.getHolder(formulaOf(sugar, flavor));
    }

    // 虽然目前的设计会导致不同Modid path相同的Flavor搜索Formula出现问题，但我不想为了修这一个小问题增加方法的复杂度了
    public static Optional<? extends Holder<Formula>> getFormula(Holder<Sugar> sugar, Holder<Flavor> flavor) {
        boolean explicit = flavor.value().explicit();
        Optional<? extends Holder<Formula>> unsafe = getFormulaUnsafe(sugar, flavor.value().name());
        if (explicit) {
            return unsafe;
        }
        if (unsafe.isPresent()) {
            return unsafe;
        }
        unsafe = getFormulaUnsafe(sugar, Flavor.ORIGINAL.location())
                .map(f -> Holder.direct(f
                                                .value()
                                                .withFlavor(flavor
                                                                    .value()
                                                                    .name())));
        return unsafe;
    }
}
