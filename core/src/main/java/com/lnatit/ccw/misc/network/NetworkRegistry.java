package com.lnatit.ccw.misc.network;

import com.lnatit.ccw.CandyWorkshop;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = CandyWorkshop.MODID)
public interface NetworkRegistry {
    String PROTOCOL_VERSION = "1";

    @SubscribeEvent
    static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(PROTOCOL_VERSION);
        registrar.playToServer(
                UpdateGlazerModePayload.TYPE,
                UpdateGlazerModePayload.STREAM_CODEC,
                UpdateGlazerModePayload::handle
        );
    }
}
