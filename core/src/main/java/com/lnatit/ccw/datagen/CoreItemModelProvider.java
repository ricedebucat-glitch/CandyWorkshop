package com.lnatit.ccw.datagen;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.item.sugaring.Sugar;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public abstract class CoreItemModelProvider extends ItemModelProvider
{
    public CoreItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CandyWorkshop.MODID, existingFileHelper);
    }

    protected void sugarModel(DeferredHolder<Sugar, ?> sugar) {
        basicItem(Sugar.getItemModel(sugar));
    }
}
