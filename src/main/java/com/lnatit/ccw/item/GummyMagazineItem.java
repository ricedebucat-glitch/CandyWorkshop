package com.lnatit.ccw.item;

import com.lnatit.ccw.item.component.legacy.Magazine;
import com.lnatit.ccw.menu.GummyMagazineMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
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
        if (!itemstack.has(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE)) {
            itemstack.set(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE, new Magazine());
        }
        Magazine magazine = itemstack.get(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE);
        assert magazine != null;
        boolean client = level.isClientSide();
        // TODO
        boolean used = true;

        if (player.isShiftKeyDown()) {
            if (!client) {
                int slot = usedHand == InteractionHand.MAIN_HAND ? player.getInventory().selected : 0;
                player.openMenu(new SimpleMenuProvider((id, inv, p) -> new GummyMagazineMenu(id, inv, magazine, usedHand, slot), itemstack.getHoverName()));
                // open menu
            }
        } else {
            List<ItemStack> results = magazine.activeSlots().stream().map(stack -> stack.copy().finishUsingItem(level, player)).toList();
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

        if (used) {
            player.awardStat(Stats.ITEM_USED.get(this));
        }
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
