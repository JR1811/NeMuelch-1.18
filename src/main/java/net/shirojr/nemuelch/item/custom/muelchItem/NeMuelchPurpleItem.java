package net.shirojr.nemuelch.item.custom.muelchItem;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.shirojr.nemuelch.NeMuelch.FUSE;

public class NeMuelchPurpleItem extends MilkBucketItem {

    public NeMuelchPurpleItem(Settings settings) { super(settings); }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)user;
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
            stack.decrement(1);
        }

        if (!world.isClient) {
            user.clearStatusEffects();
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 2400, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1400, 1));
        }

        return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.nemuelch.purple_muelch.tooltip.shift.line1"));
            tooltip.add(new TranslatableText("item.nemuelch.purple_muelch.tooltip.shift.line2"));
            tooltip.add(new TranslatableText("item.nemuelch.purple_muelch.tooltip.shift.line3"));
            tooltip.add(new TranslatableText("item.nemuelch.purple_muelch.tooltip.shift.line4"));
            tooltip.add(new TranslatableText("item.nemuelch.purple_muelch.tooltip.shift.line5"));
        }

        else {
            tooltip.add(new TranslatableText("item.nemuelch.tooltip.expand.line1"));
            tooltip.add(new TranslatableText("item.nemuelch.tooltip.expand.line2"));
        }
    }
}
