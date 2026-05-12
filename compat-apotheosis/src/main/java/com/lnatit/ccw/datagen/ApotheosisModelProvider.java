package com.lnatit.ccw.datagen;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.compat.apothesis.ApothesisCompats;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ApotheosisModelProvider extends CoreItemModelProvider {
    public ApotheosisModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        super.registerModels();
        basicItem(ApothesisCompats.MALIGNANT_BERRY.asItem());
        basicItem(ApothesisCompats.KNOWLEDGE_BOTTLE.asItem());
        basicItem(ApothesisCompats.EROSION_SHELL.asItem());
        basicItem(ApothesisCompats.VITALITY_BERRY.asItem());
    }
}

