package com.lnatit.ccw.misc.model;

import com.lnatit.ccw.item.GummyGlazerItem;
import com.lnatit.ccw.item.ItemRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.ClientHooks;

import static net.minecraft.client.renderer.entity.ItemRenderer.getFoilBufferDirect;


public class GummyGlazerRenderer extends BlockEntityWithoutLevelRenderer
{
    public GummyGlazerRenderer() {
        super(dispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(
            ItemStack stack,
            ItemDisplayContext displayContext,
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,
            int packedOverlay
    ) {
        if (stack.getItem() instanceof GummyGlazerItem glazer) {
            poseStack.pushPose();
            boolean leftHand = displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND
                               || displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
            BakedModel baked = Minecraft.getInstance().getModelManager().getModel(of(glazer));
            baked = baked.getOverrides().resolve(baked, stack, null, null, 0);
            poseStack.translate(0.5F, 0.5F, 0.5F);
            baked = ClientHooks.handleCameraTransforms(poseStack, baked, displayContext, leftHand);
            poseStack.translate(-0.5F, -0.5F, -0.5F);
            for (BakedModel model : baked.getRenderPasses(stack, true)) {
                for (RenderType rendertype : model.getRenderTypes(stack, true)) {
                    VertexConsumer vertexconsumer =
                            getFoilBufferDirect(bufferSource, rendertype, true, stack.hasFoil());
                    Minecraft.getInstance()
                             .getItemRenderer()
                             .renderModelLists(model, stack, packedLight, packedOverlay, poseStack, vertexconsumer);
                }
            }
            poseStack.popPose();
        }
    }

    public static BlockEntityRenderDispatcher dispatcher() {
        return Minecraft.getInstance().getBlockEntityRenderDispatcher();
    }

    public static ModelResourceLocation of(GummyGlazerItem glazer) {
        if (glazer == ItemRegistry.NETHER_GLAZER.get()) {
            return ModelResourceLocation.standalone(ModelHandler.NETHER_GLAZER);
        }
        if (glazer == ItemRegistry.ENDER_GLAZER.get()) {
            return ModelResourceLocation.standalone(ModelHandler.ENDER_GLAZER);
        }
        return ModelResourceLocation.standalone(ModelHandler.GUMMY_GLAZER);
    }
}
