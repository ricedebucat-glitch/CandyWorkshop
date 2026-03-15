package com.lnatit.ccw.menu;

import com.lnatit.ccw.item.ItemRegistry;
import com.lnatit.ccw.item.component.legacy.Magazine;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class GummyMagazineMenu extends ModContainerMenu {
    private final InteractionHand hand;
    private final int slotId;


    public GummyMagazineMenu(int containerId, Inventory playerInventory, Magazine magazine, InteractionHand useHand, int slotId) {
        super(MenuRegistry.GUMMY_MAGAZINE.get(), containerId);
        this.hand = useHand;
        this.addMagazineSlots(magazine);
        Slot locked = this.addLockedInventorySlots(playerInventory, 8, 106, slotId);
        if (locked != null) {
            this.slotId = locked.index;
        } else {
            this.slotId = -1;
        }
    }

    public GummyMagazineMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, new Magazine(), InteractionHand.MAIN_HAND, -1);
    }

    private void addMagazineSlots(IItemHandler contents) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                this.addSlot(new SlotItemHandler(contents, j + i * 2, 71 + j * 19, 13 + i * 19));
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItem(int slotId, int stateId, ItemStack stack) {
        super.setItem(slotId, stateId, stack);
        if (slotId < Magazine.SLOT_SIZE) {
            this.getSlot(slotId).getItem().set(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE, this.getSlot(slotId).getItem().get(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
