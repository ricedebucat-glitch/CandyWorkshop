package com.lnatit.ccw.compat.youkaishomecoming;

import com.lnatit.ccw.item.sugaring.*;
import dev.xkmc.youkaishomecoming.init.food.YHCrops;
import dev.xkmc.youkaishomecoming.init.food.YHTea;
import dev.xkmc.youkaishomecoming.init.registrate.YHEffects;
import dev.xkmc.youkaishomecoming.init.registrate.YHItems;
import net.neoforged.neoforge.registries.DeferredHolder;

public class YoukaisHomecomingCompats
{
    public static final DeferredHolder<Sugar, MultipleEffectSugar> GREEN_TEA =
            Sugars.SUGARS.register("green_tea", () ->
                    new MultipleEffectSugar("green_tea", false, true,
                                            MultipleEffectSugar.Effect.simple(YHEffects.TEA, 600, 1),
                                            MultipleEffectSugar.Effect.simple(YHEffects.SOBER)
                    )
            );
    public static final DeferredHolder<Sugar, MultipleEffectSugar> WHITE_TEA =
            Sugars.SUGARS.register("white_tea", () ->
                    new MultipleEffectSugar("white_tea", false, true,
                                            MultipleEffectSugar.Effect.simple(YHEffects.TEA),
                                            MultipleEffectSugar.Effect.simple(YHEffects.REFRESHING),
                                            MultipleEffectSugar.Effect.simple(YHEffects.SOBER)
                    )
            );
    public static final DeferredHolder<Sugar, MultipleEffectSugar> BLACK_TEA =
            Sugars.SUGARS.register("black_tea", () ->
                    new MultipleEffectSugar("black_tea", false, true,
                                            MultipleEffectSugar.Effect.simple(YHEffects.TEA),
                                            MultipleEffectSugar.Effect.simple(YHEffects.THICK),
                                            MultipleEffectSugar.Effect.simple(YHEffects.SOBER)
                    )
            );
    public static final DeferredHolder<Sugar, MultipleEffectSugar> OOLONG_TEA =
            Sugars.SUGARS.register("oolong_tea", () ->
                    new MultipleEffectSugar("oolong_tea", false, true,
                                            MultipleEffectSugar.Effect.simple(YHEffects.TEA),
                                            MultipleEffectSugar.Effect.simple(YHEffects.SMOOTHING),
                                            MultipleEffectSugar.Effect.simple(YHEffects.SOBER)
                    )
            );
    public static final DeferredHolder<Sugar, MultipleEffectSugar> YOUKAI_COFFEE =
            Sugars.SUGARS.register("youkai_coffee", () ->
                    new MultipleEffectSugar("youkai_coffee", false, true,
                                            MultipleEffectSugar.Effect.simple(YHEffects.CAFFEINATED),
                                            MultipleEffectSugar.Effect.simple(YHEffects.SOBER)
                    )
            );
    public static final DeferredHolder<Sugar, Sugar> UDUMBARA =
            Sugars.registerSingle("udumbara",
                                  builder -> builder.withEffect(YHEffects.UDUMBARA)
                                                    .withNoExcited()
                                                    .build()
            );

    public static void init() {
        SugarRefining.addCustomBlendProviders(YoukaisHomecomingCompats::addBlends);
    }

    private static void addBlends(SugarRefining.Builder builder) {
        builder.addOverworldBlend(GREEN_TEA, YHTea.GREEN.leaves.get());
        builder.addOverworldBlend(WHITE_TEA, YHTea.WHITE.leaves.get());
        builder.addOverworldBlend(BLACK_TEA, YHTea.BLACK.leaves.get());
        builder.addOverworldBlend(OOLONG_TEA, YHTea.OOLONG.leaves.get());
        builder.addOverworldBlend(YOUKAI_COFFEE, YHItems.COFFEE_POWDER.get());
        builder.addOverworldBlend(UDUMBARA, YHCrops.UDUMBARA.getFruits());
    }
}
