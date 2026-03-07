package com.lnatit.ccw.datapack;

import com.lnatit.ccw.CandyWorkshop;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@EventBusSubscriber(modid = CandyWorkshop.MODID)
public interface DataPackRegistry
{
    static void onDataPackRegister(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(Flavor.KEY, Flavor.CODEC);
        event.dataPackRegistry(Formula.KEY, Formula.CODEC);
    }

    static <T> ResourceKey<T> of(ResourceKey<Registry<T>> registry, String name) {
        return ResourceKey.create(registry, CandyWorkshop.id(name));
    }
}
