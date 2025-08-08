package com.devnemo.nemos.firework.keybinding.platform;

import com.devnemo.nemos.firework.keybinding.platform.services.IRegistryHelper;
import net.minecraft.client.KeyMapping;

import java.util.function.Supplier;

public class ForgeRegistryHelper implements IRegistryHelper {

    @Override
    public Supplier<KeyMapping> registerKeyMapping(KeyMapping keyMapping) {
        return () -> keyMapping;
    }
}
