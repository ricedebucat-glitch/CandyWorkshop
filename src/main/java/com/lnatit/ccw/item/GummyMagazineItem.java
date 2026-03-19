package com.lnatit.ccw.item;

import com.lnatit.ccw.item.component.GummyContents;
import com.lnatit.ccw.item.component.IContents;
import com.lnatit.ccw.item.component.MutableContents;
import com.lnatit.ccw.menu.GummyContentMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class GummyMagazineItem extends Item {
    public GummyMagazineItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemstack = player.getItemInHand(usedHand);
        MutableContents magazine = GummyContents.get6(itemstack);
        boolean client = level.isClientSide();

        if (player.isShiftKeyDown()) {
            if (!client) {
                int slot = usedHand == InteractionHand.MAIN_HAND ? player.getInventory().selected : 0;
                GummyContentMenu.Provider provider = GummyContentMenu.Provider.of(magazine, usedHand, slot, itemstack.getHoverName());
                player.openMenu(provider);
            }
        } else {
            List<ItemStack> active = magazine.activeSlots();
            if (active.stream().allMatch(ItemStack::isEmpty)) {
                return InteractionResultHolder.fail(itemstack);
            }
            eatGummies(level, player, active, magazine, client);
            GummyContents.set(itemstack, magazine);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    // I know it's ugly...
    public static void eatGummies(Level level, Player player, List<ItemStack> active, MutableContents magazine, boolean client) {
        IContents.Consumer consumer = new IContents.Consumer(level, player);
        List<ItemStack> results = active.stream().map(consumer).toList();
        List<ItemStack> drops = magazine.updateSlots(results);
        if (!client && !drops.isEmpty()) {
            for (ItemStack drop : drops) {
                if (drop.isEmpty()) continue;
                if (!player.getInventory().add(drop)) {
                    ItemEntity item = player.drop(drop, true);
                    if (item != null) {
                        item.setNoPickUpDelay();
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
