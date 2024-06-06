package net.marchakaa.mccourse.item.custom;

import net.marchakaa.mccourse.util.ModTags;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

public class PaxelItem extends DiggerItem implements Vanishable {
    public PaxelItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pAttackDamageModifier, pAttackSpeedModifier, pTier, ModTags.Blocks.PAXEL_MINEABLE, pProperties);
    }

    @Override
    public Component getName(ItemStack pStack) {
        int customColor = 0x45d9cc;

        return Component.translatable("item.mccourse.alexandrite_paxel").withStyle(style -> style.withColor(customColor));
    }

    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        tooltip.add(Component.literal("A powerful paxel made of alexandrite.").withStyle(style -> style.withColor(TextColor.fromRgb(0xc045d9))));
    }
}
