package com.lnatit.ccw.item.component;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public record PainterContents(NonNullList<ItemStack> contents)
{
}
