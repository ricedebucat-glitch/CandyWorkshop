package com.lnatit.ccw.item.sugaring.flavor;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.misc.RegRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.BiFunction;

@EventBusSubscriber(modid = CandyWorkshop.MODID)
public class Flavors
{
    public static final DeferredRegister<Flavor> FLAVORS =
            DeferredRegister.create(RegRegistry.FLAVOR_KEY, CandyWorkshop.MODID);

    public static final DeferredHolder<Flavor, Flavor> ORIGINAL =
            FLAVORS.register("original", () -> new Flavor("original"));
    public static final DeferredHolder<Flavor, ExcitedFlavor> EXCITED =
            register("excited", ExcitedFlavor::new, 43520);
    public static final DeferredHolder<Flavor, BoldFlavor> BOLD =
            register("bold", BoldFlavor::new, 16755200);
    public static final DeferredHolder<Flavor, MilkyFlavor> MILKY =
            register("milky", MilkyFlavor::new, 16777215);

    public static <T extends Flavor> DeferredHolder<Flavor, T> register(String name, BiFunction<String, Integer, T> constructor, int color) {
        return FLAVORS.register(name, () -> constructor.apply(name, color));
    }

    public static Holder<Flavor> from(ItemStack stack) {
        for (Holder<Flavor> flavor : FLAVORS.getEntries()) {
            if (stack.is(flavor.value().asItem())) {
                return flavor;
            }
        }
        return ORIGINAL;
    }
}
