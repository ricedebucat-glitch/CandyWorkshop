package com.lnatit.ccw.compat.fruitsdelight;

import com.lnatit.ccw.item.sugaring.*;
import dev.xkmc.fruitsdelight.init.food.FruitType;
import dev.xkmc.fruitsdelight.init.registrate.FDEffects;
import dev.xkmc.fruitsdelight.init.registrate.FDItems;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

public class FruitsDelightCompats
{
    public static final DeferredHolder<Sugar, SingleEffectSugar> BLUEBERRY =
            Sugars.registerSingle("blueberry",
                                  builder -> builder
                                          .withEffect(FDEffects.BRIGHTENING)
                                          .withNoExcited()
                                          .build()
            );

    public static final DeferredHolder<Sugar, SingleEffectSugar> HAWBERRY =
            Sugars.registerSingle("hawthorn",
                                  builder -> builder
                                          .withEffect(FDEffects.APPETIZING)
                                          .withNoExcited()
                                          .build()
            );

    public static final DeferredHolder<Sugar, SingleEffectSugar> MANGO =
            Sugars.registerSingle(
                    "mango",
                    builder -> builder
                            .withEffect(FDEffects.RAGE_AURA)
                            .withNoExcited()
                            .build()
            );

    public static final DeferredHolder<Sugar, SingleEffectSugar> ORANGE =
            Sugars.registerSingle(
                    "orange",
                    builder -> builder
                            .withEffect(FDEffects.RECOVERING)
                            .withNoExcited()
                            .build()
            );

    public static final DeferredHolder<Sugar, SingleEffectSugar> PEACH =
            Sugars.registerSingle(
                    "peach",
                    builder -> builder
                            .withEffect(FDEffects.HEAL_AURA)
                            .withNoExcited()
                            .build()
            );

    public static final DeferredHolder<Sugar, SingleEffectSugar> PEAR =
            Sugars.registerSingle(
                    "snow_pear",
                    builder -> builder
                            .withEffect(FDEffects.LOZENGE)
                            .withNoExcited()
                            .build()
            );

    public static final DeferredHolder<Sugar, SingleEffectSugar> PERSIMMON =
            Sugars.registerSingle(
                    "persimmon",
                    builder -> builder
                            .withEffect(FDEffects.ASTRINGENT)
                            .withNoExcited()
                            .build()
            );

    public static final DeferredHolder<Sugar, SingleEffectSugar> PINEAPPLE =
            Sugars.registerSingle(
                    "pineapple",
                    builder -> builder
                            .withEffect(FDEffects.SWEETENING)
                            .withNoExcited()
                            .build()
            );

    public static final DeferredHolder<Sugar, SingleEffectSugar> LEMON =
            Sugars.registerSingle(
                    "lemon",
                    builder -> builder
                            .withEffect(FDEffects.REFRESHING)
                            .withNoExcited()
                            .build()
            );

    public static final DeferredHolder<Sugar, SingleEffectSugar> CRANBERRY =
            Sugars.registerSingle(
                    "cranberry",
                    builder -> builder
                            .withEffect(FDEffects.SHRINKING)
                            .withNoExcited()
                            .build()
            );

    public static final DeferredHolder<Sugar, SingleEffectSugar> MANGOSTEEN =
            Sugars.registerSingle(
                    "mangosteen",
                    builder -> builder
                            .withEffect(FDEffects.SLIDING)
                            .withNoExcited()
                            .build()
            );

    public static final DeferredHolder<Sugar, SingleEffectSugar> CHORUS =
            Sugars.registerSingle(
                    "chorus",
                    builder -> builder
                            .withEffect(FDEffects.CHORUS)
                            .withNoExcited()
                            .build()
            );

    public static final DeferredHolder<Sugar, SingleEffectSugar> BAYBERRY =
            Sugars.registerSingle(
                    "bayberry",
                    builder -> builder
                            .withEffect(FDEffects.LEAF_PIERCING)
                            .withNoExcited()
                            .build()
            );

    public static final DeferredHolder<Sugar, SingleEffectSugar> KIWI =
            Sugars.registerSingle(
                    "kiwi",
                    builder -> builder
                            .withEffect(FDEffects.CYCLING)
                            .withNoExcited()
                            .build()
            );

    public static final DeferredHolder<Sugar, SingleEffectSugar> FIG =
            Sugars.registerSingle(
                    "fig",
                    builder -> builder
                            .withEffect(FDEffects.DIGESTING)
                            .withNoExcited()
                            .build()
            );

    public static final DeferredHolder<Sugar, MultipleEffectSugar> DURIAN =
            Sugars.SUGARS.register("durian",
                                   () -> new MultipleEffectSugar("durian", false, true,
                                                                 MultipleEffectSugar.Effect.simple(
                                                                         FDEffects.ALIENATING),
                                                                 MultipleEffectSugar.Effect.simple(
                                                                         FDEffects.SUSPICIOUS_SMELL)

                                   )
            );

    public static void init() {
        SugarRefining.addCustomBlendProviders(FruitsDelightCompats::addBlends);
    }

    private static void addBlends(SugarRefining.Builder builder) {
        builder.addOverworldBlend(BLUEBERRY, getJello(FruitType.BLUEBERRY));
        builder.addOverworldBlend(HAWBERRY, getJello(FruitType.HAWBERRY));
        builder.addOverworldBlend(MANGO, getJello(FruitType.MANGO));
        builder.addOverworldBlend(ORANGE, getJello(FruitType.ORANGE));
        builder.addOverworldBlend(PEACH, getJello(FruitType.PEACH));
        builder.addOverworldBlend(PEAR, getJello(FruitType.PEAR));
        builder.addOverworldBlend(PERSIMMON, getJello(FruitType.PERSIMMON));
        builder.addOverworldBlend(PINEAPPLE, getJello(FruitType.PINEAPPLE));
        builder.addOverworldBlend(LEMON, getJello(FruitType.LEMON));
        builder.addOverworldBlend(CRANBERRY, getJello(FruitType.CRANBERRY));
        builder.addOverworldBlend(MANGOSTEEN, getJello(FruitType.MANGOSTEEN));
        builder.addOverworldBlend(CHORUS, getJello(FruitType.CHORUS));
        builder.addOverworldBlend(BAYBERRY, getJello(FruitType.BAYBERRY));
        builder.addOverworldBlend(KIWI, getJello(FruitType.KIWI));
        builder.addOverworldBlend(FIG, getJello(FruitType.FIG));
        builder.addOverworldBlend(DURIAN, getJello(FruitType.DURIAN));
    }

    private static Item getJello(FruitType type) {
        return FDItems.JELLO[type.ordinal()].get();
    }
}
