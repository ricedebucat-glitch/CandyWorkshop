package com.lnatit.ccw.datagen;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.compat.NeapolitanCompats;
import com.lnatit.ccw.data.Effect;
import com.teamabnormals.neapolitan.core.registry.NeapolitanMobEffects;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = CandyWorkshop.MODID, bus = EventBusSubscriber.Bus.MOD)
public class NeapolitanDataGenerate {
    private static boolean formulasInitialized;

    private static void initFormulas() {
        if (formulasInitialized) {
            return;
        }
        formulasInitialized = true;

        CoreDataProviders.get()
                         .loaded("neapolitan")
                         .register(NeapolitanCompats.HOOHOO_HAHA, Effect.simple(NeapolitanMobEffects.AGILITY))
                         .defaultBold()

                         .register(NeapolitanCompats.HOOHOO_SMOOTH, Effect.simple(NeapolitanMobEffects.SLIPPING))
                         .defaultBold()

                         .register(NeapolitanCompats.MINT, Effect.simple(NeapolitanMobEffects.BERSERKING))
                         .defaultBold()

                         .register(NeapolitanCompats.RED_BEAN, Effect.simple(NeapolitanMobEffects.HARMONY))
                         .defaultBold()

                         .register(NeapolitanCompats.VANILLA, Effect.simple(NeapolitanMobEffects.VANILLA_SCENT))
                         .defaultBold()

                         .register(NeapolitanCompats.SUGAR_RUSH, Effect.simple(NeapolitanMobEffects.SUGAR_RUSH))
                         .defaultExcited()
                         .defaultBold()
                         .clearConditions();
    }

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        initFormulas();
    }
}

