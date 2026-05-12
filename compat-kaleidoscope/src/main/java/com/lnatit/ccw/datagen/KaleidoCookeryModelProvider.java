package com.lnatit.ccw.datagen;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.compat.CookeryCompat;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class KaleidoCookeryModelProvider extends CoreItemModelProvider {
    public KaleidoCookeryModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CandyWorkshop.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        super.registerModels();
        
        basicItem(CookeryCompat.SWEET_AND_SOUR_MEAT_SAUCE.getId());
        basicItem(CookeryCompat.FRESH_VEGGIE_SAUCE.getId());
        basicItem(CookeryCompat.LACTO_BOLT_RED_SAUCE.getId());
        basicItem(CookeryCompat.SASHIMI_SIDE_SAUCE.getId());
        basicItem(CookeryCompat.ULTRA_RICH_MARROW_CREAM.getId());
        basicItem(CookeryCompat.SILENCED_SAUCE.getId());
        basicItem(CookeryCompat.HEARTH_WARM_MARROW_SAUCE.getId());
        basicItem(CookeryCompat.LAMB_CARROT_SAUCE.getId());
    }
}

