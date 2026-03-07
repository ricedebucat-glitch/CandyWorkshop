package com.lnatit.ccw.datapack;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.item.ItemRegistry;
import com.lnatit.ccw.item.sugaring.modifier.Modifiers;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@EventBusSubscriber(modid = CandyWorkshop.MODID)
public interface DataPackRegistry
{
    @SubscribeEvent
    static void onDataPackRegister(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(Flavor.KEY, Flavor.DIRECT_CODEC, Flavor.DIRECT_CODEC, builder -> builder.sync(true));
        event.dataPackRegistry(Formula.KEY, Formula.CODEC, Formula.CODEC, builder -> builder.sync(true));
    }

    static <T> ResourceKey<T> of(ResourceKey<Registry<T>> registry, String name) {
        return ResourceKey.create(registry, CandyWorkshop.id(name));
    }

//    interface Flavors
//    {
//        Flavor ORIGINAL = Flavor.explicit(CandyWorkshop.id("original"), 0xFFFFFF, Ingredient.EMPTY, Modifiers.EMPTY);
//        Flavor EXCITED = Flavor.auto(EXCITED.location(), 43520, Ingredient.of(Items.COCOA_BEANS), Modifiers.EMPTY);
//        Flavor BOLD = Flavor.auto(BOLD.location(), 16755200, Ingredient.of(Items.HONEY_BOTTLE), Modifiers.EMPTY);
//        Flavor MILKY = Flavor.auto(MILKY.location(), 16777215, Ingredient.of(ItemRegistry.MILK_GELATIN.get()), Modifiers.MILKY);
//    }
}
