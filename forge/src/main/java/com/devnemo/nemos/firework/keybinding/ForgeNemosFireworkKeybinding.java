package com.devnemo.nemos.firework.keybinding;

import com.devnemo.nemos.firework.keybinding.client.KeyMappings;
import com.devnemo.nemos.firework.keybinding.service.FireworkKeyMappingService;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import static com.devnemo.nemos.firework.keybinding.Constants.MOD_ID;

@Mod(MOD_ID)
public class ForgeNemosFireworkKeybinding {

    public static final FireworkKeyMappingService fireworkKeyMappingService = FireworkKeyMappingService.getInstance();

    public ForgeNemosFireworkKeybinding(FMLJavaModLoadingContext context) {
        final var modBusGroup = context.getModBusGroup();

        if (FMLEnvironment.dist.isClient()) {
            Common.init();

            TickEvent.ClientTickEvent.Post.BUS.addListener(this::handleFireworkKeyPressedEvent);
            RegisterKeyMappingsEvent.getBus(modBusGroup).addListener(this::registerKeyMappings);
        }
    }

    private void handleFireworkKeyPressedEvent(TickEvent.ClientTickEvent.Post event) {
        while (KeyMappings.FIREWORK.get().consumeClick()) {
            fireworkKeyMappingService.handleFireworkKeyPressedEvent(Minecraft.getInstance());
        }
    }

    private void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(KeyMappings.FIREWORK.get());
    }
}