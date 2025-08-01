package com.lnatit.ccw.compat.youkaishomecoming;

import com.lnatit.ccw.item.sugaring.*;
import dev.xkmc.youkaishomecoming.init.registrate.YHEffects;
import net.neoforged.neoforge.registries.DeferredHolder;

public class YoukaisHomecomingCompats
{
    public static final DeferredHolder<Sugar, MultipleEffectSugar> GREEN_TEA =
            Sugars.SUGARS.register("green_tea", () ->
                    new MultipleEffectSugar("green_tea", false, true,
                                            MultipleEffectSugar.Effect.simple(YHEffects.TEA, 600, 1),
                                            MultipleEffectSugar.Effect.simple(YHEffects.SOBER, 600, 0)
                    )
            );
    public static final DeferredHolder<Sugar, MultipleEffectSugar> WHITE_TEA =
            Sugars.SUGARS.register("white_tea", () ->
                    new MultipleEffectSugar("white_tea", false, true,
                                            MultipleEffectSugar.Effect.simple(YHEffects.TEA, 600, 0),
                                            MultipleEffectSugar.Effect.simple(YHEffects.REFRESHING, 600, 0),
                                            MultipleEffectSugar.Effect.simple(YHEffects.SOBER, 600, 0)
                    )
            );
    public static final DeferredHolder<Sugar, MultipleEffectSugar> BLACK_TEA =
            Sugars.SUGARS.register("black_tea", () ->
                    new MultipleEffectSugar("black_tea", false, true,
                                            MultipleEffectSugar.Effect.simple(YHEffects.TEA, 600, 0),
                                            MultipleEffectSugar.Effect.simple(YHEffects.THICK, 600, 0),
                                            MultipleEffectSugar.Effect.simple(YHEffects.SOBER, 600, 0)
                    )
            );
    public static final DeferredHolder<Sugar, MultipleEffectSugar> OOLONG_TEA =
            Sugars.SUGARS.register("oolong_tea", () ->
                    new MultipleEffectSugar("oolong_tea", false, true,
                                            MultipleEffectSugar.Effect.simple(YHEffects.TEA, 600, 0),
                                            MultipleEffectSugar.Effect.simple(YHEffects.SMOOTHING, 600, 0),
                                            MultipleEffectSugar.Effect.simple(YHEffects.SOBER, 600, 0)
                    )
            );
    public static final DeferredHolder<Sugar, MultipleEffectSugar> YOUKAI_COFFEE =
            Sugars.SUGARS.register("youkai_coffee", () ->
                    new MultipleEffectSugar("youkai_coffee", false, true,
                                            MultipleEffectSugar.Effect.simple(YHEffects.CAFFEINATED, 600, 0),
                                            MultipleEffectSugar.Effect.simple(YHEffects.SOBER, 600, 0)
                    )
            );
    public static final DeferredHolder<Sugar, SingleEffectSugar> UDUMBARA =
            Sugars.registerSingle("udumbara",
                                  builder -> builder.withEffect(YHEffects.UDUMBARA)
                                                    .withNoExcited()
                                                    .build()
            );

    public static void init() {
        SugarRefining.addCustomBlendProviders(YoukaisHomecomingCompats::addBlends);
    }

    private static void addBlends(SugarRefining.Builder builder) {
    }
}
