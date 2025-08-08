package com.devnemo.nemos.firework.keybinding.platform;

import com.devnemo.nemos.firework.keybinding.platform.services.IRegistryHelper;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

import java.util.function.Supplier;

public class FabricRegistryHelper implements IRegistryHelper {

    @Override
    public Supplier<KeyMapping> registerKeyMapping(KeyMapping keyMapping) {
        var registeredKeyMapping = KeyBindingHelper.registerKeyBinding(keyMapping);

        return () -> registeredKeyMapping;
    }
}
