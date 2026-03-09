package com.lnatit.ccw.datapack;

import com.lnatit.ccw.CandyWorkshop;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@EventBusSubscriber(modid = CandyWorkshop.MODID, bus = EventBusSubscriber.Bus.MOD)
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
}
