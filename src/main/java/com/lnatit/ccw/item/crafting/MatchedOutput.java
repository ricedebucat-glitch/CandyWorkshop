package com.lnatit.ccw.item.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.common.util.INBTSerializable;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class MatchedOutput implements INBTSerializable<CompoundTag>
{
    private ItemStack output;
    @Nullable
    private RecipeHolder<RefiningRecipe> recipe;

    public void setEmpty() {
        this.output = ItemStack.EMPTY;
    }

    public boolean isEmpty() {
        return this.output.isEmpty();
    }

    public ItemStack output() {
        return this.output;
    }

    public void consume(RefiningInput input, Consumer<ItemStack> remainderConsumer) {
        if (this.recipe != null) {
            this.recipe.value().getRemainingItems(input).forEach(remainderConsumer);
            input.milk().shrink(this.recipe.value().milk().count());
            input.sugar().shrink(this.recipe.value().sugar().count());
        }
        else {
            // get default remainders here
        }
        input.main().shrink(1);
        input.extra().shrink(1);

    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        if (!output.isEmpty()) {
            tag.put("output", this.output.save(provider));
        }
        if (this.recipe != null) {
            tag.putString("recipe", this.recipe.id().toString());
        }
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.output = ItemStack.parseOptional(provider, nbt.getCompound("output"));
//        if (nbt.contains("recipe", Tag.TAG_STRING)) {
//            this.recipe = ;
//        }
    }
}
