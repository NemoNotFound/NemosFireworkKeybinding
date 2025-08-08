package com.devnemo.nemos.firework.keybinding;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

import static com.devnemo.nemos.firework.keybinding.Constants.MOD_ID;

@Mod(value = MOD_ID, dist = Dist.CLIENT)
public class NeoForgeNemosFireworkKeybinding {

    public NeoForgeNemosFireworkKeybinding() {
        Common.init();
    }
}