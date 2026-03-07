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

import java.util.function.Function;

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
    public static final DeferredHolder<Sugar, UniSugar> SPEED =
            registerUni("speed", UniSugar.Type.OVERWORLD, Items.SUGAR);
    public static final DeferredHolder<Sugar, UniSugar> BUNNY =
            registerUni("bunny", UniSugar.Type.OVERWORLD, Items.RABBIT_FOOT, ItemRegistry.ENERGY_CARROT.get());
    public static final DeferredHolder<Sugar, UniSugar> HEALING =
            registerUni("healing", UniSugar.Type.OVERWORLD, Items.GLISTERING_MELON_SLICE);
    public static final DeferredHolder<Sugar, UniSugar> POISON =
            registerUni("poison", UniSugar.Type.OVERWORLD, Items.SPIDER_EYE);
    public static final DeferredHolder<Sugar, UniSugar> PUFFERFISH =
            registerUni("pufferfish", UniSugar.Type.OVERWORLD, Items.PUFFERFISH);
    public static final DeferredHolder<Sugar, UniSugar> NIGHT_VISION =
            registerUni("night_vision", UniSugar.Type.OVERWORLD, Items.GOLDEN_CARROT);
    public static final DeferredHolder<Sugar, UniSugar> STRENGTH =
            registerUni("strength", UniSugar.Type.OVERWORLD, Items.BLAZE_POWDER);
    public static final DeferredHolder<Sugar, UniSugar> RECOVERY =
            registerUni("recovery", UniSugar.Type.OVERWORLD, Items.GHAST_TEAR, ItemRegistry.SWEET_MELON_SLICE.get());
    public static final DeferredHolder<Sugar, UniSugar> TURTLE =
            registerUni("turtle", UniSugar.Type.OVERWORLD, Items.TURTLE_SCUTE);
    public static final DeferredHolder<Sugar, UniSugar> FLUTTER =
            registerUni("flutter", UniSugar.Type.OVERWORLD, Items.PHANTOM_MEMBRANE);
    public static final DeferredHolder<Sugar, UniSugar> SNAIL =
            registerUni("snail", UniSugar.Type.OVERWORLD, Items.SOUL_SAND);
    public static final DeferredHolder<Sugar, UniSugar> STINKY =
            registerUni("stinky", UniSugar.Type.OVERWORLD, Ingredient.of(Tags.Items.MUSHROOMS));
    public static final DeferredHolder<Sugar, UniSugar> BLINDING =
            registerUni("blinding", UniSugar.Type.OVERWORLD, ItemRegistry.VOID_CARROT.get());
    public static final DeferredHolder<Sugar, UniSugar> WEAKNESS =
            registerUni("weakness", UniSugar.Type.OVERWORLD, ItemRegistry.WEAKNESS_POWDER.get());
    public static final DeferredHolder<Sugar, UniSugar> BRIGHTNESS =
            registerUni("brightness", UniSugar.Type.OVERWORLD, Items.GLOW_BERRIES);
    public static final DeferredHolder<Sugar, UniSugar> DARKNESS =
            registerUni("darkness", UniSugar.Type.OVERWORLD, Items.BLACK_DYE);
    public static final DeferredHolder<Sugar, UniSugar> HUNGER =
            registerUni("hunger", UniSugar.Type.OVERWORLD, Items.ROTTEN_FLESH);

    // Nether blends
    public static final DeferredHolder<Sugar, UniSugar> INVISIBILITY =
            registerUni("invisibility", UniSugar.Type.NETHER, ItemRegistry.PHANTOM_PEARL.get());
    public static final DeferredHolder<Sugar, UniSugar> STINGER =
            registerUni("stinger", UniSugar.Type.NETHER, Items.CACTUS);
    public static final DeferredHolder<Sugar, UniSugar> BUG =
            registerUni("bug", UniSugar.Type.NETHER, Items.STONE);
    public static final DeferredHolder<Sugar, UniSugar> STICKY =
            registerUni("sticky", UniSugar.Type.NETHER, Items.SLIME_BLOCK);
    public static final DeferredHolder<Sugar, UniSugar> BINDING =
            registerUni("binding", UniSugar.Type.NETHER, Items.COBWEB);
    public static final DeferredHolder<Sugar, UniSugar> GALE =
            registerUni("gale", UniSugar.Type.NETHER, Items.BREEZE_ROD);
    public static final DeferredHolder<Sugar, UniSugar> REFRESHING =
            registerUni("refreshing", UniSugar.Type.NETHER, Items.COCOA_BEANS);
    public static final DeferredHolder<Sugar, UniSugar> LAZY =
            registerUni("lazy", UniSugar.Type.NETHER, Items.COBBLESTONE);
    public static final DeferredHolder<Sugar, UniSugar> SOLID =
            registerUni("solid", UniSugar.Type.NETHER, ItemRegistry.CALCIUM_RICH_MILK.get());
    public static final DeferredHolder<Sugar, UniSugar> FIREPROOF =
            registerUni("fireproof", UniSugar.Type.NETHER, Items.MAGMA_CREAM, Items.OBSIDIAN);
    public static final DeferredHolder<Sugar, UniSugar> WITHERING =
            registerUni("withering", UniSugar.Type.NETHER, Items.WITHER_ROSE);
    public static final DeferredHolder<Sugar, UniSugar> RED_HEART =
            registerUni("red_heart", UniSugar.Type.NETHER, ItemRegistry.IRON_CLAD_APPLE.get());
    public static final DeferredHolder<Sugar, UniSugar> FLOATING =
            registerUni("floating", UniSugar.Type.NETHER, Items.SHULKER_SHELL);

    // End blends
    public static final DeferredHolder<Sugar, UniSugar> GOLDEN_HEART =
            registerUni("golden_heart", UniSugar.Type.END, ItemRegistry.GOLD_STUDDED_APPLE.get());
    public static final DeferredHolder<Sugar, UniSugar> SATIATING =
            registerUni("satiating", UniSugar.Type.END, ItemRegistry.BLESSED_STEAK.get());
    public static final DeferredHolder<Sugar, UniSugar> LUCKY =
            registerUni("lucky", UniSugar.Type.END, ItemRegistry.GREEDY_OFFERING.get());
    public static final DeferredHolder<Sugar, UniSugar> UNLUCKY =
            registerUni("unlucky", UniSugar.Type.END, ItemRegistry.DEFILED_OFFERING.get());
    public static final DeferredHolder<Sugar, UniSugar> TIDAL =
            registerUni("tidal", UniSugar.Type.END, Items.NAUTILUS_SHELL);
    public static final DeferredHolder<Sugar, UniSugar> FISH_SWIM =
            registerUni("fish_swim", UniSugar.Type.END, ItemRegistry.DOLPHIN_COOKIE.get());
    public static final DeferredHolder<Sugar, UniSugar> TAUNTING =
            registerUni("taunting", UniSugar.Type.END, ItemRegistry.OMINOUS_FLAG.get());
    public static final DeferredHolder<Sugar, UniSugar> DISCOUNT =
            registerUni("discount", UniSugar.Type.END, Items.EMERALD_BLOCK);

    public static void register(IEventBus eventBus) {
        SUGARS.register(eventBus);
    }

    public static DeferredHolder<Sugar, SingleEffectSugar> registerSingle(String id, Function<SingleEffectSugar.IEffectAcceptor, SingleEffectSugar> props) {
        return SUGARS.register(id, () -> props.apply(SingleEffectSugar.builder(id)));
    }

    public static DeferredHolder<Sugar, UniSugar> registerUni(String name, UniSugar.Type type, Ingredient ingredient) {
        return SUGARS.register(name, () -> new UniSugar(name, type, ingredient));
    }

    public static DeferredHolder<Sugar, UniSugar> registerUni(String name, UniSugar.Type type, ItemLike... ingredients) {
        return registerUni(name, type, Ingredient.of(ingredients));
    }
}
