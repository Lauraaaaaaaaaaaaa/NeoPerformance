package com.neomechanical.neoperformance.performanceOptimiser.managers;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TweakDataManager {
    private boolean manualHalt = false;
    private final List<Player> bypassedPlayers = new ArrayList<>();
    private TweakData tweakData;

    public TweakDataManager() {
        this.tweakData = new performanceTweaksConfiguration().loadTweakSettings();
    }

    public TweakData getTweakData() {
        return this.tweakData;
    }

    public void setTweakData(TweakData tweakData) {
        this.tweakData = tweakData;
    }

    public boolean isManualHalt() {
        return manualHalt;
    }

    public void toggleManualHalt() {
        //toggle boolean manualHalt
        manualHalt = !manualHalt;
    }

    public boolean toggleBypass(Player player) {
        //toggle boolean manualHalt
        if (bypassedPlayers.contains(player)) {
            bypassedPlayers.remove(player);
            return false;
        } else {
            bypassedPlayers.add(player);
            return true;
        }
    }

    public boolean isBypassed(Player player) {
        return bypassedPlayers.contains(player);
    }
}
