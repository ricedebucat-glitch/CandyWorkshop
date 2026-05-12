package com.lnatit.ccw.misc.network;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.item.GummyGlazerItem;
import com.lnatit.ccw.item.ItemRegistry;
import com.lnatit.ccw.item.component.GlazerMode;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record UpdateGlazerModePayload(int slotId, GlazerMode mode) implements CustomPacketPayload {
    public static final Type<UpdateGlazerModePayload> TYPE = new Type<>(CandyWorkshop.id("update_glazer_mode"));
    public static final StreamCodec<RegistryFriendlyByteBuf, UpdateGlazerModePayload> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.VAR_INT, UpdateGlazerModePayload::slotId, GlazerMode.STREAM_CODEC, UpdateGlazerModePayload::mode, UpdateGlazerModePayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(UpdateGlazerModePayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            Slot slot = player.containerMenu.getSlot(payload.slotId());
            if (slot.allowModification(player)
                    && slot.hasItem()
                    && slot.getItem().getItem() instanceof GummyGlazerItem) {
                slot.getItem().set(ItemRegistry.GLAZER_MODE_DCTYPE, payload.mode());
            }
        });
    }
}
