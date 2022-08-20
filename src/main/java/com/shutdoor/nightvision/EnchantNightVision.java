package com.shutdoor.nightvision;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

import static com.shutdoor.nightvision.NightVision.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class EnchantNightVision extends Enchantment {
    public EnchantNightVision() {
        super(Rarity.UNCOMMON, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[]{
                EquipmentSlot.HEAD
        });
    }

    @Override
    public int getMinCost(int level) {
        return 15;
    }

    @Override
    public int getMaxLevel() {return 1;}

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        ItemStack helment = event.player.getItemBySlot(EquipmentSlot.HEAD);
        MobEffect effect = MobEffects.NIGHT_VISION;
        Boolean hasEffect = event.player.hasEffect(effect);
        int enchantLevel = EnchantmentHelper.getItemEnchantmentLevel(NightVision.nightvision, helment);


        if(enchantLevel > 0 && !hasEffect){
            MobEffectInstance playerEffect = new MobEffectInstance(effect, 1000000, 100, false, false);
            playerEffect.setNoCounter(true);
           event.player.addEffect(playerEffect);
       }else{
           event.player.removeEffect(effect);
       }
    }
}
