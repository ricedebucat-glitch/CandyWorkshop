package com.lnatit.ccw.datagen;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.compat.apothesis.ApothesisCompats;
import net.minecraft.data.PackOutput;

public class ApotheosisEN_USProvider extends CoreEN_USProvider {
    public ApotheosisEN_USProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void addTranslations() {
        super.addTranslations();

        this.add(ApothesisCompats.MALIGNANT_BERRY.get(), "Malignant Berry");
        this.add(ApothesisCompats.KNOWLEDGE_BOTTLE.get(), "Knowledge Bottle");
        this.add(ApothesisCompats.EROSION_SHELL.get(), "Erosion Shell");
        this.add(ApothesisCompats.VITALITY_BERRY.get(), "Vitality Berry");
    }
}

