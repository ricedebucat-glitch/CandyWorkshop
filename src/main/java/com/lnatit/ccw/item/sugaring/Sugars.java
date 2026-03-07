package com.lnatit.ccw.item.sugaring;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.item.ItemRegistry;
import com.lnatit.ccw.misc.RegRegistry;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
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
    public static final DeferredRegister<Sugar> SUGARS =
            DeferredRegister.create(RegRegistry.SUGAR_KEY, CandyWorkshop.MODID);

    // Overworld blends
    public static final DeferredHolder<Sugar, Sugar> SPEED =
            registerUni("speed", Sugar.Type.OVERWORLD, Items.SUGAR);
    public static final DeferredHolder<Sugar, Sugar> BUNNY =
            registerUni("bunny", Sugar.Type.OVERWORLD, Items.RABBIT_FOOT, ItemRegistry.ENERGY_CARROT.get());
    public static final DeferredHolder<Sugar, Sugar> HEALING =
            registerUni("healing", Sugar.Type.OVERWORLD, Items.GLISTERING_MELON_SLICE);
    public static final DeferredHolder<Sugar, Sugar> POISON =
            registerUni("poison", Sugar.Type.OVERWORLD, Items.SPIDER_EYE);
    public static final DeferredHolder<Sugar, Sugar> PUFFERFISH =
            registerUni("pufferfish", Sugar.Type.OVERWORLD, Items.PUFFERFISH);
    public static final DeferredHolder<Sugar, Sugar> NIGHT_VISION =
            registerUni("night_vision", Sugar.Type.OVERWORLD, Items.GOLDEN_CARROT);
    public static final DeferredHolder<Sugar, Sugar> STRENGTH =
            registerUni("strength", Sugar.Type.OVERWORLD, Items.BLAZE_POWDER);
    public static final DeferredHolder<Sugar, Sugar> RECOVERY =
            registerUni("recovery", Sugar.Type.OVERWORLD, Items.GHAST_TEAR, ItemRegistry.SWEET_MELON_SLICE.get());
    public static final DeferredHolder<Sugar, Sugar> TURTLE =
            registerUni("turtle", Sugar.Type.OVERWORLD, Items.TURTLE_SCUTE);
    public static final DeferredHolder<Sugar, Sugar> FLUTTER =
            registerUni("flutter", Sugar.Type.OVERWORLD, Items.PHANTOM_MEMBRANE);
    public static final DeferredHolder<Sugar, Sugar> SNAIL =
            registerUni("snail", Sugar.Type.OVERWORLD, Items.SOUL_SAND);
    public static final DeferredHolder<Sugar, Sugar> STINKY =
            registerUni("stinky", Sugar.Type.OVERWORLD, Ingredient.of(Tags.Items.MUSHROOMS));
    public static final DeferredHolder<Sugar, Sugar> BLINDING =
            registerUni("blinding", Sugar.Type.OVERWORLD, ItemRegistry.VOID_CARROT.get());
    public static final DeferredHolder<Sugar, Sugar> WEAKNESS =
            registerUni("weakness", Sugar.Type.OVERWORLD, ItemRegistry.WEAKNESS_POWDER.get());
    public static final DeferredHolder<Sugar, Sugar> BRIGHTNESS =
            registerUni("brightness", Sugar.Type.OVERWORLD, Items.GLOW_BERRIES);
    public static final DeferredHolder<Sugar, Sugar> DARKNESS =
            registerUni("darkness", Sugar.Type.OVERWORLD, Items.BLACK_DYE);
    public static final DeferredHolder<Sugar, Sugar> HUNGER =
            registerUni("hunger", Sugar.Type.OVERWORLD, Items.ROTTEN_FLESH);

    // Nether blends
    public static final DeferredHolder<Sugar, Sugar> INVISIBILITY =
            registerUni("invisibility", Sugar.Type.NETHER, ItemRegistry.PHANTOM_PEARL.get());
    public static final DeferredHolder<Sugar, Sugar> STINGER =
            registerUni("stinger", Sugar.Type.NETHER, Items.CACTUS);
    public static final DeferredHolder<Sugar, Sugar> BUG =
            registerUni("bug", Sugar.Type.NETHER, Items.STONE);
    public static final DeferredHolder<Sugar, Sugar> STICKY =
            registerUni("sticky", Sugar.Type.NETHER, Items.SLIME_BLOCK);
    public static final DeferredHolder<Sugar, Sugar> BINDING =
            registerUni("binding", Sugar.Type.NETHER, Items.COBWEB);
    public static final DeferredHolder<Sugar, Sugar> GALE =
            registerUni("gale", Sugar.Type.NETHER, Items.BREEZE_ROD);
    public static final DeferredHolder<Sugar, Sugar> REFRESHING =
            registerUni("refreshing", Sugar.Type.NETHER, Items.COCOA_BEANS);
    public static final DeferredHolder<Sugar, Sugar> LAZY =
            registerUni("lazy", Sugar.Type.NETHER, Items.COBBLESTONE);
    public static final DeferredHolder<Sugar, Sugar> SOLID =
            registerUni("solid", Sugar.Type.NETHER, ItemRegistry.CALCIUM_RICH_MILK.get());
    public static final DeferredHolder<Sugar, Sugar> FIREPROOF =
            registerUni("fireproof", Sugar.Type.NETHER, Items.MAGMA_CREAM, Items.OBSIDIAN);
    public static final DeferredHolder<Sugar, Sugar> WITHERING =
            registerUni("withering", Sugar.Type.NETHER, Items.WITHER_ROSE);
    public static final DeferredHolder<Sugar, Sugar> RED_HEART =
            registerUni("red_heart", Sugar.Type.NETHER, ItemRegistry.IRON_CLAD_APPLE.get());
    public static final DeferredHolder<Sugar, Sugar> FLOATING =
            registerUni("floating", Sugar.Type.NETHER, Items.SHULKER_SHELL);

    // End blends
    public static final DeferredHolder<Sugar, Sugar> GOLDEN_HEART =
            registerUni("golden_heart", Sugar.Type.END, ItemRegistry.GOLD_STUDDED_APPLE.get());
    public static final DeferredHolder<Sugar, Sugar> SATIATING =
            registerUni("satiating", Sugar.Type.END, ItemRegistry.BLESSED_STEAK.get());
    public static final DeferredHolder<Sugar, Sugar> LUCKY =
            registerUni("lucky", Sugar.Type.END, ItemRegistry.GREEDY_OFFERING.get());
    public static final DeferredHolder<Sugar, Sugar> UNLUCKY =
            registerUni("unlucky", Sugar.Type.END, ItemRegistry.DEFILED_OFFERING.get());
    public static final DeferredHolder<Sugar, Sugar> TIDAL =
            registerUni("tidal", Sugar.Type.END, Items.NAUTILUS_SHELL);
    public static final DeferredHolder<Sugar, Sugar> FISH_SWIM =
            registerUni("fish_swim", Sugar.Type.END, ItemRegistry.DOLPHIN_COOKIE.get());
    public static final DeferredHolder<Sugar, Sugar> TAUNTING =
            registerUni("taunting", Sugar.Type.END, ItemRegistry.OMINOUS_FLAG.get());
    public static final DeferredHolder<Sugar, Sugar> DISCOUNT =
            registerUni("discount", Sugar.Type.END, Items.EMERALD_BLOCK);

    public static void register(IEventBus eventBus) {
        SUGARS.register(eventBus);
    }

    public static DeferredHolder<Sugar, Sugar> registerUni(String name, Sugar.Type type, Ingredient ingredient) {
        return SUGARS.register(name, () -> new Sugar(name, type, ingredient));
    }

    public static DeferredHolder<Sugar, Sugar> registerUni(String name, Sugar.Type type, ItemLike... ingredients) {
        return registerUni(name, type, Ingredient.of(ingredients));
    }
}
