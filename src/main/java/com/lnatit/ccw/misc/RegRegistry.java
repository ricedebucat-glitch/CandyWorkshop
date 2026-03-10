package com.lnatit.ccw.misc;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.item.sugaring.Sugar;
import com.lnatit.ccw.item.sugaring.flavor.Flavor;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

@EventBusSubscriber(modid = CandyWorkshop.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegRegistry
{
    public static final ResourceKey<Registry<Sugar>> SUGAR_KEY = ResourceKey.createRegistryKey(
            ResourceLocation.fromNamespaceAndPath(CandyWorkshop.MODID, "sugar"));
    public static final ResourceKey<Registry<Flavor>> FLAVOR_KEY = ResourceKey.createRegistryKey(CandyWorkshop.id("flavor"));
    public static final ResourceKey<Registry<IModifier>> MODIFIER_KEY = ResourceKey.createRegistryKey(CandyWorkshop.id("modifier"));

    public static final Registry<Sugar> SUGAR = new RegistryBuilder<>(SUGAR_KEY)
            .sync(true)
            .defaultKey(ResourceLocation.fromNamespaceAndPath(CandyWorkshop.MODID, "vanilla"))
            .create();
    public static final Registry<Flavor> FLAVOR = new RegistryBuilder<>(FLAVOR_KEY)
            .sync(true)
            .defaultKey(CandyWorkshop.id("original"))
            .create();
    public static final Registry<IModifier> MODIFIER = new RegistryBuilder<>(MODIFIER_KEY)
            .sync(true)
            .defaultKey(CandyWorkshop.id("empty"))
            .create();

    @SubscribeEvent
    public static void registerRegistries(final NewRegistryEvent event) {
        event.register(SUGAR);
        event.register(FLAVOR);
        event.register(MODIFIER);
    }
}
