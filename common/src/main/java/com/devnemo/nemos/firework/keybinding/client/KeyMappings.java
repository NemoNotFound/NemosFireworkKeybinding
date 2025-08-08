package com.devnemo.nemos.firework.keybinding.client;

import com.devnemo.nemos.firework.keybinding.platform.Services;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

import java.util.function.Supplier;

import static com.devnemo.nemos.firework.keybinding.Constants.MOD_ID;

public class KeyMappings {

    private static final String category = String.format("%s.category.nemosFireworkKeybinding", MOD_ID);

    public static Supplier<KeyMapping> FIREWORK = registerKeyMapping(new KeyMapping(
            String.format("%s.key.firework", MOD_ID),
            GLFW.GLFW_KEY_UNKNOWN,
            category
    ));

    public static void init() {}

    private static Supplier<KeyMapping> registerKeyMapping(KeyMapping keyMapping) {
        return Services.REGISTRY_HELPER.registerKeyMapping(keyMapping);
    }
}
