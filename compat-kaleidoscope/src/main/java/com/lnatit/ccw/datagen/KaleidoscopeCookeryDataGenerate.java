package com.lnatit.ccw.datagen;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.compat.CookeryCompat;
import com.lnatit.ccw.data.Effect;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = CandyWorkshop.MODID, bus = EventBusSubscriber.Bus.MOD)
public class KaleidoscopeCookeryDataGenerate {
    private static boolean formulasInitialized;

    private static void initFormulas() {
        if (formulasInitialized) {
            return;
        }
        formulasInitialized = true;

        CoreDataProviders.get()
                         .loaded("kaleidoscope_cookery")
                         .register(CookeryCompat.SWEET_AND_SOUR_MEAT, Effect.simple(com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects.VIGOR))
                         .defaultBold()

                         .register(CookeryCompat.VEGGIE_FRESH, Effect.simple(com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects.PRESERVATION))
                         .defaultBold()

                         .register(CookeryCompat.LACTRO_BOLT, Effect.simple(com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects.FLATULENCE))
                         .defaultBold()

                         .register(CookeryCompat.SASHIMI, Effect.simple(com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects.MUSTARD))
                         .defaultBold()

                         .register(CookeryCompat.TALLOW, Effect.simple(com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects.SATIATED_SHIELD))
                         .defaultBold()

                         .register(CookeryCompat.PHANTO_BYE, Effect.simple(com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects.SULFUR))
                         .defaultBold()

                         .register(CookeryCompat.BROTH_WARM, Effect.simple(com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects.WARMTH))
                         .defaultBold()

                         .register(CookeryCompat.MUTTON_DASH, Effect.simple(com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects.TUNDRA_STRIDER))
                         .defaultBold()
                         .clearConditions();
    }

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        initFormulas();

        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        generator.addProvider(event.includeClient(), new KaleidoscopeCookeryModelProvider(output, existingFileHelper));
    }
}

