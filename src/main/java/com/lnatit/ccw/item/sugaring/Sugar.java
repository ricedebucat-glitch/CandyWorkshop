package com.lnatit.ccw.item.sugaring;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.datapack.Flavor;
import com.lnatit.ccw.item.ItemRegistry;
import com.lnatit.ccw.misc.RegRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;

public abstract class Sugar
{
    public static final Codec<Holder<Sugar>> CODEC = RegRegistry.SUGAR.holderByNameCodec();
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<Sugar>> STREAM_CODEC = ByteBufCodecs.holderRegistry(
            RegRegistry.SUGAR_KEY);
    protected final String name;

    public Sugar(String name) {
        this.name = name;
    }

    public static ItemStack createSugar(@Nullable Holder<Sugar> sugar, Flavor flavor) {
        if (sugar == null) {
            return ItemStack.EMPTY;
        }
        ItemStack itemStack = ItemRegistry.GUMMY_ITEM.toStack();
        itemStack.set(ItemRegistry.SUGAR_CONTENTS_DCTYPE, new SugarContents(Optional.of(sugar), flavor));
        return itemStack;
    }

    public static Collection<ItemStack> createAllFlavors(@Nullable Holder<Sugar> sugar) {
        if (sugar == null) {
            return Set.of();
        }
        Set<ItemStack> sugars = new HashSet<>();
        for (Flavor flavor : sugar.value().getAvailableFlavors()) {
            sugars.add(Sugar.createSugar(sugar, flavor));
        }
        return sugars;
    }

    public String name() {
        return name;
    }

    public ItemStack createItemStack() {
        return ItemStack.EMPTY;
    }

    public List<Effect> getEffects() {
        // TODO make a copy of the list to prevent modification
        return List.of();
    }

    // TODO move to SugarContents
    public void applyOn(LivingEntity entity, Flavor flavor) {}

    // TODO move to SugarContents
    public void addSugarTooltip(Consumer<Component> tooltipAdder, Flavor flavor, float ticksPerSecond) {
    }

    public ResourceLocation getItemModel() {
        return ResourceLocation.fromNamespaceAndPath(CandyWorkshop.MODID, this.name)
                               .withSuffix("_gummy");

    }

    public ResourceLocation getModelId() {
        return getItemModel().withPrefix("item/");
    }
}
