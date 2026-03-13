package com.lnatit.ccw.item.component;

import com.lnatit.ccw.item.ItemRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;

import java.util.List;
import java.util.function.BiFunction;

public abstract class GummyContents extends ItemStackHandler {
    protected Tier tier = Tier.PRIMARY;

    public GummyContents(int slots) {
        super(slots);
    }

    protected GummyContents(List<ItemStack> stacks, Tier tier) {
        super(NonNullList.copyOf(stacks));
        this.tier = tier;
    }

    public List<ItemStack> activeSlots() {
        int length = Math.min(this.stacks.size(), this.getTierMarch() * this.tier.ordinal());
        return this.stacks.subList(0, length);
    }

    public void upgrade() {
        switch (this.tier) {
            case PRIMARY:
                this.tier = Tier.NETHER;
                break;
            case NETHER:
                this.tier = Tier.ENDER;
                break;
        }
    }

    @Override
    public void setSize(int size) {
        throw new RuntimeException("Resize is not allowed!");
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return stack.isEmpty() || slot < this.stacks.size() && stack.is(ItemRegistry.GUMMY);
    }

    protected abstract int getTierMarch();

    public static <S extends GummyContents> Codec<S> codec(BiFunction<NonNullList<ItemStack>, Tier, S> factory) {
        return RecordCodecBuilder.create(inst -> inst.group(NonNullList.codecOf(ItemStack.CODEC).fieldOf("stacks").forGetter(c -> c.stacks),
                Tier.CODEC.fieldOf("tier").forGetter(c -> c.tier)
        ).apply(inst, factory));
    }

    public static <S extends GummyContents> StreamCodec<FriendlyByteBuf, S> streamCodec(BiFunction<NonNullList<ItemStack>, Tier, S> factory) {
        return StreamCodec.composite(
                (StreamCodec<FriendlyByteBuf, NonNullList<ItemStack>>) (StreamCodec<?, ?>) ItemStack.LIST_STREAM_CODEC,
                c -> c.stacks,
                Tier.STREAM_CODEC,
                c -> c.tier,
                factory
        );
    }

    public enum Tier implements StringRepresentable {
        PRIMARY("primary"),
        NETHER("nether"),
        ENDER("ender");

        public static final Codec<Tier> CODEC = StringRepresentable.fromEnum(Tier::values);
        public static final StreamCodec<FriendlyByteBuf, Tier> STREAM_CODEC = NeoForgeStreamCodecs.enumCodec(Tier.class);

        private final String name;

        Tier(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}
