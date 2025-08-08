package com.devnemo.nemos.examplemod.platform.services;

import net.minecraft.client.KeyMapping;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;
import java.util.function.Supplier;

public interface IRegistryHelper {

    Supplier<Block> registerBlock(String id, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties properties);
    Supplier<Block> registerBlock(String id, Function<BlockBehaviour.Properties, Block> function, Supplier<BlockBehaviour.Properties> properties);
    Supplier<Item> registerItem(String id, Function<Item.Properties, Item> function, Item.Properties properties);
    Supplier<Item> registerItem(String id, Function<Item.Properties, Item> function, Supplier<Item.Properties> properties);
    <T extends Entity> Supplier<EntityType<T>> registerEntity(String id, EntityType.Builder<T> entityTypeBuilder);
    <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> creativeModeTab);
    CreativeModeTab.Builder createCreativeModeTab();
    Supplier<KeyMapping> registerKeyMapping(KeyMapping keyMapping);
    Supplier<Holder<Attribute>> registerAttribute(String id, Attribute attribute);
}
