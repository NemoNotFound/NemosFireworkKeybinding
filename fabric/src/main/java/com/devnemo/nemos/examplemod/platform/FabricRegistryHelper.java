package com.devnemo.nemos.examplemod.platform;

import com.devnemo.nemos.examplemod.Constants;
import com.devnemo.nemos.examplemod.platform.services.IRegistryHelper;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.devnemo.nemos.examplemod.Constants.MOD_ID;

public class FabricRegistryHelper implements IRegistryHelper {

    @Override
    public Supplier<Block> registerBlock(String id, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties properties) {
        return registerSupplierWithResourceKey(BuiltInRegistries.BLOCK, id, key -> function.apply(properties.setId(key)));
    }

    @Override
    public Supplier<Block> registerBlock(String id, Function<BlockBehaviour.Properties, Block> function, Supplier<BlockBehaviour.Properties> properties) {
        return registerSupplierWithResourceKey(BuiltInRegistries.BLOCK, id, key -> function.apply(properties.get().setId(key)));
    }

    @Override
    public Supplier<Item> registerItem(String id, Function<Item.Properties, Item> function, Item.Properties properties) {
        return registerSupplierWithResourceKey(BuiltInRegistries.ITEM, id, key -> function.apply(properties.setId(key)));
    }

    @Override
    public Supplier<Item> registerItem(String id, Function<Item.Properties, Item> function, Supplier<Item.Properties> properties) {
        return  registerSupplierWithResourceKey(BuiltInRegistries.ITEM, id, key -> function.apply(properties.get().setId(key)));
    }

    @Override
    public <T extends Entity> Supplier<EntityType<T>> registerEntity(String id, EntityType.Builder<T> entityTypeBuilder) {
        var resourceLocation = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, id);
        var resourceKey = ResourceKey.create(Registries.ENTITY_TYPE, resourceLocation);

        return registerSupplier(BuiltInRegistries.ENTITY_TYPE, id, () -> entityTypeBuilder.build(resourceKey));
    }

    @Override
    public <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> creativeModeTab) {
        return registerSupplier(BuiltInRegistries.CREATIVE_MODE_TAB, id, creativeModeTab);
    }

    @Override
    public CreativeModeTab.Builder createCreativeModeTab() {
        return FabricItemGroup.builder();
    }

    @Override
    public Supplier<KeyMapping> registerKeyMapping(KeyMapping keyMapping) {
        var registeredKeyMapping = KeyBindingHelper.registerKeyBinding(keyMapping);

        return () -> registeredKeyMapping;
    }

    @Override
    public Supplier<Holder<Attribute>> registerAttribute(String id, Attribute attribute) {
        var attributeReference = Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, ResourceLocation.fromNamespaceAndPath(MOD_ID, id), attribute);

        return () -> attributeReference;
    }

    private static <T, R extends Registry<? super T>> Supplier<T> registerSupplier(R registry, String id, Supplier<T> object) {
        final var resourceLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
        final var registeredObject = Registry.register((Registry<T>) registry, resourceLocation, object.get());

        return () -> registeredObject;
    }

    private static <T, R extends Registry<T>> Supplier<T> registerSupplierWithResourceKey(R registry, String id, Function<ResourceKey<T>, T> object) {
        final var resourceLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
        final var registeredObject = Registry.register(registry, resourceLocation, object.apply(ResourceKey.create(registry.key(), resourceLocation)));

        return () -> registeredObject;
    }
}
