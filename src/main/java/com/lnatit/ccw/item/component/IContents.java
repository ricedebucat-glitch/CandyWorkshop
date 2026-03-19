package com.lnatit.ccw.item.component;

import com.google.common.collect.ImmutableList;
import com.lnatit.ccw.item.ItemRegistry;
import com.mojang.datafixers.util.Function3;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public interface IContents {
    List<ItemStack> stacks();

    Tier tier();

    Type type();

    default int activeSize() {
        return this.type().tierMarch * (this.tier().ordinal() + 1);
    }

    default NonNullList<ItemStack> items() {
        return NonNullList.copyOf(this.stacks());
    }

    static <S extends IContents> Codec<S> codec(Function3<List<ItemStack>, Tier, Type, S> factory) {
        return RecordCodecBuilder.create(inst -> inst.group(
                Codec.list(ItemStack.OPTIONAL_CODEC).fieldOf("stacks").forGetter(IContents::items),
                Tier.CODEC.fieldOf("tier").forGetter(IContents::tier),
                Type.CODEC.fieldOf("type").forGetter(IContents::type)
        ).apply(inst, factory));
    }

    static <S extends IContents> StreamCodec<RegistryFriendlyByteBuf, S> streamCodec(Function3<List<ItemStack>, Tier, Type, S> factory) {
        return StreamCodec.composite(
                ItemStack.OPTIONAL_LIST_STREAM_CODEC,
                IContents::stacks,
                Tier.STREAM_CODEC,
                IContents::tier,
                Type.STREAM_CODEC,
                IContents::type,
                factory
        );
    }

    enum Tier implements StringRepresentable {
        PRIMARY("primary"),
        NETHER("nether"),
        ENDER("ender");

        public static final Codec<Tier> CODEC = StringRepresentable.fromEnum(Tier::values);
        public static final StreamCodec<RegistryFriendlyByteBuf, Tier> STREAM_CODEC = NeoForgeStreamCodecs.enumCodec(Tier.class);

        private final String name;

        Tier(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }

    enum Type implements StringRepresentable {
        MAGAZINE("magazine", 6, 2, ItemRegistry.MAGAZINE_CONTENTS_DCTYPE),
//        APPLICATOR("applicator", 3, 1, ItemRegistry.APPLICATOR_CONTENTS_DCTYPE),
        ;

        public static final Codec<Type> CODEC = StringRepresentable.fromEnum(Type::values);
        public static final StreamCodec<RegistryFriendlyByteBuf, Type> STREAM_CODEC = NeoForgeStreamCodecs.enumCodec(Type.class);

        private final String name;
        public final int size;
        public final int tierMarch;
        public final Supplier<DataComponentType<GummyContents>> supplier;

        Type(String name, int size, int tierMarch, Supplier<DataComponentType<GummyContents>> supplier) {
            this.name = name;
            this.size = size;
            this.tierMarch = tierMarch;
            this.supplier = supplier;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }

        public GummyContents defaultContents() {
            return new GummyContents(
                    ImmutableList.copyOf(Collections.nCopies(this.size, ItemStack.EMPTY)),
                    Tier.PRIMARY,
                    this
            );
        }

        public MutableContents getMutable(ItemStack stack) {
            if (!stack.has(this.supplier))
                stack.set(this.supplier, defaultContents());
            GummyContents contents = stack.get(this.supplier);
            assert contents != null;
            return new MutableContents(contents);
        }
    }

    record Consumer(Level level, LivingEntity entity) implements Function<ItemStack, ItemStack> {
        @Override
        public ItemStack apply(ItemStack stack) {
            if (stack.isEmpty()) return stack;
            return stack.copy().finishUsingItem(level, entity);
        }
    }
}
