package com.lnatit.ccw.menu;

import com.lnatit.ccw.item.GummyMagazineItem;
import com.lnatit.ccw.item.ItemRegistry;
import com.lnatit.ccw.item.component.GummyContents;
import com.lnatit.ccw.item.component.MutableContents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class GummyContentMenu extends ModContainerMenu {
    private final InteractionHand hand;
    private final MutableContents mutable;
    private final int locked;

    public GummyContentMenu(int containerId, Inventory playerInventory, Context context) {
        super(MenuRegistry.GUMMY_MAGAZINE.get(), containerId);
        this.hand = context.useHand();
        this.mutable = context.mutable();
        this.addContentSlots(mutable);
        int slotId = context.slotId();
        if (hand == InteractionHand.OFF_HAND)
            slotId = -1;
        Slot locked = this.addLockedInventorySlots(playerInventory, 8, 106, slotId);
        if (locked != null) {
            this.locked = locked.index;
        } else {
            this.locked = -1;
        }
    }

    public GummyContentMenu(int containerId, Inventory playerInventory, RegistryFriendlyByteBuf data) {
        this(containerId, playerInventory, Context.STREAM_CODEC.decode(data));
    }

    private void addContentSlots(IItemHandler contents) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                this.addSlot(new SlotItemHandler(contents, j + i * 2, 71 + j * 19, 13 + i * 19));
            }
        }
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (id == 0) {
            GummyMagazineItem.eatGummies(player.level(), player, this.mutable.activeSlots(), this.mutable, !(player instanceof ServerPlayer));
            player.awardStat(Stats.ITEM_USED.get(this.getContainer(player).getItem()));
            return true;
        }
        return super.clickMenuButton(player, id);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        if (index == this.locked) {
            return ItemStack.EMPTY;
        }

        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            result = stack.copy();
            if (index < 3 * 2) {
                if (!this.moveItemStackTo(stack, 6, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.moveItemStackTo(stack, 0, 6, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            }
            else {
                slot.setChanged();
            }
        }
        return result;
    }

    private ItemStack getContainer(Player player) {
        if (hand == InteractionHand.MAIN_HAND) {
            return player.getMainHandItem();
        }
        return player.getOffhandItem();
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        ItemStack stack = this.getContainer(player);
        GummyContents.set(stack, this.mutable);
    }

    @Override
    public boolean stillValid(Player player) {
        return this.getContainer(player).is(ItemRegistry.GUMMY_MAGAZINE);
    }

    public record Context(MutableContents mutable, InteractionHand useHand, int slotId) {
        public static final StreamCodec<RegistryFriendlyByteBuf, Context> STREAM_CODEC = StreamCodec.composite(
                MutableContents.STREAM_CODEC,
                Context::mutable,
                ByteBufCodecs.BOOL,
                Context::mainHand,
                ByteBufCodecs.INT,
                Context::slotId,
                Context::of
        );

        private static Context of(MutableContents mutable, boolean mainHand, int slotId) {
            return new Context(mutable, mainHand ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND, slotId);
        }

        private boolean mainHand() {
            return useHand == InteractionHand.MAIN_HAND;
        }
    }

    public record Provider(Context context, Component title) implements MenuProvider {
        public static Provider of(MutableContents mutable, InteractionHand useHand, int slot, Component title) {
            return new Provider(new Context(mutable, useHand, slot), title);
        }

        @Override
        public Component getDisplayName() {
            return title;
        }

        @Override
        public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
            return new GummyContentMenu(containerId, playerInventory, this.context);
        }

        @Override
        public void writeClientSideData(AbstractContainerMenu menu, RegistryFriendlyByteBuf buffer) {
            Context.STREAM_CODEC.encode(buffer, this.context);
        }
    }
}
