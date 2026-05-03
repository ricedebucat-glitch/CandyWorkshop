package com.lnatit.ccw.misc.keybind;

import com.lnatit.ccw.CandyWorkshop;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(value = Dist.CLIENT, modid = CandyWorkshop.MODID, bus = EventBusSubscriber.Bus.MOD)
public class KeyRegistry
{
    public static final Lazy<KeyMapping> SWITCH_MODE = Lazy.of(() -> new KeyMapping("key.ccw.switch_mode",
                                                                                    KeyConflictContext.GUI,
                                                                                    InputConstants.Type.KEYSYM,
                                                                                    GLFW.GLFW_KEY_D,
                                                                                    "key.categories.misc"));

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(SWITCH_MODE.get());
    }
}
