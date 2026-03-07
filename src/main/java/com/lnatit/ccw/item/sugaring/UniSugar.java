package com.lnatit.ccw.item.sugaring;

import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class UniSugar extends Sugar
{
    private final Type type;
    private final Ingredient ingredient;

    public UniSugar(String name, Type type, Ingredient ingredient) {
        super(name);
        this.type = type;
        this.ingredient = ingredient;
    }

    public enum Type
    {
        OVERWORLD,
        NETHER,
        END
    }
}
