package net.marchakaa.mccourse.datagen;

import net.marchakaa.mccourse.MCCourseMod;
import net.marchakaa.mccourse.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future, CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, future, completableFuture, MCCourseMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.ALEXANDRITE_HELMET.get(),
                     ModItems.ALEXANDRITE_CHESTPLATE.get(),
                     ModItems.ALEXANDRITE_LEGGINGS.get(),
                     ModItems.ALEXANDRITE_BOOTS.get());
        this.tag(ItemTags.MUSIC_DISCS)
                .add(ModItems.BAR_BRAWL_RECORD.get())
                .add(ModItems.VRGO_DO_RE_MI_RECORD.get());
    }

    @Override
    public String getName() {
        return "Item Tags";
    }
}
