package com.devnemo.nemos.firework.keybinding.event;

import com.devnemo.nemos.firework.keybinding.client.KeyMappings;
import com.devnemo.nemos.firework.keybinding.service.FireworkKeyMappingService;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

import static com.devnemo.nemos.firework.keybinding.Constants.MOD_ID;

@EventBusSubscriber(value = Dist.CLIENT, modid = MOD_ID)
public class ClientEventHandler {

    public static final FireworkKeyMappingService fireworkKeyMappingService = FireworkKeyMappingService.getInstance();

    @SubscribeEvent
    public static void handleFireworkKeyPressedEvent(ClientTickEvent.Post event) {
        while (KeyMappings.FIREWORK.get().consumeClick()) {
            fireworkKeyMappingService.handleFireworkKeyPressedEvent(Minecraft.getInstance());
        }
    }

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(KeyMappings.FIREWORK.get());
    }
}
