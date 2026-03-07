package com.lnatit.ccw.item.sugaring.modifier;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.misc.RegRegistry;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Modifiers
{
    public static final DeferredRegister<IModifier> MODIFIERS = DeferredRegister.create(RegRegistry.MODIFIER_KEY, CandyWorkshop.MODID);

    public static final DeferredHolder<IModifier, ? extends IModifier> EMPTY = MODIFIERS.register("empty", () -> IModifier.EMPTY);
    public static final DeferredHolder<IModifier, MilkyModifier> MILKY = MODIFIERS.register("milky", MilkyModifier::new);
}
