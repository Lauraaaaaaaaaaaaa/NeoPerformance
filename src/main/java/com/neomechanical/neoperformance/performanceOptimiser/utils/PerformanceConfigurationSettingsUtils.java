package com.neomechanical.neoperformance.performanceOptimiser.utils;

import com.neomechanical.neoperformance.performanceOptimiser.managers.data.HaltData;
import com.neomechanical.neoperformance.performanceOptimiser.managers.data.TweakData;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.List;

public class PerformanceConfigurationSettingsUtils {
    private PerformanceConfigurationSettingsUtils() {
    }

    public static boolean canMobSpawn(TweakData tweakData, EntitySpawnEvent e) {
        List<Entity> list = e.getEntity().getNearbyEntities(tweakData.getMobCapRadius(), 2, tweakData.getMobCapRadius());
        int mobCap = tweakData.getMobCap();
        if (mobCap == -1) {
            return true;
        }
        return list.size() <= tweakData.getMobCap();
    }

    public static boolean canMove(HaltData haltData, double instantaneousSpeed) {
        int maxSpeed = haltData.getMaxSpeed();
        if (maxSpeed == -1) {
            return true;
        }
        return instantaneousSpeed < maxSpeed;
    }

    public static boolean canExplode(TweakData tweakData, EntityExplodeEvent entityExplodeEvent) {
        //Get the list of explosives nearby the explosion
        int tntHalt = tweakData.getExplosionCap();
        if (tntHalt == -1) {
            return true;
        }
        List<Entity> list = entityExplodeEvent.getEntity().getNearbyEntities(10, 10, 10);
        boolean canExplode = list.size() < tntHalt;
        if (!canExplode) {
            //remove all in list so that the explosion doesn't happen and lag is prevented
            list.removeIf(entity -> entity.getType() != entityExplodeEvent.getEntity().getType());
        }
        //If the list is greater than the tntHalt, cancel the explosion
        return canExplode;
    }
}
