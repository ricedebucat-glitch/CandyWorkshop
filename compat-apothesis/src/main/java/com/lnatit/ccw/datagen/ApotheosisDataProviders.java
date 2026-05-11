package com.lnatit.ccw.compat.datagen;

import com.lnatit.ccw.compat.apothesis.ApothesisCompats;
import com.lnatit.ccw.datagen.FormulaDatagenSupport.Formulas;
import com.lnatit.ccw.data.Effect;
import dev.shadowsoffire.apothic_attributes.api.ALObjects;

/**
 * Registers apotheosis-specific formula entries into shared formula datagen support.
 */
public final class ApotheosisDataProviders {
    private static boolean initialized;

    private ApotheosisDataProviders() {
    }

    public static void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        Formulas.of()
                .loaded("apotheosis")
                .register(ApothesisCompats.GRIEVOUS, Effect.simple(ALObjects.MobEffects.GRIEVOUS))
                .defaultExcited()
                .defaultBold()

                .register(ApothesisCompats.KNOWLEDGE, Effect.simple(ALObjects.MobEffects.KNOWLEDGE))
                .defaultExcited()
                .defaultBold()

                .register(ApothesisCompats.SUNDERING, Effect.simple(ALObjects.MobEffects.SUNDERING))
                .defaultExcited()
                .defaultBold()

                .register(ApothesisCompats.VITALITY, Effect.simple(ALObjects.MobEffects.VITALITY))
                .defaultExcited()
                .defaultBold()
                .clearConditions();
    }
}

