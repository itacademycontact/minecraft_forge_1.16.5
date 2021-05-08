package com.example.examplemod.entities;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entities.hog.HogEntity;
import com.example.examplemod.setup.Registration;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID)
public class Zombie {

    @SubscribeEvent
    public static void modifyJoiningZombie(EntityJoinWorldEvent e) {
        if (!(e.getEntity() instanceof ZombieEntity)){
            return;
        }

        ZombieEntity z = (ZombieEntity) e.getEntity();
        z.setItemInHand(Hand.MAIN_HAND, new ItemStack(Registration.BLOCK_KPI.get()));
        z.setItemSlot(EquipmentSlotType.HEAD, new ItemStack(Items.DIAMOND_HELMET));
        z.setItemSlot(EquipmentSlotType.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
        z.setItemSlot(EquipmentSlotType.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS));
        z.setItemSlot(EquipmentSlotType.FEET, new ItemStack(Items.DIAMOND_BOOTS));
    }

    @SubscribeEvent
    public static void onCreeperDeath(LivingDeathEvent e) {
        if (!(e.getEntity() instanceof ZombieEntity)){
            return;
        }

        HogEntity mz = new HogEntity(Registration.HOG.get(), e.getEntity().getCommandSenderWorld());
        mz.setItemInHand(Hand.MAIN_HAND, new ItemStack(Registration.BEEF_COOKED.get()));
        mz.setPos(e.getEntity().getX(), e.getEntity().getY(), e.getEntity().getZ());
        e.getEntity().getCommandSenderWorld().addFreshEntity(mz);
    }
}
