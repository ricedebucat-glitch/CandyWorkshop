package com.lnatit.ccw.compat.fruitsdelight;

import com.lnatit.ccw.item.sugaring.Sugar;
import com.lnatit.ccw.item.sugaring.Sugars;
import dev.xkmc.fruitsdelight.init.food.FruitType;
import dev.xkmc.fruitsdelight.init.registrate.FDItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;

public class FruitsDelightCompats
{
    public static final DeferredHolder<Sugar, Sugar> BLUEBERRY = Sugars.SUGARS.register("blueberry",
                                                                                        () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                        Ingredient.of(
                                                                                                                getJello(
                                                                                                                        FruitType.BLUEBERRY))));

    public static final DeferredHolder<Sugar, Sugar> HAWTHORN = Sugars.SUGARS.register("hawthorn",
                                                                                       () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                       Ingredient.of(
                                                                                                               getJello(
                                                                                                                       FruitType.HAWBERRY))));

    public static final DeferredHolder<Sugar, Sugar> MANGO = Sugars.SUGARS.register("mango",
                                                                                    () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                    Ingredient.of(
                                                                                                            getJello(
                                                                                                                    FruitType.MANGO))));

    public static final DeferredHolder<Sugar, Sugar> ORANGE = Sugars.SUGARS.register("orange",
                                                                                     () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                     Ingredient.of(
                                                                                                             getJello(
                                                                                                                     FruitType.ORANGE))));

    public static final DeferredHolder<Sugar, Sugar> PEACH = Sugars.SUGARS.register("peach",
                                                                                    () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                    Ingredient.of(
                                                                                                            getJello(
                                                                                                                    FruitType.PEACH))));

    public static final DeferredHolder<Sugar, Sugar> PEAR = Sugars.SUGARS.register("snow_pear",
                                                                                   () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                   Ingredient.of(
                                                                                                           getJello(
                                                                                                                   FruitType.PEAR))));

    public static final DeferredHolder<Sugar, Sugar> PERSIMMON = Sugars.SUGARS.register("persimmon",
                                                                                        () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                        Ingredient.of(
                                                                                                                getJello(
                                                                                                                        FruitType.PERSIMMON))));

    public static final DeferredHolder<Sugar, Sugar> PINEAPPLE = Sugars.SUGARS.register("pineapple",
                                                                                        () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                        Ingredient.of(
                                                                                                                getJello(
                                                                                                                        FruitType.PINEAPPLE))));

    public static final DeferredHolder<Sugar, Sugar> LEMON = Sugars.SUGARS.register("lemon",
                                                                                    () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                    Ingredient.of(
                                                                                                            getJello(
                                                                                                                    FruitType.LEMON))));

    public static final DeferredHolder<Sugar, Sugar> CRANBERRY = Sugars.SUGARS.register("cranberry",
                                                                                        () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                        Ingredient.of(
                                                                                                                getJello(
                                                                                                                        FruitType.CRANBERRY))));

    public static final DeferredHolder<Sugar, Sugar> MANGOSTEEN = Sugars.SUGARS.register("mangosteen",
                                                                                         () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                         Ingredient.of(
                                                                                                                 getJello(
                                                                                                                         FruitType.MANGOSTEEN))));

    public static final DeferredHolder<Sugar, Sugar> CHORUS = Sugars.SUGARS.register("chorus",
                                                                                     () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                     Ingredient.of(
                                                                                                             getJello(
                                                                                                                     FruitType.CHORUS))));

    public static final DeferredHolder<Sugar, Sugar> BAYBERRY = Sugars.SUGARS.register("bayberry",
                                                                                       () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                       Ingredient.of(
                                                                                                               getJello(
                                                                                                                       FruitType.BAYBERRY))));

    public static final DeferredHolder<Sugar, Sugar> KIWI = Sugars.SUGARS.register("kiwi",
                                                                                   () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                   Ingredient.of(
                                                                                                           getJello(
                                                                                                                   FruitType.KIWI))));

    public static final DeferredHolder<Sugar, Sugar> FIG = Sugars.SUGARS.register("fig",
                                                                                  () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                  Ingredient.of(getJello(
                                                                                                          FruitType.FIG))));

    public static final DeferredHolder<Sugar, Sugar> DURIAN = Sugars.SUGARS.register("durian",
                                                                                     () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                     Ingredient.of(
                                                                                                             getJello(
                                                                                                                     FruitType.DURIAN))));

    public static void init() {
    }

    private static Item getJello(FruitType type) {
        return FDItems.JELLO[type.ordinal()].get();
    }
}
