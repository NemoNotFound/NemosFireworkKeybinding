package com.devnemo.nemos.examplemod;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.devnemo.nemos.examplemod.Constants.MOD_ID;

@Mod(MOD_ID)
public class NeoForgeExampleMod {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final DeferredRegister<Attribute> ATTRIBUTE = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, MOD_ID);

    public NeoForgeExampleMod(IEventBus eventBus) {
        Common.init();

        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        ENTITIES.register(eventBus);
        CREATIVE_TABS.register(eventBus);
        ATTRIBUTE.register(eventBus);
    }
}