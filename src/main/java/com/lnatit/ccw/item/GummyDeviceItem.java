package com.lnatit.ccw.item;

import com.lnatit.ccw.block.entity.DrawerTableBlockEntity;
import com.lnatit.ccw.item.component.GummyContents;
import com.lnatit.ccw.item.component.IContents;
import com.lnatit.ccw.item.component.MutableContents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;

public abstract class GummyDeviceItem extends Item
{
    protected final IContents.Type type;
    protected final Tier tier;

    public GummyDeviceItem(Properties properties, IContents.Type type, Tier tier) {
        super(properties);
        this.type = type;
        this.tier = tier;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null && context.getPlayer().isShiftKeyDown()) {
            Level level = context.getLevel();
            BlockPos pos = context.getClickedPos();
            if (level.getBlockEntity(pos) instanceof DrawerTableBlockEntity table) {
                if (level.isClientSide()) {
                    return InteractionResult.SUCCESS;
                }

                ItemStack stack = context.getItemInHand();
                MutableContents mutable = this.getMutable(stack);
                IItemHandler contents = table.accessInventory(context.getClickedFace());

                boolean changed = false;
                int i, j;
                for (i = 0; i < mutable.getSlots(); i++) {
                    ItemStack mStack = mutable.getStackInSlot(i);
                    int pulled = Math.min(mStack.getMaxStackSize(), mutable.getSlotLimit(i)) - mStack.getCount();
                    if (mStack.isEmpty() || pulled <= 0) {
                        continue;
                    }

                    for (j = 0; j < contents.getSlots(); j++) {
                        ItemStack current = contents.getStackInSlot(j);
                        if (current.isEmpty()) {
                            continue;
                        }

                        if (ItemStack.isSameItemSameComponents(current, mStack)) {
                            ItemStack topped = contents.extractItem(j, pulled, true);
                            ItemStack remain = mutable.insertItem(i, topped, true);
                            int filled = topped.getCount() - remain.getCount();
                            if (filled > 0) {
                                contents.extractItem(j, filled, false);
                                mutable.insertItem(i, topped, false);
                                changed = true;
                                pulled -= filled;
                                if (pulled <= 0) {
                                    break;
                                }
                            }
                        }
                    }
                }

                if (!changed) {
                    ItemStack template;
                    int k = 0;
                    i = mutable.activeSize();
                    while (k < mutable.activeSize() && i < mutable.getSlots()) {
                        // 跳过已有内容的非活跃槽位
                        while (i < mutable.getSlots() && !mutable.getStackInSlot(i).isEmpty()) i++;
                        if (i >= mutable.getSlots()) break;

                        template = mutable.getStackInSlot(k);
                        if (!template.isEmpty() && template.getCount() >= Math.min(template.getMaxStackSize(),
                                                                                   mutable.getSlotLimit(k))) {
                            int pulled = mutable.getSlotLimit(i);
                            for (j = 0; j < contents.getSlots(); j++) {
                                ItemStack current = contents.getStackInSlot(j);
                                if (current.isEmpty()) {
                                    continue;
                                }

                                if (ItemStack.isSameItemSameComponents(template, current)) {
                                    ItemStack topped = contents.extractItem(j, pulled, true);
                                    ItemStack remain = mutable.insertItem(i, topped, true);
                                    int filled = topped.getCount() - remain.getCount();
                                    if (filled > 0) {
                                        contents.extractItem(j, filled, false);
                                        mutable.insertItem(i, topped, false);
                                        changed = true;
                                        pulled -= filled;
                                        if (pulled <= 0) {
                                            break;
                                        }
                                    }
                                }
                            }
                            if (pulled <= 0) i++;  // 槽位已填满，推进到下一个非活跃槽位
                        }
                        k++;  // 当前模板处理完毕（无论是否有效、是否找到物品）
                    }
                }

                if (changed) {
                    GummyContents.set(stack, mutable);
                }

                // Client returns SUCCESS, so must always CONSUME
                return InteractionResult.CONSUME;
            }
        }
        return super.useOn(context);
    }

    public MutableContents getMutable(ItemStack stack) {
        if (!stack.has(this.type.dataComponentType)) {
            stack.set(this.type.dataComponentType, this.type.defaultContents());
        }
        GummyContents contents = stack.get(this.type.dataComponentType);
        assert contents != null;
        return new MutableContents(contents, this.tier);
    }

    private static int doFill(IItemHandler from, ItemStack template, MutableContents to, int slotId) {
        ItemStack dest = to.getStackInSlot(slotId);
        if (!dest.isEmpty() && !ItemStack.isSameItemSameComponents(template, dest)) {
            if (template.isEmpty()) {
                template = dest;
            }
            else {
                return 0;
            }
        }

        int pulled = Math.min(to.getSlotLimit(slotId), template.getMaxStackSize());
        int maxSize = pulled;
        pulled -= dest.getCount();
        for (int j = 0; j < from.getSlots(); j++) {
            ItemStack current = from.getStackInSlot(j);
            if (current.isEmpty()) {
                continue;
            }

            if (ItemStack.isSameItemSameComponents(current, template)) {
                ItemStack topped = from.extractItem(j, pulled, true);
                ItemStack remain = to.insertItem(slotId, topped, true);
                int toFill = topped.getCount() - remain.getCount();
                if (toFill > 0) {
                    from.extractItem(j, toFill, false);
                    to.insertItem(slotId, topped, false);
//                                changed = true;
                    pulled -= toFill;
                    if (pulled <= 0) {
                        break;
                    }
                }
            }
        }
        return maxSize - pulled;
    }
}
