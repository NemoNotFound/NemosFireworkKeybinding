package com.nemonotfound.nemos.firework.keybinding;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

import static com.nemonotfound.nemos.firework.keybinding.NemosFireworkKeybinding.MOD_ID;

public class NemosFireworkKeybindingClient implements ClientModInitializer {

    private static KeyBinding fireworkRocketKeyBinding;
    private static final int LAST_HOTBAR_SLOT_INDEX = 8;
    private static final int PLAYER_INVENTORY_SLOT_COUNT_WITHOUT_EQUIPMENT_AND_CRAFTING_SLOTS = 36;

    @Override
    public void onInitializeClient() {
        fireworkRocketKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key." + MOD_ID + ".firework",
                InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN,
                "category." + MOD_ID + ".nemos-firework-keybinding"));

        registerFireworkKeyPressedEvent();
    }

    private void registerFireworkKeyPressedEvent() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (fireworkRocketKeyBinding.wasPressed()) {
                handleFireworkKeyPressedEvent(client);
            }
        });
    }

    private void handleFireworkKeyPressedEvent(MinecraftClient client) {
        ClientPlayerEntity player = client.player;
        PlayerInventory inventory = Objects.requireNonNull(client.player).getInventory();

        for (int slot = 0; slot < inventory.size(); slot++) {
            final ItemStack itemStack = inventory.getStack(slot);

            if (itemStack.isOf(Items.FIREWORK_ROCKET)) {
                useFireworkRocket(slot, player, client);

                break;
            }
        }
    }

    private void useFireworkRocket(int slot, ClientPlayerEntity player, MinecraftClient client) {
        ClientPlayerInteractionManager interactionManager = client.interactionManager;

        if (interactionManager == null) {
            return;
        }

        if (player.getMainHandStack().isOf(Items.FIREWORK_ROCKET)) {
            interactionManager.interactItem(player, Hand.MAIN_HAND);
        } else if (player.getOffHandStack().isOf(Items.FIREWORK_ROCKET)) {
            interactionManager.interactItem(player, Hand.OFF_HAND);
        } else {
            swapAndUseFireworkRocket(slot, player, interactionManager);
        }
    }

    private void swapAndUseFireworkRocket(int slot, ClientPlayerEntity player, ClientPlayerInteractionManager interactionManager) {
        slot = getSlotIndex(slot);
        int selectedSlot = getSlotIndex(player.getInventory().getSelectedSlot());

        swapFireworkRocket(interactionManager, slot, selectedSlot, player);
        interactionManager.interactItem(player, Hand.MAIN_HAND);
        swapFireworkRocket(interactionManager, slot, selectedSlot, player);
    }

    private int getSlotIndex(int slot) {
        if (slot <= LAST_HOTBAR_SLOT_INDEX) {
            slot += PLAYER_INVENTORY_SLOT_COUNT_WITHOUT_EQUIPMENT_AND_CRAFTING_SLOTS;
        }

        return slot;
    }

    private void swapFireworkRocket(ClientPlayerInteractionManager interactionManager, int slot, int selectedSlot, ClientPlayerEntity player) {
        if (selectedSlot == PLAYER_INVENTORY_SLOT_COUNT_WITHOUT_EQUIPMENT_AND_CRAFTING_SLOTS) {
            //No idea why, but when the selected slot is the first slot, the swapping won't work the other way around
            interactionManager.clickSlot(0, selectedSlot, 0, SlotActionType.SWAP, player);
            interactionManager.clickSlot(0, slot, 0, SlotActionType.SWAP, player);
            interactionManager.clickSlot(0, selectedSlot, 0, SlotActionType.SWAP, player);
        } else {
            interactionManager.clickSlot(0, slot, 0, SlotActionType.SWAP, player);
            interactionManager.clickSlot(0, selectedSlot, 0, SlotActionType.SWAP, player);
            interactionManager.clickSlot(0, slot, 0, SlotActionType.SWAP, player);
        }
    }
}