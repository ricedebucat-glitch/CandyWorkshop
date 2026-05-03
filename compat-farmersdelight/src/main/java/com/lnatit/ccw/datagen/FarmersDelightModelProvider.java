package com.lnatit.ccw.datagen;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.compat.FarmersDelightCompats;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class FarmersDelightModelProvider extends ItemModelProvider {
    public FarmersDelightModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CandyWorkshop.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(FarmersDelightCompats.GLAZED_MEAT_RICE.get());
    }
}

