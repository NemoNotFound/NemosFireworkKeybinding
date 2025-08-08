package com.devnemo.nemos.firework.keybinding.service;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Objects;

import static com.devnemo.nemos.firework.keybinding.Constants.LAST_HOTBAR_SLOT_INDEX;
import static com.devnemo.nemos.firework.keybinding.Constants.PLAYER_INVENTORY_SLOT_COUNT_WITHOUT_EQUIPMENT_AND_CRAFTING_SLOTS;

public class FireworkKeyMappingService {

    private static FireworkKeyMappingService INSTANCE;

    public static FireworkKeyMappingService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FireworkKeyMappingService();
        }

        return INSTANCE;
    }

    public void handleFireworkKeyPressedEvent(Minecraft minecraft) {
        var player = minecraft.player;
        var inventory = Objects.requireNonNull(minecraft.player).getInventory();

        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            final ItemStack itemStack = inventory.getItem(slot);

            if (itemStack.is(Items.FIREWORK_ROCKET)) {
                useFireworkRocket(slot, player, minecraft);

                break;
            }
        }
    }

    private void useFireworkRocket(int slot, LocalPlayer player, Minecraft minecraft) {
        var gameMode = minecraft.gameMode;

        if (gameMode == null) {
            return;
        }

        if (player.getMainHandItem().is(Items.FIREWORK_ROCKET)) {
            gameMode.useItem(player, InteractionHand.MAIN_HAND);
        } else if (player.getOffhandItem().is(Items.FIREWORK_ROCKET)) {
            gameMode.useItem(player, InteractionHand.OFF_HAND);
        } else {
            swapAndUseFireworkRocket(slot, player, gameMode);
        }
    }

    private void swapAndUseFireworkRocket(int slot, LocalPlayer player, MultiPlayerGameMode gameMode) {
        slot = getSlotIndex(slot);
        int selectedSlot = getSlotIndex(player.getInventory().getSelectedSlot());

        swapFireworkRocket(gameMode, slot, selectedSlot, player);
        gameMode.useItem(player, InteractionHand.MAIN_HAND);
        swapFireworkRocket(gameMode, slot, selectedSlot, player);
    }

    private int getSlotIndex(int slot) {
        if (slot <= LAST_HOTBAR_SLOT_INDEX) {
            slot += PLAYER_INVENTORY_SLOT_COUNT_WITHOUT_EQUIPMENT_AND_CRAFTING_SLOTS;
        }

        return slot;
    }

    private void swapFireworkRocket(MultiPlayerGameMode gameMode, int slot, int selectedSlot, LocalPlayer player) {
        if (selectedSlot == PLAYER_INVENTORY_SLOT_COUNT_WITHOUT_EQUIPMENT_AND_CRAFTING_SLOTS) {
            //No idea why, but when the selected slot is the first slot, the swapping won't work the other way around
            gameMode.handleInventoryMouseClick(0, selectedSlot, 0, ClickType.SWAP, player);
            gameMode.handleInventoryMouseClick(0, slot, 0, ClickType.SWAP, player);
            gameMode.handleInventoryMouseClick(0, selectedSlot, 0, ClickType.SWAP, player);
        } else {
            gameMode.handleInventoryMouseClick(0, slot, 0, ClickType.SWAP, player);
            gameMode.handleInventoryMouseClick(0, selectedSlot, 0, ClickType.SWAP, player);
            gameMode.handleInventoryMouseClick(0, slot, 0, ClickType.SWAP, player);
        }
    }
}
