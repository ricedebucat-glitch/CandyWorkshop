package com.lnatit.ccw.item.component;

import com.lnatit.ccw.item.ItemRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

public class MutableContents extends ItemStackHandler implements IContents {
    public static final StreamCodec<RegistryFriendlyByteBuf, MutableContents> STREAM_CODEC = IContents.streamCodec(MutableContents::new);

    private Tier tier;
    private final Type type;

    public MutableContents(GummyContents contents) {
        this(contents.stacks(), contents.tier(), contents.type());
    }

    private MutableContents(List<ItemStack> stacks, Tier tier, Type type) {
        super(type.size);
        this.tier = tier;
        this.type = type;
        for (int i = 0; i < type.size; i++) {
            this.stacks.set(i, stacks.get(i));
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
    public List<ItemStack> stacks() {
        return this.stacks;
    }

    @Override
    public Tier tier() {
        return this.tier;
    }

    @Override
    public Type type() {
        return this.type;
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
