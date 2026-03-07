package com.lnatit.ccw.item.sugaring;

import com.lnatit.ccw.datapack.Flavor;
import com.lnatit.ccw.datapack.Formula;
import com.lnatit.ccw.misc.data.AttachmentRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

// TODO 修改为注册项，使用RegistryFileCodec进行双路径序列化，在注册表中，
//  只写入不符合统一口味转换规则的项（如即时效果药水的浓郁效果注册为InvalidContent、神龟药水的口味单独注册对应Effect列表等）
//  以减少注册项的数量，并精简代码；在物品数据中，将原始值修改为Holder包装值，参考原版Instrument注册项
//  命名：Formula？
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
                .translatable(descriptionId + "." + this.sugar.value().name())
                .withStyle(ChatFormatting.WHITE);
        return this.flavor.is(Flavor.ORIGINAL) ? name : this.flavor.value().prefix().append(" ").append(name);
    }

    public void addSugarTooltip(Consumer<Component> tooltipAdder, float ticksPerSecond) {
        // TODO 糖果文本从Formula注册表读
        this.sugar.value().addSugarTooltip(tooltipAdder, this.flavor, ticksPerSecond);
        if (this.flavor.is(Flavor.ORIGINAL)) {
            tooltipAdder.accept(this.flavor.value().description());
        }
    }

    public void onConsume(LivingEntity entity) {
        sugar.value().applyOn(entity, this.flavor);



        if (entity instanceof ServerPlayer player) {
            player.getData(AttachmentRegistry.SUGAR_STAT).addHistory(this.sugar, player);
        }
    }

    public SugarContents cycle() {
        if (this.sugar.isPresent()) {
            List<Flavor> flavors = this.sugar.get().value().getAvailableFlavors();
            int index = (flavors.indexOf(this.flavor) + 1) % flavors.size();
            return new SugarContents(this.sugar, flavors.get(index));
        }
        return this;
    }

    private void applyOn(LivingEntity entity) {
        boolean explicit = this.flavor.value().explicit();
        Optional<Holder.Reference<Formula>> optional = entity.level().registryAccess().registryOrThrow(Formula.KEY).getHolder(
                Formula.formulaOf(this.sugar, this.flavor));
        if (explicit && !optional.isPresent()) {
            Formula formula = optional.get().value().applyOn(entity);
        }
    }

    public static SugarContents vanilla() {
        return new SugarContents(Optional.empty(), Minecraft.getInstance().);
    }

}
