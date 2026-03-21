package com.lnatit.ccw.item;

import com.lnatit.ccw.item.component.IContents;
import net.minecraft.world.item.Item;

public class GummyGlazerItem extends TieredItem
{
    private GummyGlazerItem(Properties properties, Tier tier) {
        super(properties, tier);
    }

    public static GummyGlazerItem create(Tier tier) {
        return new GummyGlazerItem(new Item.Properties().component(IContents.Type.GLAZER.dataComponentType,
                                                                   IContents.Type.GLAZER.defaultContents()), tier);
    }
}
