package com.lnatit.ccw.datagen;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.data.DataPackRegistry;
import com.lnatit.ccw.data.Effect;
import com.lnatit.ccw.data.Formula;
import com.lnatit.ccw.item.sugaring.Flavor;
import com.lnatit.ccw.item.sugaring.Flavors;
import com.lnatit.ccw.item.sugaring.Sugar;
import com.lnatit.ccw.item.sugaring.Sugars;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDataProviders extends DatapackBuiltinEntriesProvider
{
    public static final Map<ResourceKey<?>, List<ICondition>> CONDITIONS = new HashMap<>();

    public ModDataProviders(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output,
              registries,
              new RegistrySetBuilder().add(Formula.KEY, ModDataProviders::register),
              CONDITIONS,
              Set.of(CandyWorkshop.MODID));
    }

    private static void register(BootstrapContext<Formula> bootstrap) {
        Builtin.INSTANCE.register(bootstrap);
    }

    @Override
    public String getName() {
        return "Candy Workshop - Datapacks";
    }

    private interface Entry
    {
        default HalfContext registerSugar(
                BootstrapContext<Formula> bootstrap,
                Holder<Sugar> sugarHolder,
                Effect... effects
        ) {
            return new RegisterContext(bootstrap, sugarHolder, effects);
        }

        void register(BootstrapContext<Formula> bootstrap);
    }

    private enum Builtin implements Entry
    {
        INSTANCE;

        @Override
        public void register(BootstrapContext<Formula> bootstrap) {
            // Overworld blends
            this.registerSugar(bootstrap, Sugars.SPEED, Effect.simple(MobEffects.MOVEMENT_SPEED))
                .defaultExcited()
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.BUNNY, Effect.simple(MobEffects.JUMP))
                .defaultExcited()
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.HEALING, Effect.instant(MobEffects.HEAL)).defaultExcited().run();
            this.registerSugar(bootstrap, Sugars.POISON, Effect.simple(MobEffects.POISON))
                .defaultExcited()
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.PUFFERFISH, Effect.simple(MobEffects.WATER_BREATHING))
                .defaultExcited()
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.NIGHT_VISION, Effect.simple(MobEffects.NIGHT_VISION))
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.STRENGTH, Effect.simple(MobEffects.DAMAGE_BOOST))
                .defaultExcited()
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.RECOVERY, Effect.simple(MobEffects.REGENERATION))
                .defaultExcited()
                .defaultBold()
                .run();
            this.registerSugar(bootstrap,
                               Sugars.TURTLE,
                               new Effect(MobEffects.MOVEMENT_SLOWDOWN, 100, 3),
                               new Effect(MobEffects.DAMAGE_RESISTANCE, 100, 2))
                .excited(new Effect(MobEffects.MOVEMENT_SLOWDOWN, 100, 5),
                         new Effect(MobEffects.DAMAGE_RESISTANCE, 100, 3))
                .bold(new Effect(MobEffects.MOVEMENT_SLOWDOWN, 200, 3),
                      new Effect(MobEffects.DAMAGE_RESISTANCE, 200, 2))
                .run();
            this.registerSugar(bootstrap, Sugars.FLUTTER, Effect.simple(MobEffects.SLOW_FALLING))
                .defaultExcited()
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.SNAIL, Effect.simple(MobEffects.MOVEMENT_SLOWDOWN))
                .defaultExcited()
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.STINKY, Effect.simple(MobEffects.CONFUSION)).defaultBold().run();
            this.registerSugar(bootstrap, Sugars.BLINDING, Effect.simple(MobEffects.BLINDNESS)).defaultBold().run();
            this.registerSugar(bootstrap, Sugars.WEAKNESS, Effect.simple(MobEffects.WEAKNESS))
                .defaultExcited()
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.BRIGHTNESS, Effect.simple(MobEffects.GLOWING)).defaultBold().run();
            this.registerSugar(bootstrap, Sugars.DARKNESS, Effect.simple(MobEffects.DARKNESS)).defaultBold().run();
            this.registerSugar(bootstrap, Sugars.HUNGER, Effect.simple(MobEffects.HUNGER))
                .defaultExcited()
                .defaultBold()
                .run();

            // Nether blends
            this.registerSugar(bootstrap, Sugars.INVISIBILITY, Effect.simple(MobEffects.INVISIBILITY))
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.STINGER, Effect.instant(MobEffects.HARM)).defaultExcited().run();
            this.registerSugar(bootstrap, Sugars.BUG, Effect.simple(MobEffects.INFESTED)).defaultBold().run();
            this.registerSugar(bootstrap, Sugars.STICKY, Effect.simple(MobEffects.OOZING)).defaultBold().run();
            this.registerSugar(bootstrap, Sugars.BINDING, Effect.simple(MobEffects.WEAVING)).defaultBold().run();
            this.registerSugar(bootstrap, Sugars.GALE, Effect.simple(MobEffects.WIND_CHARGED)).defaultBold().run();
            this.registerSugar(bootstrap, Sugars.REFRESHING, Effect.simple(MobEffects.DIG_SPEED))
                .defaultExcited()
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.LAZY, Effect.simple(MobEffects.DIG_SLOWDOWN))
                .defaultExcited()
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.SOLID, Effect.simple(MobEffects.DAMAGE_RESISTANCE))
                .defaultExcited()
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.FIREPROOF, Effect.simple(MobEffects.FIRE_RESISTANCE))
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.WITHERING, Effect.simple(MobEffects.WITHER))
                .defaultExcited()
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.RED_HEART, Effect.simple(MobEffects.HEALTH_BOOST))
                .defaultExcited()
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.FLOATING, Effect.simple(MobEffects.LEVITATION)).defaultBold().run();

            // End blends
            this.registerSugar(bootstrap, Sugars.GOLDEN_HEART, Effect.simple(MobEffects.ABSORPTION))
                .defaultExcited()
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.SATIATING, Effect.simple(MobEffects.SATURATION)).defaultBold().run();
            this.registerSugar(bootstrap, Sugars.LUCKY, Effect.simple(MobEffects.LUCK)).defaultBold().run();
            this.registerSugar(bootstrap, Sugars.UNLUCKY, Effect.simple(MobEffects.UNLUCK)).defaultBold().run();
            this.registerSugar(bootstrap, Sugars.TIDAL, Effect.simple(MobEffects.CONDUIT_POWER)).defaultBold().run();
            this.registerSugar(bootstrap, Sugars.FISH_SWIM, Effect.simple(MobEffects.DOLPHINS_GRACE))
                .defaultBold()
                .run();
            this.registerSugar(bootstrap, Sugars.TAUNTING, Effect.simple(MobEffects.BAD_OMEN)).defaultBold().run();
            this.registerSugar(bootstrap, Sugars.DISCOUNT, Effect.simple(MobEffects.HERO_OF_THE_VILLAGE))
                .defaultExcited()
                .defaultBold()
                .run();
        }
    }

    private enum Apotheosis implements Entry
    {
        INSTANCE;

        @Override
        public HalfContext registerSugar(
                BootstrapContext<Formula> bootstrap,
                Holder<Sugar> sugarHolder,
                Effect... effects
        ) {
            return Entry.super.registerSugar(bootstrap, sugarHolder, effects)
                              .loaded("apotheosis");
        }

        @Override
        public void register(BootstrapContext<Formula> bootstrap) {

        }
    }

    private enum FarmersDelight implements Entry
    {
        INSTANCE;

        @Override
        public HalfContext registerSugar(
                BootstrapContext<Formula> bootstrap,
                Holder<Sugar> sugarHolder,
                Effect... effects
        ) {
            return Entry.super.registerSugar(bootstrap, sugarHolder, effects)
                              .loaded("farmersdelight");
        }

        @Override
        public void register(BootstrapContext<Formula> bootstrap) {

        }
    }

    private enum FruitsDelight implements Entry
    {
        INSTANCE;

        @Override
        public HalfContext registerSugar(
                BootstrapContext<Formula> bootstrap,
                Holder<Sugar> sugarHolder,
                Effect... effects
        ) {
            return Entry.super.registerSugar(bootstrap, sugarHolder, effects)
                              .loaded("fruitsdelight");
        }

        @Override
        public void register(BootstrapContext<Formula> bootstrap) {

        }
    }

    private enum YoukaisHomecoming implements Entry
    {
        INSTANCE;

        @Override
        public HalfContext registerSugar(
                BootstrapContext<Formula> bootstrap,
                Holder<Sugar> sugarHolder,
                Effect... effects
        ) {
            return Entry.super.registerSugar(bootstrap, sugarHolder, effects)
                              .loaded("youkaishomecoming");
        }

        @Override
        public void register(BootstrapContext<Formula> bootstrap) {

        }
    }

    private enum Neapolitan implements Entry
    {
        INSTANCE;

        @Override
        public HalfContext registerSugar(
                BootstrapContext<Formula> bootstrap,
                Holder<Sugar> sugarHolder,
                Effect... effects
        ) {
            return Entry.super.registerSugar(bootstrap, sugarHolder, effects)
                              .loaded("neapolitan");
        }

        @Override
        public void register(BootstrapContext<Formula> bootstrap) {

        }
    }

    // Helper methods & classes below...
    private interface HalfContext extends Runnable
    {
        HalfContext addConditions(List<ICondition> conditions);

        default HalfContext loaded(String modid) {
            return addConditions(List.of(new ModLoadedCondition(modid)));
        }

        List<Effect> getOriginalEffects();

        HalfContext excited(Effect... effects);

        default HalfContext defaultExcited() {
            return excited(getOriginalEffects().stream().map(Effect::enhanceAmplifier).toArray(Effect[]::new));
        }

        HalfContext bold(Effect... effects);

        default HalfContext defaultBold() {
            return bold(getOriginalEffects().stream().map(Effect::doubleDuration).toArray(Effect[]::new));
        }
    }

    private static class RegisterContext implements HalfContext
    {
        BootstrapContext<Formula> bootstrap;
        Holder<Sugar> sugarHolder;
        List<Effect> originalEffects;
        @Nullable
        List<ICondition> conditions = null;
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
        public HalfContext addConditions(List<ICondition> conditions) {
            if (this.conditions == null) {
                this.conditions = conditions;
            }
            else {
                this.conditions.addAll(conditions);
            }
            return this;
        }

        @Override
        public List<Effect> getOriginalEffects() {
            return this.originalEffects;
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
            if (this.conditions != null) {
                CONDITIONS.put(of(sugarHolder, Flavors.ORIGINAL), this.conditions);
                if (excitedEffects != null) {
                    CONDITIONS.put(of(sugarHolder, Flavors.EXCITED), this.conditions);
                }
                if (boldEffects != null) {
                    CONDITIONS.put(of(sugarHolder, Flavors.BOLD), this.conditions);
                }
            }

            bootstrap.register(of(sugarHolder, Flavors.ORIGINAL),
                               new Formula(sugarHolder, Flavors.ORIGINAL, originalEffects));
            if (excitedEffects != null) {
                bootstrap.register(of(sugarHolder, Flavors.EXCITED),
                                   new Formula(sugarHolder, Flavors.EXCITED, excitedEffects));
            }
            if (boldEffects != null) {
                bootstrap.register(of(sugarHolder, Flavors.BOLD), new Formula(sugarHolder, Flavors.BOLD, boldEffects));
            }
        }

        public static ResourceKey<Formula> of(Holder<Sugar> sugar, Holder<Flavor> flavor) {
            String sugarName = ((Holder<?>) sugar).getKey().location().getPath();
            String flavorName = ((Holder<?>) flavor).getKey().location().getPath();
            return DataPackRegistry.of(Formula.KEY, sugarName + "_" + flavorName);
        }
    }
}
