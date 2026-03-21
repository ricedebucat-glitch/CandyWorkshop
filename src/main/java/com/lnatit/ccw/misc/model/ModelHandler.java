package com.lnatit.ccw.misc.model;

import com.lnatit.ccw.CandyWorkshop;
import com.lnatit.ccw.item.ItemRegistry;
import com.lnatit.ccw.item.MilkExtractorItem;
import com.lnatit.ccw.item.sugaring.Sugar;
import com.lnatit.ccw.item.sugaring.Sugars;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent;

import java.util.Map;

@EventBusSubscriber(modid = CandyWorkshop.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public interface ModelHandler
{
    ResourceLocation BROKEN = ResourceLocation.fromNamespaceAndPath(CandyWorkshop.MODID, "broken");
//    public static final ResourceLocation TIER =  ResourceLocation.fromNamespaceAndPath(CandyWorkshop.MODID, "tier");

    @SubscribeEvent
    static void onPropertyRegister(FMLClientSetupEvent event) {
        event.enqueueWork(() -> ItemProperties.register(ItemRegistry.MILK_EXTRACTOR.get(),
                                                        BROKEN,
                                                        (stack, level, entity, seed) -> MilkExtractorItem.isBroken(stack)
                                                                                        ? 1.0F
                                                                                        : 0.0F));
//        event.enqueueWork(() -> ItemProperties.register(
//                ItemRegistry.GUMMY_MAGAZINE.get(),
//                TIER,
//                (stack, level, entity, seed) -> {
//                    GummyContents m = stack.get(ItemRegistry.MAGAZINE_CONTENTS_DCTYPE);
//                    if (m != null) {
//                        return (float) m.tier().ordinal() / 2;
//                    }
//                    return 0;
//                }
//        ));
    }

    ResourceLocation GUMMY_GLAZER = CandyWorkshop.id("item/gummy_glazer_base");
    ResourceLocation NETHER_GLAZER = CandyWorkshop.id("item/nether_glazer_base");
    ResourceLocation ENDER_GLAZER = CandyWorkshop.id("item/ender_glazer_base");

    @SubscribeEvent
    static void onModelRegister(ModelEvent.RegisterAdditional event) {
        for (Holder<Sugar> sugarHolder : Sugars.SUGARS.getEntries()) {
            event.register(ModelResourceLocation.standalone(Sugar.getModelId(sugarHolder)));
        }

        event.register(ModelResourceLocation.standalone(GUMMY_GLAZER));
        event.register(ModelResourceLocation.standalone(NETHER_GLAZER));
        event.register(ModelResourceLocation.standalone(ENDER_GLAZER));
    }

    @SubscribeEvent
    static void onModelBake(ModelEvent.ModifyBakingResult event) {
        Map<ModelResourceLocation, BakedModel> modelMap = event.getModels();
        ModelResourceLocation gummy = ModelResourceLocation.inventory(ItemRegistry.GUMMY.getId());
        modelMap.compute(gummy, (k, original) -> SugarOverrideHandler.getModel(original));
    }
}
