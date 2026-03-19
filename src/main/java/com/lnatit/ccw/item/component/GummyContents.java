package com.lnatit.ccw.item.component;

import com.lnatit.ccw.item.ItemRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public interface GummyContents {
    Tier tier();

    List<ItemStack> stacks();

    int getTierMarch();

    default int activeSize() {
        return this.getTierMarch() * this.tier().ordinal();
    }

    default NonNullList<ItemStack> items() {
        return NonNullList.copyOf(this.stacks());
    }

    static <S extends GummyContents> Codec<S> codec(BiFunction<List<ItemStack>, Tier, S> factory) {
        return RecordCodecBuilder.create(inst -> inst.group(
                Codec.list(ItemStack.CODEC).fieldOf("stacks").forGetter(GummyContents::items),
                Tier.CODEC.fieldOf("tier").forGetter(GummyContents::tier)
        ).apply(inst, factory));
    }

    static <S extends GummyContents> StreamCodec<RegistryFriendlyByteBuf, S> streamCodec(BiFunction<List<ItemStack>, Tier, S> factory) {
        return StreamCodec.composite(
                ItemStack.LIST_STREAM_CODEC,
                GummyContents::stacks,
                Tier.STREAM_CODEC,
                GummyContents::tier,
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

    class Mutable<I extends GummyContents> extends ItemStackHandler implements GummyContents {
        private Tier tier;
        private final int tierMarch;

        public Mutable(I contents, int size) {
            super(size);
            this.tier = contents.tier();
            this.tierMarch = contents.getTierMarch();
            for (int i = 0; i < size; i++) {
                this.stacks.set(i, contents.stacks().get(i));
            }
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
            if (this.tier == Tier.PRIMARY) {
                this.tier = Tier.NETHER;
            } else {
                this.tier = Tier.ENDER;
            }
        }

        @Override
        public Tier tier() {
            return this.tier;
        }

        @Override
        public List<ItemStack> stacks() {
            return this.stacks;
        }

        @Override
        public int getTierMarch() {
            return tierMarch;
        }

        @Override
        public void setSize(int size) {
            throw new RuntimeException("Resize is not allowed!");
        }

        @Override
        public void setStackInSlot(int slot, ItemStack stack) {
            if (isItemValid(slot, stack)) {
                super.setStackInSlot(slot, stack);
            } else {
                throw new RuntimeException("Invalid item " + stack + " in slot " + slot + "!");
            }
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return stack.isEmpty() || slot < this.stacks.size() && stack.is(ItemRegistry.GUMMY);
        }
    }
}
