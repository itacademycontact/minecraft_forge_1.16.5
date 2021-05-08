package com.example.examplemod.setup;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entities.hog.HogEntity;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);
    public static final DeferredRegister<EntityType<?>> MOBS = DeferredRegister.create(ForgeRegistries.ENTITIES, ExampleMod.MOD_ID);

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // attach DeferredRegister to the event bus
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        MOBS.register(modEventBus);
    }

    public static final RegistryObject<Item> BEEF_COOKED = ITEMS.register("beef_cooked_vn", () ->
            new Item(
                    new Item.Properties().food(new Food.Builder().meat().fast().nutrition(10).build())
                            .tab(ItemGroup.TAB_FOOD)
            )
    );

    public static final RegistryObject<Item> DIAMOND_PICKAXE_EAT = ITEMS.register("diamond_pickaxe_vn", () ->
            new Item(new Item.Properties().food(new Food.Builder().meat().fast().nutrition(20).build())
                    .tab(ItemGroup.TAB_FOOD))
    );

    public static final RegistryObject<Block> BLOCK_KPI = BLOCKS.register("block_kpi_vn", () ->
            new Block(Block.Properties.of(Material.STONE)
                    .strength(5.f, 6.f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE))
    );

    public static final RegistryObject<Item> ITEM_KPI = ITEMS.register("block_kpi_vn", () ->
            new BlockItem(
                    BLOCK_KPI.get(),
                    new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)
            )
    );

    public static final RegistryObject<EntityType<HogEntity>> HOG = MOBS.register("hog",
            () -> {
                System.out.println("Ok Register Hog");
                return EntityType.Builder.of(HogEntity::new, EntityClassification.CREATURE)
                        .sized(1.0f, 1.0f) // Hitbox Size
                        .fireImmune()
                        .build(new ResourceLocation(ExampleMod.MOD_ID, "hog").toString());
            });
}