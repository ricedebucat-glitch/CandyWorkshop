package com.lnatit.ccw.item;

import net.minecraft.world.item.Item;

public abstract class TieredItem extends Item
{
    protected final Tier tier;

    public TieredItem(Properties properties, Tier tier) {
        super(properties);
        this.tier = tier;
    }
}
