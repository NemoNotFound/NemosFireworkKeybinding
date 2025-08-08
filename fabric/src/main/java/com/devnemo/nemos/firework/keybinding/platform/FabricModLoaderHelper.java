package com.devnemo.nemos.firework.keybinding.platform;

import com.devnemo.nemos.firework.keybinding.platform.services.IModLoaderHelper;
import net.fabricmc.loader.api.FabricLoader;

public class FabricModLoaderHelper implements IModLoaderHelper {

    @Override
    public String getModLoaderName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
