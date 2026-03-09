package com.lnatit.ccw.datagen;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.datapack.DataPackRegistry;
import com.lnatit.ccw.datapack.Effect;
import com.lnatit.ccw.datapack.Flavor;
import com.lnatit.ccw.datapack.Formula;
import com.lnatit.ccw.item.ItemRegistry;
import com.lnatit.ccw.item.sugaring.Sugar;
import com.lnatit.ccw.item.sugaring.Sugars;
import com.lnatit.ccw.item.sugaring.modifier.Modifiers;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public abstract class ModDataProviders extends DatapackBuiltinEntriesProvider
{
    public ModDataProviders(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> registries,
            RegistrySetBuilder datapackEntriesBuilder
    ) {
        super(output, registries, datapackEntriesBuilder, Set.of(CandyWorkshop.MODID));
    }

    public static class Flavors extends ModDataProviders
    {
        public static final ResourceKey<Flavor> ORIGINAL = Flavor.ORIGINAL;
        public static final ResourceKey<Flavor> EXCITED = of("excited");
        public static final ResourceKey<Flavor> BOLD = of("bold");
        public static final ResourceKey<Flavor> MILKY = of("milky");

        public Flavors(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries, new RegistrySetBuilder().add(Flavor.KEY, Flavors::register));
        }

        @Override
        public String getName() {
            return "Candy Workshop - Flavors";
        }

        private static void register(BootstrapContext<Flavor> bootstrap) {
            bootstrap.register(ORIGINAL,
                               Flavor.explicit(0xFFFFFF, Ingredient.EMPTY, Modifiers.EMPTY));

            bootstrap.register(EXCITED,
                               Flavor.auto(43520,
                                           Ingredient.of(Items.COCOA_BEANS),
                                           Modifiers.EMPTY));
            bootstrap.register(BOLD,
                               Flavor.auto(16755200,
                                           Ingredient.of(Items.HONEY_BOTTLE),
                                           Modifiers.EMPTY));
            bootstrap.register(MILKY,
                               Flavor.auto(16777215,
                                           Ingredient.of(ItemRegistry.MILK_GELATIN.get()),
                                           Modifiers.MILKY));
        }

        private static ResourceKey<Flavor> of(String name) {
            return DataPackRegistry.of(Flavor.KEY, name);
        }

        private static Set<ResourceKey<Flavor>> flavors() {
            return Set.of(ORIGINAL, EXCITED, BOLD, MILKY);
        }
    }

    public static class Formulas extends ModDataProviders
    {
        public Formulas(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries, new RegistrySetBuilder().add(Formula.KEY, Formulas::register));
        }

        @Override
        public String getName() {
            return "Candy Workshop - Formulas";
        }

        private static void register(BootstrapContext<Formula> bootstrap) {
            // Overworld blends
            registerSimpleSugar(bootstrap, Sugars.SPEED, Effect.simple(MobEffects.MOVEMENT_SPEED));
            registerSimpleSugar(bootstrap, Sugars.BUNNY, Effect.simple(MobEffects.JUMP));
            registerSimpleSugarNoBold(bootstrap, Sugars.HEALING, Effect.instant(MobEffects.HEAL));
            registerSimpleSugar(bootstrap, Sugars.POISON, Effect.simple(MobEffects.POISON));
            registerSimpleSugar(bootstrap, Sugars.PUFFERFISH, Effect.simple(MobEffects.WATER_BREATHING));
            registerSimpleSugarNoExcited(bootstrap, Sugars.NIGHT_VISION, Effect.simple(MobEffects.NIGHT_VISION));
            registerSimpleSugar(bootstrap, Sugars.STRENGTH, Effect.simple(MobEffects.DAMAGE_BOOST));
            registerSimpleSugar(bootstrap, Sugars.RECOVERY, Effect.simple(MobEffects.REGENERATION));
            registerSugar(bootstrap,
                          Sugars.TURTLE,
                          new Effect(MobEffects.MOVEMENT_SLOWDOWN, 100, 3),
                          new Effect(MobEffects.DAMAGE_RESISTANCE, 100, 2))
                    .excited(new Effect(MobEffects.MOVEMENT_SLOWDOWN, 100, 5),
                             new Effect(MobEffects.DAMAGE_RESISTANCE, 100, 3))
                    .bold(new Effect(MobEffects.MOVEMENT_SLOWDOWN, 200, 3),
                          new Effect(MobEffects.DAMAGE_RESISTANCE, 200, 2))
                    .run();
            registerSimpleSugar(bootstrap, Sugars.FLUTTER, Effect.simple(MobEffects.SLOW_FALLING));
            registerSimpleSugar(bootstrap, Sugars.SNAIL, Effect.simple(MobEffects.MOVEMENT_SLOWDOWN));
            registerSimpleSugarNoExcited(bootstrap, Sugars.STINKY, Effect.simple(MobEffects.CONFUSION));
            registerSimpleSugarNoExcited(bootstrap, Sugars.BLINDING, Effect.simple(MobEffects.BLINDNESS));
            registerSimpleSugar(bootstrap, Sugars.WEAKNESS, Effect.simple(MobEffects.WEAKNESS));
            registerSimpleSugarNoExcited(bootstrap, Sugars.BRIGHTNESS, Effect.simple(MobEffects.GLOWING));
            registerSimpleSugarNoExcited(bootstrap, Sugars.DARKNESS, Effect.simple(MobEffects.DARKNESS));
            registerSimpleSugar(bootstrap, Sugars.HUNGER, Effect.simple(MobEffects.HUNGER));

            // Nether blends
            registerSimpleSugarNoExcited(bootstrap, Sugars.INVISIBILITY, Effect.simple(MobEffects.INVISIBILITY));
            registerSimpleSugarNoBold(bootstrap, Sugars.STINGER, Effect.instant(MobEffects.HARM));
            registerSimpleSugarNoExcited(bootstrap, Sugars.BUG, Effect.simple(MobEffects.INFESTED));
            registerSimpleSugarNoExcited(bootstrap, Sugars.STICKY, Effect.simple(MobEffects.OOZING));
            registerSimpleSugarNoExcited(bootstrap, Sugars.BINDING, Effect.simple(MobEffects.WEAVING));
            registerSimpleSugarNoExcited(bootstrap, Sugars.GALE, Effect.simple(MobEffects.WIND_CHARGED));
            registerSimpleSugar(bootstrap, Sugars.REFRESHING, Effect.simple(MobEffects.DIG_SPEED));
            registerSimpleSugar(bootstrap, Sugars.LAZY, Effect.simple(MobEffects.DIG_SLOWDOWN));
            registerSimpleSugar(bootstrap, Sugars.SOLID, Effect.simple(MobEffects.DAMAGE_RESISTANCE));
            registerSimpleSugarNoExcited(bootstrap, Sugars.FIREPROOF, Effect.simple(MobEffects.FIRE_RESISTANCE));
            registerSimpleSugar(bootstrap, Sugars.WITHERING, Effect.simple(MobEffects.WITHER));
            registerSimpleSugar(bootstrap, Sugars.RED_HEART, Effect.simple(MobEffects.HEALTH_BOOST));
            registerSimpleSugarNoExcited(bootstrap, Sugars.FLOATING, Effect.simple(MobEffects.LEVITATION));

            // End blends
            registerSimpleSugar(bootstrap, Sugars.GOLDEN_HEART, Effect.simple(MobEffects.ABSORPTION));
            registerSimpleSugarNoExcited(bootstrap, Sugars.SATIATING, Effect.simple(MobEffects.SATURATION));
            registerSimpleSugarNoExcited(bootstrap, Sugars.LUCKY, Effect.simple(MobEffects.LUCK));
            registerSimpleSugarNoExcited(bootstrap, Sugars.UNLUCKY, Effect.simple(MobEffects.UNLUCK));
            registerSimpleSugarNoExcited(bootstrap, Sugars.TIDAL, Effect.simple(MobEffects.CONDUIT_POWER));
            registerSimpleSugarNoExcited(bootstrap, Sugars.FISH_SWIM, Effect.simple(MobEffects.DOLPHINS_GRACE));
            registerSimpleSugarNoExcited(bootstrap, Sugars.TAUNTING, Effect.simple(MobEffects.BAD_OMEN));
            registerSimpleSugar(bootstrap, Sugars.DISCOUNT, Effect.simple(MobEffects.HERO_OF_THE_VILLAGE));
        }

        private static ResourceKey<Formula> of(String name) {
            return DataPackRegistry.of(Formula.KEY, name);
        }

        private static void registerSimpleSugar(
                BootstrapContext<Formula> bootstrap,
                Holder<Sugar> sugarHolder,
                Effect... effects
        ) {
            registerSimpleSugar(bootstrap, sugarHolder, false, false, effects);
        }

        private static void registerSimpleSugarNoExcited(
                BootstrapContext<Formula> bootstrap,
                Holder<Sugar> sugarHolder,
                Effect... effects
        ) {
            registerSimpleSugar(bootstrap, sugarHolder, true, false, effects);
        }

        private static void registerSimpleSugarNoBold(
                BootstrapContext<Formula> bootstrap,
                Holder<Sugar> sugarHolder,
                Effect... effects
        ) {
            registerSimpleSugar(bootstrap, sugarHolder, false, true, effects);
        }

        private static void registerSimpleSugar(
                BootstrapContext<Formula> bootstrap,
                Holder<Sugar> sugarHolder,
                boolean hasNoExcited,
                boolean hasNoBold,
                Effect... effects
        ) {
            List<Effect> effectList = List.of(effects);
            bootstrap.register(of(Formula.formulaNameOf(CandyWorkshop.getName(sugarHolder), Flavors.ORIGINAL.location().getPath())),
                               new Formula(sugarHolder, Flavors.ORIGINAL.location(), effectList));
            if (!hasNoExcited) {
                bootstrap.register(of(Formula.formulaNameOf(CandyWorkshop.getName(sugarHolder), Flavors.EXCITED.location().getPath())),
                                   new Formula(sugarHolder,
                                               Flavors.EXCITED.location(),
                                               effectList.stream().map(Effect::enhanceAmplifier).toList()));
            }
            if (!hasNoBold) {
                bootstrap.register(of(Formula.formulaNameOf(CandyWorkshop.getName(sugarHolder), Flavors.BOLD.location().getPath())),
                                   new Formula(sugarHolder,
                                               Flavors.BOLD.location(),
                                               effectList.stream().map(Effect::doubleDuration).toList()));
            }
            // Milky didn't modify the effects, the program will fall back to the Original flavor.
//            bootstrap.register(
//                    of(sugarHolder.getRegisteredName() + Flavors.MILKY.location().getPath()),
//                    new Formula(
//                            sugarHolder,
//                            Flavors.MILKY.location(),
//                            List.of(effects)
//                    )
//            );
        }

        private static RegisterContext registerSugar(
                BootstrapContext<Formula> bootstrap,
                Holder<Sugar> sugarHolder,
                Effect... effects
        ) {
            return new RegisterContext(bootstrap, sugarHolder, effects);
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
                bootstrap.register(of(Formula.formulaNameOf(CandyWorkshop.getName(sugarHolder), Flavors.ORIGINAL.location().getPath())),
                                   new Formula(sugarHolder, Flavors.ORIGINAL.location(), originalEffects));
                if (excitedEffects != null) {
                    bootstrap.register(of(Formula.formulaNameOf(CandyWorkshop.getName(sugarHolder), Flavors.EXCITED.location().getPath())),
                                       new Formula(sugarHolder, Flavors.EXCITED.location(), excitedEffects));
                }
                if (boldEffects != null) {
                    bootstrap.register(of(Formula.formulaNameOf(CandyWorkshop.getName(sugarHolder), Flavors.BOLD.location().getPath())),
                                       new Formula(sugarHolder, Flavors.BOLD.location(), boldEffects));
                }
            }
        }
    }
}
