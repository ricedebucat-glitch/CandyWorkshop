package com.lnatit.ccw.item.sugaring;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.datapack.Effect;
import com.lnatit.ccw.item.sugaring.flavor.SimpleFlavor;
import com.lnatit.ccw.datapack.Formula;
import com.lnatit.ccw.item.sugaring.flavor.Flavor;
import com.lnatit.ccw.misc.data.AttachmentRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public record SugarContents(Holder<Sugar> sugar, Holder<Flavor> flavor)
{
    public static final Codec<SugarContents> CODEC = RecordCodecBuilder.create(ins -> ins
            .group(Sugar.CODEC.fieldOf("sugar").forGetter(SugarContents::sugar),
                   Flavor.CODEC.fieldOf("flavor").forGetter(SugarContents::flavor))
            .apply(ins, SugarContents::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, SugarContents> STREAM_CODEC = StreamCodec.composite(Sugar.STREAM_CODEC,
                                                                                                                 SugarContents::sugar,
                                                                                                                 Flavor.STREAM_CODEC,
                                                                                                                 SugarContents::flavor,
                                                                                                                 SugarContents::new);

    public boolean is(Holder<Sugar> sugar) {
        return sugar.equals(this.sugar);
    }

    public boolean is(Holder<Sugar> sugar, Holder<Flavor> flavor) {
        return is(sugar) && flavor.equals(this.flavor);
    }

    public Component getName(String descriptionId) {
        // temporary fix
        Component name = Component
                .translatable(descriptionId + "." + CandyWorkshop.getName(this.sugar))
                .withStyle(ChatFormatting.WHITE);
        return this.flavor.is(Flavor.ORIGINAL) ? name : Flavor.prefix(this.flavor).append(" ").append(name);
    }

    public void addSugarTooltip(Consumer<Component> tooltipAdder, float ticksPerSecond) {
        Formula
                .getFormulaOptional(this.sugar, this.flavor)
                .map(f -> f.value().effects())
                .orElse(List.of())
                .forEach(effect -> tooltipAdder.accept(effect.getDescription(ticksPerSecond)));
        
        tooltipAdder.accept(Flavor.description(this.flavor));
    }

    public void onConsume(LivingEntity entity) {
        if (entity instanceof ServerPlayer player) {
            Optional<? extends Holder<Formula>> f = Formula.getFormulaOptional(this.sugar, this.flavor);
            f.ifPresent(holder -> applyOn(holder.value(), entity));
            player.getData(AttachmentRegistry.SUGAR_STAT).addHistory(this.sugar, player);
        }
    }

    public SugarContents cycle() {
        Registry<SimpleFlavor> R = SimpleFlavor.REGISTRIES();
        if (R == null) {
            return this;
        }
        Optional<ResourceKey<SimpleFlavor>> key = this.flavor.unwrapKey();

        boolean after = false;
        SugarContents pending = this;
        for (Map.Entry<ResourceKey<SimpleFlavor>, SimpleFlavor> entry : R.entrySet()) {
            if (!after && key.isPresent() && entry.getKey().location().equals(key.get().location())) {
                after = true;
                continue;
            }

            var newFlavor = R.getHolder(entry.getKey()).get();
            Optional<? extends Holder<Formula>> f = Formula.getFormulaOptional(this.sugar, newFlavor);
            if (f.isPresent()) {
                if (after) {
                    return new SugarContents(this.sugar, newFlavor);
                }
                else {
                    pending = new SugarContents(this.sugar, newFlavor);
                }
            }
        }
        return pending;
    }

    private static void applyOn(Formula formula, LivingEntity entity) {
        List<Flavor> flavors = List.of(formula.flavor().value());
        List<Effect> effects = formula.effects();

        flavors.forEach(m -> m.preConsume(entity, effects, effects));
        effects.forEach(e -> e.extendEffect(entity));
        flavors.forEach(m -> m.postConsume(entity, effects, effects));
    }
}
