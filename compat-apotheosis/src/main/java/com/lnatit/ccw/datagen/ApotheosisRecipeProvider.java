package com.lnatit.ccw.datagen;

import com.lnatit.ccw.compat.ApotheosisCompats;
import dev.shadowsoffire.apotheosis.Apoth;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.concurrent.CompletableFuture;

public class ApotheosisRecipeProvider extends RecipeProvider {
    protected ApotheosisRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output, HolderLookup.Provider registries) {
        RecipeOutput conditional = output.withConditions(new ModLoadedCondition("apotheosis"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BREWING, ApotheosisCompats.KNOWLEDGE_BOTTLE)
                .requires(Apoth.Items.GEM_DUST.value())
                .requires(Items.EXPERIENCE_BOTTLE)
                .unlockedBy("has_gem_dust", has(Apoth.Items.GEM_DUST.value()))
                .save(conditional);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BREWING, ApotheosisCompats.VITALITY_BERRY)
                .requires(Apoth.Items.GEM_DUST.value())
                .requires(Items.SWEET_BERRIES)
                .unlockedBy("has_gem_dust", has(Apoth.Items.GEM_DUST.value()))
                .save(conditional);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BREWING, ApotheosisCompats.MALIGNANT_BERRY)
                .requires(Apoth.Items.GEM_DUST.value())
                .requires(Items.FERMENTED_SPIDER_EYE)
                .requires(Items.SWEET_BERRIES)
                .unlockedBy("has_gem_dust", has(Apoth.Items.GEM_DUST.value()))
                .save(conditional);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BREWING, ApotheosisCompats.EROSION_SHELL)
                .requires(Apoth.Items.GEM_DUST.value())
                .requires(Items.FERMENTED_SPIDER_EYE)
                .requires(Items.SHULKER_SHELL)
                .unlockedBy("has_gem_dust", has(Apoth.Items.GEM_DUST.value()))
                .save(conditional);
    }
}

