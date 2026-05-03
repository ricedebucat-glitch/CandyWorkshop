package com.lnatit.ccw.datagen;

import com.lnatit.ccw.CandyWorkshop;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ReiEN_USProvider extends LanguageProvider {
    public ReiEN_USProvider(PackOutput output) {
        super(output, CandyWorkshop.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add("compat.ccw.rei.title", "Refining");
    }
}

