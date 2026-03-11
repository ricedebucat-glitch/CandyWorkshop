package com.lnatit.ccw.item.component;

import com.lnatit.ccw.item.ItemRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.List;

public class GummyContents
{
    protected final int size;
    protected NonNullList<ItemStack> contents;
    protected int tier = 1;

    public GummyContents(int size) {
        this.size = size;
        this.contents = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    protected void updateContents(List<ItemStack> contents) {
        for (int i = 0; i < this.contents.size(); i++) {
            if (i < contents.size() && contents.get(i) != null) {
                this.contents.set(i, contents.get(i));
            }
            else {
                this.contents.set(i, ItemStack.EMPTY);
            }
        }
    }

    protected int getTierMarch() {
        return 1;
    }

    public void onConsume(LivingEntity entity) {
        this.checkSize();
        if (entity instanceof ServerPlayer player) {
            for (int i = 0; i < Math.min(tier * this.getTierMarch(), this.contents.size()); i++) {
                ItemStack stack = contents.get(i);
            }
        }
    }

    protected void checkSize() {
        if (contents.size() != size) {
            contents = NonNullList.copyOf(contents.subList(0, size));
        }
    }

//    protected void checkContents() {
//        for (int i = 0; i < contents.size(); i++) {
//            if (!contents.get(i).isEmpty()) {
//                contents.set(i, ItemStack.EMPTY);
//            }
//        }
//    }

    public class GummyItemHandler extends ItemStackHandler
    {
        public GummyItemHandler() {
            super(contents);
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return slot < size && (stack.isEmpty() || stack.is(ItemRegistry.GUMMY_ITEM));
        }
    }
}
