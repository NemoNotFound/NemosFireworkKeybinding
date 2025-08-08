package com.devnemo.nemos.firework.keybinding.platform.services;

import net.minecraft.client.KeyMapping;

import java.util.function.Supplier;

public interface IRegistryHelper {

    Supplier<KeyMapping> registerKeyMapping(KeyMapping keyMapping);
}
