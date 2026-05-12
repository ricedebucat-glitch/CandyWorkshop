package com.lnatit.ccw.datagen;

import com.lnatit.ccw.compat.CookeryCompat;
import net.minecraft.data.PackOutput;

public class KaleidoCookeryEN_USProvider extends CoreEN_USProvider {
    public KaleidoCookeryEN_USProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void addTranslations() {
        super.addTranslations();

        this.add(CookeryCompat.SWEET_AND_SOUR_MEAT_SAUCE.get(), "Sweet & Sour Meat Sauce");
        this.add(CookeryCompat.FRESH_VEGGIE_SAUCE.get(), "Fresh Veggie Sauce");
        this.add(CookeryCompat.LACTO_BOLT_RED_SAUCE.get(), "Lacto-bolt Red Sauce");
        this.add(CookeryCompat.SASHIMI_SIDE_SAUCE.get(), "Sashimi Side Sauce");
        this.add(CookeryCompat.ULTRA_RICH_MARROW_CREAM.get(), "Ultra-rich Marrow Cream");
        this.add(CookeryCompat.SILENCED_SAUCE.get(), "Silenced Sauce");
        this.add(CookeryCompat.HEARTH_WARM_MARROW_SAUCE.get(), "Hearth-warm Marrow Sauce");
        this.add(CookeryCompat.LAMB_CARROT_SAUCE.get(), "Lamb Carrot Sauce");
    }
}

