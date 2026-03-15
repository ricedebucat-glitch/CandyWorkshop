package com.lnatit.ccw.item.component.legacy;

import com.lnatit.ccw.item.ItemRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;

import java.util.ArrayList;
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

    public Tier tier() {
        return this.tier;
    }

    public List<ItemStack> activeSlots() {
        int length = Math.min(this.stacks.size(), this.activeSize());
        return this.stacks.subList(0, length);
    }

    public List<ItemStack> updateSlots(List<ItemStack> stacks) {
        stacks = stacks.subList(0, Math.min(stacks.size(), this.activeSize()));
        List<ItemStack> unaccepted = new ArrayList<>();
        for (int i = 0; i < stacks.size(); i++) {
            ItemStack stack = stacks.get(i);
            if (isItemValid(i, stack)) {
                if (stack.isEmpty()) {
                    feed(i);
                } else {
                    this.stacks.set(i, stack);
                }
            } else {
                this.stacks.set(i, ItemStack.EMPTY);
                unaccepted.add(stack);
            }
        }
        return unaccepted;
    }

    private void feed(int slot) {
        ItemStack target = this.stacks.get(slot);
        for (int i = this.activeSize(); i < this.stacks.size(); i++) {
            ItemStack stack = this.stacks.get(i);
            if (ItemStack.isSameItemSameComponents(target, stack)) {
                this.stacks.set(slot, stack);
                this.stacks.set(i, ItemStack.EMPTY);
                return;
            }
        }
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
    public void setStackInSlot(int slot, ItemStack stack) {
        if (isItemValid(slot, stack)) {
            super.setStackInSlot(slot, stack);
        } else  {
            throw new RuntimeException("Invalid item " + stack + " in slot " + slot + "!");
        }
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return stack.isEmpty() || slot < this.stacks.size() && stack.is(ItemRegistry.GUMMY);
    }

    protected abstract int getTierMarch();

    protected int activeSize() {
        return this.getTierMarch() * this.tier.ordinal();
    }

    protected boolean isEmpty() {
        return this.stacks.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GummyContents other) {
            return this.isEmpty() && other.isEmpty() && this.tier.ordinal() == other.tier.ordinal();
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static <S extends GummyContents> Codec<S> codec(BiFunction<NonNullList<ItemStack>, Tier, S> factory) {
        return RecordCodecBuilder.create(inst -> inst.group(
                (NonNullList.codecOf(ItemStack.CODEC)).fieldOf("stacks").forGetter(c -> c.stacks),
                Tier.CODEC.fieldOf("tier").forGetter(c -> c.tier)
        ).apply(inst, factory));
    }

    public static <S extends GummyContents> StreamCodec<RegistryFriendlyByteBuf, S> streamCodec(BiFunction<List<ItemStack>, Tier, S> factory) {
        return StreamCodec.composite(
                ItemStack.LIST_STREAM_CODEC,
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
