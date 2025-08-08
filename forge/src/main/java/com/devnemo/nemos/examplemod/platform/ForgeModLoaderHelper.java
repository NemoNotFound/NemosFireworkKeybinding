package com.devnemo.nemos.examplemod.platform;

import com.devnemo.nemos.examplemod.platform.services.IModLoaderHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgeModLoaderHelper implements IModLoaderHelper {

    @Override
    public String getModLoaderName() {
        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }
}