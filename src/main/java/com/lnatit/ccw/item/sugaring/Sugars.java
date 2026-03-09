package com.lnatit.ccw.item.sugaring;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.item.ItemRegistry;
import com.lnatit.ccw.misc.RegRegistry;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @see MobEffects
 * Excited: amplified
 * Bold: extended
 */
public class Sugars
{
    public static final DeferredRegister<Sugar> SUGARS = DeferredRegister.create(RegRegistry.SUGAR_KEY,
                                                                                 CandyWorkshop.MODID);

    // Overworld blends
    public static final DeferredHolder<Sugar, Sugar> SPEED = SUGARS.register("speed",
                                                                             () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                             Ingredient.of(Items.SUGAR)));
    public static final DeferredHolder<Sugar, Sugar> BUNNY = SUGARS.register("bunny",
                                                                             () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                             Ingredient.of(Items.RABBIT_FOOT,
                                                                                                           ItemRegistry.ENERGY_CARROT.get())));

    public static final DeferredHolder<Sugar, Sugar> HEALING = SUGARS.register("healing",
                                                                               () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                               Ingredient.of(Items.GLISTERING_MELON_SLICE)));
    public static final DeferredHolder<Sugar, Sugar> POISON = SUGARS.register("poison",
                                                                              () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                              Ingredient.of(Items.SPIDER_EYE)));
    public static final DeferredHolder<Sugar, Sugar> PUFFERFISH = SUGARS.register("pufferfish",
                                                                                  () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                  Ingredient.of(Items.PUFFERFISH)));
    public static final DeferredHolder<Sugar, Sugar> NIGHT_VISION = SUGARS.register("night_vision",
                                                                                    () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                    Ingredient.of(Items.GOLDEN_CARROT)));
    public static final DeferredHolder<Sugar, Sugar> STRENGTH = SUGARS.register("strength",
                                                                                () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                Ingredient.of(Items.BLAZE_POWDER)));
    public static final DeferredHolder<Sugar, Sugar> RECOVERY = SUGARS.register("recovery",
                                                                                () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                Ingredient.of(Items.GHAST_TEAR,
                                                                                                              ItemRegistry.SWEET_MELON_SLICE.get())));

    public static final DeferredHolder<Sugar, Sugar> TURTLE = SUGARS.register("turtle",
                                                                              () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                              Ingredient.of(Items.TURTLE_SCUTE)));
    public static final DeferredHolder<Sugar, Sugar> FLUTTER = SUGARS.register("flutter",
                                                                               () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                               Ingredient.of(Items.PHANTOM_MEMBRANE)));
    public static final DeferredHolder<Sugar, Sugar> SNAIL = SUGARS.register("snail",
                                                                             () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                             Ingredient.of(Items.SOUL_SAND)));
    public static final DeferredHolder<Sugar, Sugar> STINKY = SUGARS.register("stinky",
                                                                              () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                              Ingredient.of(Tags.Items.MUSHROOMS)));

    public static final DeferredHolder<Sugar, Sugar> BLINDING = SUGARS.register("blinding",
                                                                                () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                Ingredient.of(
                                                                                                        ItemRegistry.VOID_CARROT.get())));


    public static final DeferredHolder<Sugar, Sugar> WEAKNESS = SUGARS.register("weakness",
                                                                                () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                Ingredient.of(
                                                                                                        ItemRegistry.WEAKNESS_POWDER.get())));

    public static final DeferredHolder<Sugar, Sugar> BRIGHTNESS = SUGARS.register("brightness",
                                                                                  () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                  Ingredient.of(Items.GLOW_BERRIES)));
    public static final DeferredHolder<Sugar, Sugar> DARKNESS = SUGARS.register("darkness",
                                                                                () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                                Ingredient.of(Items.BLACK_DYE)));
    public static final DeferredHolder<Sugar, Sugar> HUNGER = SUGARS.register("hunger",
                                                                              () -> new Sugar(Sugar.Type.OVERWORLD,
                                                                                              Ingredient.of(Items.ROTTEN_FLESH)));

    // Nether blends
    public static final DeferredHolder<Sugar, Sugar> INVISIBILITY = SUGARS.register("invisibility",
                                                                                    () -> new Sugar(Sugar.Type.NETHER,
                                                                                                    Ingredient.of(
                                                                                                            ItemRegistry.PHANTOM_PEARL.get())));


    public static final DeferredHolder<Sugar, Sugar> STINGER = SUGARS.register("stinger",
                                                                               () -> new Sugar(Sugar.Type.NETHER,
                                                                                               Ingredient.of(Items.CACTUS)));
    public static final DeferredHolder<Sugar, Sugar> BUG = SUGARS.register("bug",
                                                                           () -> new Sugar(Sugar.Type.NETHER,
                                                                                           Ingredient.of(Items.STONE)));
    public static final DeferredHolder<Sugar, Sugar> STICKY = SUGARS.register("sticky",
                                                                              () -> new Sugar(Sugar.Type.NETHER,
                                                                                              Ingredient.of(Items.SLIME_BLOCK)));
    public static final DeferredHolder<Sugar, Sugar> BINDING = SUGARS.register("binding",
                                                                               () -> new Sugar(Sugar.Type.NETHER,
                                                                                               Ingredient.of(Items.COBWEB)));
    public static final DeferredHolder<Sugar, Sugar> GALE = SUGARS.register("gale",
                                                                            () -> new Sugar(Sugar.Type.NETHER,
                                                                                            Ingredient.of(Items.BREEZE_ROD)));
    public static final DeferredHolder<Sugar, Sugar> REFRESHING = SUGARS.register("refreshing",
                                                                                  () -> new Sugar(Sugar.Type.NETHER,
                                                                                                  Ingredient.of(Items.COCOA_BEANS)));
    public static final DeferredHolder<Sugar, Sugar> LAZY = SUGARS.register("lazy",
                                                                            () -> new Sugar(Sugar.Type.NETHER,
                                                                                            Ingredient.of(Items.COBBLESTONE)));
    public static final DeferredHolder<Sugar, Sugar> SOLID = SUGARS.register("solid",
                                                                             () -> new Sugar(Sugar.Type.NETHER,
                                                                                             Ingredient.of(ItemRegistry.CALCIUM_RICH_MILK.get())));


    public static final DeferredHolder<Sugar, Sugar> FIREPROOF = SUGARS.register("fireproof",
                                                                                 () -> new Sugar(Sugar.Type.NETHER,
                                                                                                 Ingredient.of(Items.MAGMA_CREAM,
                                                                                                               Items.OBSIDIAN)));
    public static final DeferredHolder<Sugar, Sugar> WITHERING = SUGARS.register("withering",
                                                                                 () -> new Sugar(Sugar.Type.NETHER,
                                                                                                 Ingredient.of(Items.WITHER_ROSE)));
    public static final DeferredHolder<Sugar, Sugar> RED_HEART = SUGARS.register("red_heart",
                                                                                 () -> new Sugar(Sugar.Type.NETHER,
                                                                                                 Ingredient.of(
                                                                                                         ItemRegistry.IRON_CLAD_APPLE.get())));


    public static final DeferredHolder<Sugar, Sugar> FLOATING = SUGARS.register("floating",
                                                                                () -> new Sugar(Sugar.Type.NETHER,
                                                                                                Ingredient.of(Items.SHULKER_SHELL)));

    // End blends
    public static final DeferredHolder<Sugar, Sugar> GOLDEN_HEART = SUGARS.register("golden_heart",
                                                                                    () -> new Sugar(Sugar.Type.END,
                                                                                                    Ingredient.of(
                                                                                                            ItemRegistry.GOLD_STUDDED_APPLE.get())));


    public static final DeferredHolder<Sugar, Sugar> SATIATING = SUGARS.register("satiating",
                                                                                 () -> new Sugar(Sugar.Type.END,
                                                                                                 Ingredient.of(
                                                                                                         ItemRegistry.BLESSED_STEAK.get())));

    public static final DeferredHolder<Sugar, Sugar> LUCKY = SUGARS.register("lucky",
                                                                             () -> new Sugar(Sugar.Type.END,
                                                                                             Ingredient.of(ItemRegistry.GREEDY_OFFERING.get())));


    public static final DeferredHolder<Sugar, Sugar> UNLUCKY = SUGARS.register("unlucky",
                                                                               () -> new Sugar(Sugar.Type.END,
                                                                                               Ingredient.of(
                                                                                                       ItemRegistry.DEFILED_OFFERING.get())));

    public static final DeferredHolder<Sugar, Sugar> TIDAL = SUGARS.register("tidal",
                                                                             () -> new Sugar(Sugar.Type.END,
                                                                                             Ingredient.of(Items.NAUTILUS_SHELL)));
    public static final DeferredHolder<Sugar, Sugar> FISH_SWIM = SUGARS.register("fish_swim",
                                                                                 () -> new Sugar(Sugar.Type.END,
                                                                                                 Ingredient.of(
                                                                                                         ItemRegistry.DOLPHIN_COOKIE.get())));

    public static final DeferredHolder<Sugar, Sugar> TAUNTING = SUGARS.register("taunting",
                                                                                () -> new Sugar(Sugar.Type.END,
                                                                                                Ingredient.of(
                                                                                                        ItemRegistry.OMINOUS_FLAG.get())));

    public static final DeferredHolder<Sugar, Sugar> DISCOUNT = SUGARS.register("discount",
                                                                                () -> new Sugar(Sugar.Type.END,
                                                                                                Ingredient.of(Items.EMERALD_BLOCK)));

    public static DeferredHolder<Sugar, Sugar> registerUni(String name, Sugar.Type type, Ingredient ingredient) {
        return SUGARS.register(name, () -> new Sugar(type, ingredient));
    }

    public static DeferredHolder<Sugar, Sugar> registerUni(String name, Sugar.Type type, ItemLike... ingredients) {
        return SUGARS.register(name, () -> new Sugar(type, Ingredient.of(ingredients)));
    }
}
