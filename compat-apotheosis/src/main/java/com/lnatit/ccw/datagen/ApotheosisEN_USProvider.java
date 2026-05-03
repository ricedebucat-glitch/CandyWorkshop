package com.lnatit.ccw.datagen;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.compat.apothesis.ApothesisCompats;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ApotheosisEN_USProvider extends LanguageProvider {
    public ApotheosisEN_USProvider(PackOutput output) {
        super(output, CandyWorkshop.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add(ApothesisCompats.MALIGNANT_BERRY.get(), "Malignant Berry");
        this.add(ApothesisCompats.KNOWLEDGE_BOTTLE.get(), "Knowledge Bottle");
        this.add(ApothesisCompats.EROSION_SHELL.get(), "Erosion Shell");
        this.add(ApothesisCompats.VITALITY_BERRY.get(), "Vitality Berry");
    }
}

