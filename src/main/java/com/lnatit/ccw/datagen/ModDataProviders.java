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
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class ModDataProviders extends DatapackBuiltinEntriesProvider
{
    private static final Map<ResourceKey<?>, List<ICondition>> CONDITIONS = new HashMap<>();

    public ModDataProviders(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output,
              registries,
              new RegistrySetBuilder().add(Formula.KEY, ModDataProviders::register),
              CONDITIONS,
              Set.of(CandyWorkshop.MODID));
    }

    private static void register(BootstrapContext<Formula> bootstrap) {
        Formulas.CONTEXTS.forEach(context -> context.register(bootstrap));
    }

    @Override
    public String getName() {
        return "Candy Workshop - Datapacks";
    }

    static {
        Formulas.of()
                // Overworld blends
                .register(Sugars.SPEED, Effect.simple(MobEffects.MOVEMENT_SPEED))
                .defaultExcited()
                .defaultBold()

                .register(Sugars.BUNNY, Effect.simple(MobEffects.JUMP))
                .defaultExcited()
                .defaultBold()

                .register(Sugars.HEALING, Effect.instant(MobEffects.HEAL))
                .defaultExcited()

                .register(Sugars.POISON, Effect.simple(MobEffects.POISON))
                .defaultExcited()
                .defaultBold()

                .register(Sugars.PUFFERFISH, Effect.simple(MobEffects.WATER_BREATHING))
                .defaultExcited()
                .defaultBold()

                .register(Sugars.NIGHT_VISION, Effect.simple(MobEffects.NIGHT_VISION))
                .defaultBold()

                .register(Sugars.STRENGTH, Effect.simple(MobEffects.DAMAGE_BOOST))
                .defaultExcited()
                .defaultBold()

                .register(Sugars.RECOVERY, Effect.simple(MobEffects.REGENERATION))
                .defaultExcited()
                .defaultBold()

                .register(Sugars.TURTLE,
                          new Effect(MobEffects.MOVEMENT_SLOWDOWN, 100, 3),
                          new Effect(MobEffects.DAMAGE_RESISTANCE, 100, 2))
                .excited(new Effect(MobEffects.MOVEMENT_SLOWDOWN, 100, 5),
                         new Effect(MobEffects.DAMAGE_RESISTANCE, 100, 3))
                .bold(new Effect(MobEffects.MOVEMENT_SLOWDOWN, 200, 3),
                      new Effect(MobEffects.DAMAGE_RESISTANCE, 200, 2))

                .register(Sugars.FLUTTER, Effect.simple(MobEffects.SLOW_FALLING))
                .defaultExcited()
                .defaultBold()

                .register(Sugars.SNAIL, Effect.simple(MobEffects.MOVEMENT_SLOWDOWN))
                .defaultExcited()
                .defaultBold()

                .register(Sugars.STINKY, Effect.simple(MobEffects.CONFUSION))
                .defaultBold()

                .register(Sugars.BLINDING, Effect.simple(MobEffects.BLINDNESS))
                .defaultBold()

                .register(Sugars.WEAKNESS, Effect.simple(MobEffects.WEAKNESS))
                .defaultExcited()
                .defaultBold()

                .register(Sugars.BRIGHTNESS, Effect.simple(MobEffects.GLOWING))
                .defaultBold()

                .register(Sugars.DARKNESS, Effect.simple(MobEffects.DARKNESS))
                .defaultBold()

                .register(Sugars.HUNGER, Effect.simple(MobEffects.HUNGER))
                .defaultExcited()
                .defaultBold()

                // Nether blends
                .register(Sugars.INVISIBILITY, Effect.simple(MobEffects.INVISIBILITY))
                .defaultBold()

                .register(Sugars.STINGER, Effect.instant(MobEffects.HARM))
                .defaultExcited()

                .register(Sugars.BUG, Effect.simple(MobEffects.INFESTED))
                .defaultBold()

                .register(Sugars.STICKY, Effect.simple(MobEffects.OOZING))
                .defaultBold()

                .register(Sugars.BINDING, Effect.simple(MobEffects.WEAVING))
                .defaultBold()

                .register(Sugars.GALE, Effect.simple(MobEffects.WIND_CHARGED))
                .defaultBold()

                .register(Sugars.REFRESHING, Effect.simple(MobEffects.DIG_SPEED))
                .defaultExcited()
                .defaultBold()

                .register(Sugars.LAZY, Effect.simple(MobEffects.DIG_SLOWDOWN))
                .defaultExcited()
                .defaultBold()

                .register(Sugars.SOLID, Effect.simple(MobEffects.DAMAGE_RESISTANCE))
                .defaultExcited()
                .defaultBold()

                .register(Sugars.FIREPROOF, Effect.simple(MobEffects.FIRE_RESISTANCE))
                .defaultBold()

                .register(Sugars.WITHERING, Effect.simple(MobEffects.WITHER))
                .defaultExcited()
                .defaultBold()

                .register(Sugars.RED_HEART, Effect.simple(MobEffects.HEALTH_BOOST))
                .defaultExcited()
                .defaultBold()

                .register(Sugars.FLOATING, Effect.simple(MobEffects.LEVITATION))
                .defaultBold()

                // End blends
                .register(Sugars.GOLDEN_HEART, Effect.simple(MobEffects.ABSORPTION))
                .defaultExcited()
                .defaultBold()

                .register(Sugars.SATIATING, Effect.simple(MobEffects.SATURATION))
                .defaultBold()

                .register(Sugars.LUCKY, Effect.simple(MobEffects.LUCK))
                .defaultBold()

                .register(Sugars.UNLUCKY, Effect.simple(MobEffects.UNLUCK))
                .defaultBold()

                .register(Sugars.TIDAL, Effect.simple(MobEffects.CONDUIT_POWER))
                .defaultBold()

                .register(Sugars.FISH_SWIM, Effect.simple(MobEffects.DOLPHINS_GRACE))
                .defaultBold()

                .register(Sugars.TAUNTING, Effect.simple(MobEffects.BAD_OMEN))
                .defaultBold()

                .register(Sugars.DISCOUNT, Effect.simple(MobEffects.HERO_OF_THE_VILLAGE))
                .defaultExcited()
                .defaultBold()

                .loaded("apotheosis")
                .clearConditions()
                .loaded("farmersdelight")
                .clearConditions()
                .loaded("fruitsdelight")
                .clearConditions()
                .loaded("youkaishomecoming")
                .clearConditions()
                .loaded("neapolitan")
                .clearConditions();
    }


    private record Context<V>(ResourceKey<V> key, V value)
    {
        public void register(BootstrapContext<V> context) {
            context.register(key, value);
        }
    }

    private static class Formulas
    {
        private static final List<Context<Formula>> CONTEXTS = new ArrayList<>();
        private List<ICondition> conditions = List.of();
        @Nullable
        private Holder<Sugar> sugar;
        private List<Effect> effects = List.of();

        public static Formulas of() {
            return new Formulas();
        }

        public Formulas with(ICondition... conditions) {
            this.conditions = List.of(conditions);
            return this;
        }

        public Formulas loaded(String modid) {
            return with(new ModLoadedCondition(modid));
        }

        public Formulas clearConditions() {
            this.conditions = List.of();
            return this;
        }

        public Formulas register(Holder<Sugar> sugar, Effect... effects) {
            this.sugar = sugar;
            this.effects = List.of(effects);
            append(Flavors.ORIGINAL);
            return this;
        }

        public Formulas excited(Effect... effects) {
            append(Flavors.EXCITED, List.of(effects));
            return this;
        }

        public Formulas defaultExcited() {
            return this.excited(this.effects.stream().map(Effect::enhanceAmplifier).toArray(Effect[]::new));
        }

        public Formulas bold(Effect... effects) {
            append(Flavors.BOLD, List.of(effects));
            return this;
        }

        public Formulas defaultBold() {
            return this.bold(this.effects.stream().map(Effect::doubleDuration).toArray(Effect[]::new));
        }

        public Formulas add(Holder<Flavor> flavor, Effect... effects) {
            append(flavor, List.of(effects));
            return this;
        }

        private ResourceKey<Formula> of(Holder<Flavor> flavor) {
            String sugarName = ((Holder<?>) this.sugar).getKey().location().getPath();
            String flavorName = ((Holder<?>) flavor).getKey().location().getPath();
            return DataPackRegistry.of(Formula.KEY, sugarName + "_" + flavorName);
        }

        private void append(Holder<Flavor> flavor) {
            this.append(flavor, this.effects);
        }

        private void append(Holder<Flavor> flavor, List<Effect> effects) {
            if (!this.effects.isEmpty()) {
                ResourceKey<Formula> key = of(flavor);
                CONTEXTS.add(new Context<>(key, new Formula(this.sugar, Flavors.ORIGINAL, effects)));
                if (!conditions.isEmpty()) {
                    CONDITIONS.put(key, conditions);
                }
            }
        }
    }
}
