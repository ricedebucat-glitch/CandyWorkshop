package com.lnatit.ccw.item.component;

import com.lnatit.ccw.item.ItemRegistry;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.List;

public abstract class GummyContents extends ItemStackHandler {
    private Tier tier = Tier.PRIMARY;

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

    public enum Tier {
        PRIMARY,
        NETHER,
        ENDER;
    }
}
