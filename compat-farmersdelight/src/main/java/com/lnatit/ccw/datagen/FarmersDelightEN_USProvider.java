package com.lnatit.ccw.datagen;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.compat.FarmersDelightCompats;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class FarmersDelightEN_USProvider extends CoreEN_USProvider {
    public FarmersDelightEN_USProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void addTranslations() {
        super.addTranslations();

        this.add(FarmersDelightCompats.GLAZED_MEAT_RICE.get(), "Glazed Meat Rice");
    }
}

