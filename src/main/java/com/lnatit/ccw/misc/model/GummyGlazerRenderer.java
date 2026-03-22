package com.lnatit.ccw.misc.model;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.item.GummyGlazerItem;
import com.lnatit.ccw.item.ItemRegistry;
import com.lnatit.ccw.item.component.GummyContents;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.ClientHooks;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.client.renderer.entity.ItemRenderer.getFoilBufferDirect;


public class GummyGlazerRenderer extends BlockEntityWithoutLevelRenderer {
    public static final double STEP = 0.0625;

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
            boolean leftHand = displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND
                    || displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND;

            ItemTransform flip = new ItemTransform(
                    new Vector3f(0, 180, 0),
                    new Vector3f(),
                    new Vector3f(1, 1, 1)
            );







            poseStack.translate(0.5F, 0.5F, 0.5F);
            poseStack.pushPose();
            BakedModel baked = Minecraft.getInstance().getModelManager().getModel(of(glazer));
            // maybe useless cuz no overrides here
            baked = baked.getOverrides().resolve(baked, stack, null, null, 0);

//            flip.apply(leftHand, poseStack);

            baked = ClientHooks.handleCameraTransforms(poseStack, baked, displayContext, leftHand);
            poseStack.translate(-0.5F, -0.5F, -0.5F);
            renderBaked(baked, stack, poseStack, bufferSource, packedLight, packedOverlay);
            poseStack.popPose();

            ItemTransform rotate = new ItemTransform(
                    new Vector3f(0, 0, 0),
                    new Vector3f(0, 5f, 2.5f).mul((float) STEP),
                    new Vector3f(.25f, .25f, .25f)
            );

            ItemTransform transform = new ItemTransform(
                    new Vector3f(0, 160, -8),
                    // 1/16 x
                    new Vector3f(4.25f, 4.25f, -1.25f).mul((float) STEP),
//                    new Vector3f(),
                    new Vector3f(0.6f, 0.6f, 0.6f)
            );


            if (true && stack.has(ItemRegistry.GLAZER_CONTENTS_DCTYPE)) {
                GummyContents contents = stack.get(ItemRegistry.GLAZER_CONTENTS_DCTYPE);
                for (ItemStack gummy : contents.items()) {
                    if (gummy.isEmpty())
                        continue;

                    poseStack.pushPose();
                    baked = Minecraft.getInstance().getItemRenderer().getModel(gummy, Minecraft.getInstance().level, Minecraft.getInstance().player, 0);
                    // Why here mojang resolved it twice?
                    baked = baked.getOverrides().resolve(baked, gummy, Minecraft.getInstance().level, Minecraft.getInstance().player, 0);
//                    baked = ClientHooks.handleCameraTransforms(poseStack, baked, displayContext, leftHand);

                    flip.apply(leftHand, poseStack);
                    rotate.apply(leftHand, poseStack);
//                    transform.apply(leftHand, poseStack);


                    poseStack.translate(-0.5F, -0.5F, -0.5F);
                    renderBaked(baked, gummy, poseStack, bufferSource, packedLight, packedOverlay);
                    poseStack.popPose();
                }
            }
        }
    }

    private static void renderBaked(BakedModel baked, ItemStack stack, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        for (BakedModel model : baked.getRenderPasses(stack, true)) {
            for (RenderType rendertype : model.getRenderTypes(stack, true)) {
                VertexConsumer vertexconsumer =
                        getFoilBufferDirect(bufferSource, rendertype, true, stack.hasFoil());
                Minecraft.getInstance()
                        .getItemRenderer()
                        .renderModelLists(model, stack, packedLight, packedOverlay, poseStack, vertexconsumer);
            }
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

    private record ModelContext(ItemStack stack, BakedModel baked) {

    }
}
