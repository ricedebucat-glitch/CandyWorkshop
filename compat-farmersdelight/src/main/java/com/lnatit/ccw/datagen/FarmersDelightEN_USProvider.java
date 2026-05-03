package com.lnatit.ccw.datagen;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.compat.FarmersDelightCompats;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class FarmersDelightEN_USProvider extends LanguageProvider {
    public FarmersDelightEN_USProvider(PackOutput output) {
        super(output, CandyWorkshop.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add(FarmersDelightCompats.GLAZED_MEAT_RICE.get(), "Glazed Meat Rice");
    }
}

