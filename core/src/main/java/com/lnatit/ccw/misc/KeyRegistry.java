package com.lnatit.ccw.misc;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.item.GummyGlazerItem;
import com.lnatit.ccw.item.ItemRegistry;
import com.lnatit.ccw.item.component.GlazerMode;
import com.lnatit.ccw.misc.network.UpdateGlazerModePayload;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(value = Dist.CLIENT, modid = CandyWorkshop.MODID)
public interface KeyRegistry
{
    Lazy<KeyMapping> SWITCH_MODE = Lazy.of(() -> new KeyMapping("key.ccw.switch_mode",
                                                                KeyConflictContext.GUI,
                                                                InputConstants.Type.KEYSYM,
                                                                GLFW.GLFW_KEY_D,
                                                                "key.categories.misc"));

    @SubscribeEvent
    static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(SWITCH_MODE.get());
    }

    @SubscribeEvent
    static void onKeyPressed(ScreenEvent.KeyPressed.Pre event) {
        if (event.getScreen() instanceof AbstractContainerScreen<?> screen) {
            Player player = Minecraft.getInstance().player;
            Slot slot = screen.getSlotUnderMouse();
            if (slot != null && player != null
                && slot.allowModification(player)
                && slot.hasItem()
                && slot.getItem().getItem() instanceof GummyGlazerItem) {
                if (event.getKeyCode() == InputConstants.KEY_LSHIFT) {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundRegistry.UNFOLD_DESC.get(), 1.0f));
                }
                if (SWITCH_MODE.get()
                               .isActiveAndMatches(InputConstants.getKey(event.getKeyCode(), event.getScanCode()))) {
                    GlazerMode old = GlazerMode.getOrDefault(slot.getItem());
                    GlazerMode newMode = old == GlazerMode.SAVE ? GlazerMode.EXTEND : GlazerMode.SAVE;
                    slot.getItem().set(ItemRegistry.GLAZER_MODE_DCTYPE, newMode);
                    // Trigger sound
                    Minecraft.getInstance()
                             .getSoundManager()
                             .play(SimpleSoundInstance.forUI(SoundRegistry.SWITCH_MODE.get(), 1.0f));
                    // Notify server
                    PacketDistributor.sendToServer(new UpdateGlazerModePayload(slot.index, newMode));
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    static void onKeyReleased(ScreenEvent.KeyReleased.Pre event) {
        if (event.getScreen() instanceof AbstractContainerScreen<?> screen) {
            Player player = Minecraft.getInstance().player;
            Slot slot = screen.getSlotUnderMouse();
            if (slot != null && player != null
                && slot.allowModification(player)
                && slot.hasItem()
                && slot.getItem().getItem() instanceof GummyGlazerItem) {
                if (event.getKeyCode() == InputConstants.KEY_LSHIFT) {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundRegistry.FOLD_DESC.get(), 1.0f));
                }
            }
        }
    }
}
