package com.lnatit.ccw.datagen;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.datapack.DataPackRegistry;
import com.lnatit.ccw.datapack.Effect;
import com.lnatit.ccw.datapack.Formula;
import com.lnatit.ccw.item.sugaring.Sugar;
import com.lnatit.ccw.item.sugaring.Sugars;
import com.lnatit.ccw.item.sugaring.flavor.Flavor;
import com.lnatit.ccw.item.sugaring.flavor.Flavors;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDataProviders extends DatapackBuiltinEntriesProvider
{
    public ModDataProviders(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> registries
    ) {
        super(output, registries, new RegistrySetBuilder().add(Formula.KEY, ModDataProviders::register), Set.of(CandyWorkshop.MODID));
    }

    public static ResourceKey<Formula> of(Holder<Sugar> sugar, Holder<Flavor> flavor) {
        String sugarName = ((Holder<?>) sugar).getKey().location().getPath();
        String flavorName = ((Holder<?>) flavor).getKey().location().getPath();
        return DataPackRegistry.of(Formula.KEY, sugarName + "_" + flavorName);
    }

    private static void register(BootstrapContext<Formula> bootstrap) {
        // Overworld blends
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.SPEED, Effect.simple(MobEffects.MOVEMENT_SPEED));
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.BUNNY, Effect.simple(MobEffects.JUMP));
        ModDataProviders.registerSimpleSugarNoBold(bootstrap, Sugars.HEALING, Effect.instant(MobEffects.HEAL));
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.POISON, Effect.simple(MobEffects.POISON));
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.PUFFERFISH, Effect.simple(MobEffects.WATER_BREATHING));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.NIGHT_VISION, Effect.simple(MobEffects.NIGHT_VISION));
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.STRENGTH, Effect.simple(MobEffects.DAMAGE_BOOST));
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.RECOVERY, Effect.simple(MobEffects.REGENERATION));
        ModDataProviders.registerSugar(bootstrap,
                                       Sugars.TURTLE,
                                       new Effect(MobEffects.MOVEMENT_SLOWDOWN, 100, 3),
                                       new Effect(MobEffects.DAMAGE_RESISTANCE, 100, 2))
                        .excited(new Effect(MobEffects.MOVEMENT_SLOWDOWN, 100, 5),
                         new Effect(MobEffects.DAMAGE_RESISTANCE, 100, 3))
                        .bold(new Effect(MobEffects.MOVEMENT_SLOWDOWN, 200, 3),
                      new Effect(MobEffects.DAMAGE_RESISTANCE, 200, 2))
                        .run();
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.FLUTTER, Effect.simple(MobEffects.SLOW_FALLING));
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.SNAIL, Effect.simple(MobEffects.MOVEMENT_SLOWDOWN));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.STINKY, Effect.simple(MobEffects.CONFUSION));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.BLINDING, Effect.simple(MobEffects.BLINDNESS));
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.WEAKNESS, Effect.simple(MobEffects.WEAKNESS));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.BRIGHTNESS, Effect.simple(MobEffects.GLOWING));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.DARKNESS, Effect.simple(MobEffects.DARKNESS));
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.HUNGER, Effect.simple(MobEffects.HUNGER));

        // Nether blends
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.INVISIBILITY, Effect.simple(MobEffects.INVISIBILITY));
        ModDataProviders.registerSimpleSugarNoBold(bootstrap, Sugars.STINGER, Effect.instant(MobEffects.HARM));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.BUG, Effect.simple(MobEffects.INFESTED));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.STICKY, Effect.simple(MobEffects.OOZING));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.BINDING, Effect.simple(MobEffects.WEAVING));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.GALE, Effect.simple(MobEffects.WIND_CHARGED));
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.REFRESHING, Effect.simple(MobEffects.DIG_SPEED));
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.LAZY, Effect.simple(MobEffects.DIG_SLOWDOWN));
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.SOLID, Effect.simple(MobEffects.DAMAGE_RESISTANCE));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.FIREPROOF, Effect.simple(MobEffects.FIRE_RESISTANCE));
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.WITHERING, Effect.simple(MobEffects.WITHER));
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.RED_HEART, Effect.simple(MobEffects.HEALTH_BOOST));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.FLOATING, Effect.simple(MobEffects.LEVITATION));

        // End blends
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.GOLDEN_HEART, Effect.simple(MobEffects.ABSORPTION));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.SATIATING, Effect.simple(MobEffects.SATURATION));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.LUCKY, Effect.simple(MobEffects.LUCK));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.UNLUCKY, Effect.simple(MobEffects.UNLUCK));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.TIDAL, Effect.simple(MobEffects.CONDUIT_POWER));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.FISH_SWIM, Effect.simple(MobEffects.DOLPHINS_GRACE));
        ModDataProviders.registerSimpleSugarNoExcited(bootstrap, Sugars.TAUNTING, Effect.simple(MobEffects.BAD_OMEN));
        ModDataProviders.registerSimpleSugar(bootstrap, Sugars.DISCOUNT, Effect.simple(MobEffects.HERO_OF_THE_VILLAGE));
    }

    private static void registerSimpleSugar(
            BootstrapContext<Formula> bootstrap,
            Holder<Sugar> sugarHolder,
            Effect... effects
    ) {
        ModDataProviders.registerSimpleSugar(bootstrap, sugarHolder, false, false, effects);
    }

    private static void registerSimpleSugarNoExcited(
            BootstrapContext<Formula> bootstrap,
            Holder<Sugar> sugarHolder,
            Effect... effects
    ) {
        ModDataProviders.registerSimpleSugar(bootstrap, sugarHolder, true, false, effects);
    }

    private static void registerSimpleSugarNoBold(
            BootstrapContext<Formula> bootstrap,
            Holder<Sugar> sugarHolder,
            Effect... effects
    ) {
        ModDataProviders.registerSimpleSugar(bootstrap, sugarHolder, false, true, effects);
    }

    private static void registerSimpleSugar(
            BootstrapContext<Formula> bootstrap,
            Holder<Sugar> sugarHolder,
            boolean hasNoExcited,
            boolean hasNoBold,
            Effect... effects
    ) {
        List<Effect> effectList = List.of(effects);
        bootstrap.register(ModDataProviders.of(sugarHolder, Flavors.ORIGINAL),
                           new Formula(sugarHolder, Flavors.ORIGINAL, effectList));
        if (!hasNoExcited) {
            bootstrap.register(ModDataProviders.of(sugarHolder, Flavors.EXCITED),
                               new Formula(sugarHolder,
                                           Flavors.EXCITED,
                                           effectList.stream().map(Effect::enhanceAmplifier).toList()));
        }
        if (!hasNoBold) {
            bootstrap.register(ModDataProviders.of(sugarHolder, Flavors.BOLD),
                               new Formula(sugarHolder,
                                           Flavors.BOLD,
                                           effectList.stream().map(Effect::doubleDuration).toList()));
        }
    }

    private static RegisterContext registerSugar(
            BootstrapContext<Formula> bootstrap,
            Holder<Sugar> sugarHolder,
            Effect... effects
    ) {
        return new RegisterContext(bootstrap, sugarHolder, effects);
    }

    @Override
    public String getName() {
        return "Candy Workshop - Datapacks";
    }

    // Helper classes below...
    private interface HalfContext extends Runnable
    {
        HalfContext excited(Effect... effects);

        HalfContext bold(Effect... effects);
    }

    private static class RegisterContext implements HalfContext
    {
        BootstrapContext<Formula> bootstrap;
        Holder<Sugar> sugarHolder;
        List<Effect> originalEffects;
        @Nullable
        List<Effect> excitedEffects = null;
        @Nullable
        List<Effect> boldEffects = null;

        private RegisterContext(BootstrapContext<Formula> bootstrap, Holder<Sugar> sugarHolder, Effect... effects) {
            this.bootstrap = bootstrap;
            this.sugarHolder = sugarHolder;
            this.originalEffects = List.of(effects);
        }

        @Override
        public HalfContext excited(Effect... effects) {
            this.excitedEffects = List.of(effects);
            return this;
        }

        @Override
        public HalfContext bold(Effect... effects) {
            this.boldEffects = List.of(effects);
            return this;
        }

        @Override
        public void run() {
            bootstrap.register(ModDataProviders.of(sugarHolder ,Flavors.ORIGINAL),
                               new Formula(sugarHolder, Flavors.ORIGINAL, originalEffects));
            if (excitedEffects != null) {
                bootstrap.register(ModDataProviders.of(sugarHolder, Flavors.EXCITED),
                                   new Formula(sugarHolder, Flavors.EXCITED, excitedEffects));
            }
            if (boldEffects != null) {
                bootstrap.register(ModDataProviders.of(sugarHolder, Flavors.BOLD),
                                   new Formula(sugarHolder, Flavors.BOLD, boldEffects));
            }
        }
    }
}
