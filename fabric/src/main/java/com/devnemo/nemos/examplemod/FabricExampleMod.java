package com.devnemo.nemos.examplemod;

import net.fabricmc.api.ModInitializer;

public class FabricExampleMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Common.init();
    }
}
