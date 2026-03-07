package com.lnatit.ccw.item.sugaring;

import com.lnatit.ccw.datapack.Flavor;
import com.lnatit.ccw.misc.data.AttachmentRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.Minecart;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

// TODO 修改为注册项，使用RegistryFileCodec进行双路径序列化，在注册表中，
//  只写入不符合统一口味转换规则的项（如即时效果药水的浓郁效果注册为InvalidContent、神龟药水的口味单独注册对应Effect列表等）
//  以减少注册项的数量，并精简代码；在物品数据中，将原始值修改为Holder包装值，参考原版Instrument注册项
//  命名：Formula？
public record SugarContents(Optional<Holder<Sugar>> sugar, Holder<Flavor> flavor)
{
    public static final Codec<SugarContents> CODEC = RecordCodecBuilder.create(
            ins -> ins.group(
                              Sugar.CODEC.optionalFieldOf("sugar").forGetter(SugarContents::sugar),
                              Flavor.CODEC.fieldOf("flavor").forGetter(SugarContents::flavor)
                      )
                      .apply(ins, SugarContents::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, SugarContents> STREAM_CODEC = StreamCodec.composite(
            Sugar.STREAM_CODEC.apply(ByteBufCodecs::optional),
            SugarContents::sugar,
            Flavor.STREAM_CODEC,
            SugarContents::flavor,
            SugarContents::new
    );

    public boolean is(Holder<Sugar> holder) {
        return this.sugar.isPresent() && holder.equals(this.sugar.get());
    }

    public boolean is(Holder<Sugar> holder, Flavor type) {
        return is(holder) && this.flavor.equals(type);
    }

    public Component getName(String descriptionId) {
        // temporary fix
        Component name = Component.translatable(
                descriptionId.concat(this.sugar.map(s -> ".".concat(s.value().name())).orElse(""))
        ).withStyle(ChatFormatting.WHITE);
        MutableComponent flavor = Flavor.nameOf(this.flavor);
        return flavor == null ? name : flavor.append(" ").append(name);
    }

    public void addSugarTooltip(Consumer<Component> tooltipAdder, float ticksPerSecond) {
        if (this.sugar.isPresent()) {
            this.sugar.get().value().addSugarTooltip(tooltipAdder, this.flavor, ticksPerSecond);
            Component desc = Flavor.descriptionOf(this.flavor);
            if (desc != null) {
                tooltipAdder.accept(desc);
            }
        }
    }

    public void onConsume(LivingEntity entity) {
        if (this.sugar.isPresent()) {
            Holder<Sugar> holder = this.sugar.get();
            holder.value().applyOn(entity, this.flavor);
            if (entity instanceof ServerPlayer player) {
                player.getData(AttachmentRegistry.SUGAR_STAT).addHistory(holder, player);
            }
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

    public static SugarContents vanilla() {
        return new SugarContents(Optional.empty(), Minecraft.getInstance().);
    }
}
