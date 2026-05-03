package com.lnatit.ccw.compat.neapolitan;

import com.lnatit.ccw.item.sugaring.Sugar;
import com.lnatit.ccw.item.sugaring.Sugars;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;

public class NeapolitanCompats
{
    public static final DeferredHolder<Sugar, Sugar> HOOHOO_HAHA = Sugars.SUGARS.register("hoohoo_haha",
                                                                                          () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                          Ingredient.of(
                                                                                                                  NeapolitanItems.DRIED_BANANA.get())));
    public static final DeferredHolder<Sugar, Sugar> HOOHOO_SMOOTH = Sugars.SUGARS.register("hoohoo_smooth",
                                                                                            () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                            Ingredient.of(
                                                                                                                    NeapolitanItems.BANANA.get())));
    public static final DeferredHolder<Sugar, Sugar> MINT = Sugars.SUGARS.register("mint",
                                                                                   () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                   Ingredient.of(
                                                                                                           NeapolitanItems.MINT_LEAVES.get())));
    public static final DeferredHolder<Sugar, Sugar> RED_BEAN = Sugars.SUGARS.register("red_bean",
                                                                                       () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                       Ingredient.of(
                                                                                                               NeapolitanItems.ROASTED_ADZUKI_BEANS.get())));
    public static final DeferredHolder<Sugar, Sugar> VANILLA = Sugars.SUGARS.register("vanilla",
                                                                                      () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                      Ingredient.of(
                                                                                                              NeapolitanItems.DRIED_VANILLA_PODS.get())));
    public static final DeferredHolder<Sugar, Sugar> SUGAR_RUSH = Sugars.SUGARS.register("sugar_rush",
                                                                                         () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                         Ingredient.of(
                                                                                                                 NeapolitanItems.CHOCOLATE_BAR.get())));

    public static void init() {
    }
}
