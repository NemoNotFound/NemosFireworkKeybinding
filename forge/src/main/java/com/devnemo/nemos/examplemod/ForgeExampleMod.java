package com.devnemo.nemos.examplemod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.devnemo.nemos.examplemod.Constants.MOD_ID;

@Mod(MOD_ID)
public class ForgeExampleMod {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final DeferredRegister<Attribute> ATTRIBUTE = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, MOD_ID);

    public ForgeExampleMod(FMLJavaModLoadingContext context) {
        final var modBusGroup = context.getModBusGroup();
        Common.init();

        BLOCKS.register(modBusGroup);
        ENTITIES.register(modBusGroup);
        ITEMS.register(modBusGroup);
        CREATIVE_TABS.register(modBusGroup);
        ATTRIBUTE.register(modBusGroup);
    }
}