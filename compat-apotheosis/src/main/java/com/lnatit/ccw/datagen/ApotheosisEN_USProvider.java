package com.lnatit.ccw.datagen;

import com.lnatit.ccw.compat.ApotheosisCompats;
import net.minecraft.data.PackOutput;

public class ApotheosisEN_USProvider extends CoreEN_USProvider {
    public ApotheosisEN_USProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void addTranslations() {
        super.addTranslations();

        this.add(ApotheosisCompats.MALIGNANT_BERRY.get(), "Malignant Berry");
        this.add(ApotheosisCompats.KNOWLEDGE_BOTTLE.get(), "Knowledge Bottle");
        this.add(ApotheosisCompats.EROSION_SHELL.get(), "Erosion Shell");
        this.add(ApotheosisCompats.VITALITY_BERRY.get(), "Vitality Berry");
    }
}

