package com.lnatit.ccw.item.sugaring;

import com.lnatit.ccw.datapack.Effect;
import com.lnatit.ccw.datapack.Formula;
import com.lnatit.ccw.item.sugaring.flavor.Flavor;
import com.lnatit.ccw.item.sugaring.flavor.Flavors;
import com.lnatit.ccw.misc.data.AttachmentRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
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
                .translatable(descriptionId + "." + this.sugar.getKey().location().getPath())
                .withStyle(ChatFormatting.WHITE);
        return this.flavor.is(Flavors.ORIGINAL) ? name : Flavor.prefix(this.flavor).append(" ").append(name);
    }

    public void addSugarTooltip(Consumer<Component> tooltipAdder, float ticksPerSecond) {
        Formula
                .getFormulaOptional(this.sugar, this.flavor)
                .map(Formula::effects)
                .orElse(List.of())
                .forEach(effect -> tooltipAdder.accept(effect.getDescription(ticksPerSecond)));

        tooltipAdder.accept(Flavor.description(this.flavor));
    }

    public void onConsume(LivingEntity entity) {
        if (entity instanceof ServerPlayer player) {
            Optional<Formula> optional = Formula.getFormulaOptional(this.sugar, this.flavor);
            optional.ifPresent(formula -> applyOn(formula, entity));
            player.getData(AttachmentRegistry.SUGAR_STAT).addHistory(this.sugar, player);
        }
    }

    public SugarContents cycle() {
        return new SugarContents(this.sugar, Flavor.next(this.flavor));
    }

    private static void applyOn(Formula formula, LivingEntity entity) {
        List<Flavor> flavors = List.of(formula.flavor().value());
        List<Effect> effects = formula.effects();

        flavors.forEach(m -> m.preConsume(entity, effects, effects));
        effects.forEach(e -> e.extendEffect(entity));
        flavors.forEach(m -> m.postConsume(entity, effects, effects));
    }
}
