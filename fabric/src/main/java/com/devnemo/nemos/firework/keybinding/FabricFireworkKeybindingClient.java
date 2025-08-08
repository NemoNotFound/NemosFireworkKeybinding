package com.devnemo.nemos.firework.keybinding;

import com.devnemo.nemos.firework.keybinding.client.KeyMappings;
import com.devnemo.nemos.firework.keybinding.service.FireworkKeyMappingService;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class FabricFireworkKeybindingClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyMappings.init();

        var fireworkKeyMappingService = FireworkKeyMappingService.getInstance();

        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            while (KeyMappings.FIREWORK.get().consumeClick()) {
                fireworkKeyMappingService.handleFireworkKeyPressedEvent(minecraft);
            }
        });
    }
}
