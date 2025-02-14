package com.neomechanical.neoperformance.performance.smart.smartReport;

import com.neomechanical.neoconfig.neoutils.languages.LanguageManager;
import com.neomechanical.neoperformance.NeoPerformance;
import com.neomechanical.neoperformance.performance.smart.smartReport.utils.CPU;
import com.neomechanical.neoperformance.performance.smart.smartReport.utils.Grading;
import com.neomechanical.neoperformance.performance.smart.smartReport.utils.Memory;
import com.neomechanical.neoperformance.performance.smart.smartReport.utils.spark.SparkUtils;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class SmartReportPlaceholders {
    private final LanguageManager languageManager;

    public SmartReportPlaceholders(LanguageManager languageManager) {
        this.languageManager = languageManager;
    }

    public void addPlaceholders(NeoPerformance plugin) {
        languageManager
                .addInternalPlaceholder("%OVERALLGRADE%", (Player player) -> Grading.getFancyGrade(Grading.getServerGrade(plugin)))
                //Add memory placeholders
                .addInternalPlaceholder("%USEDMEMORY%", (Player player) -> String.valueOf(Memory.usedMemory()))
                .addInternalPlaceholder("%FREEMEMORY%", (Player player) -> String.valueOf(Memory.freeMemory()))
                .addInternalPlaceholder("%MAXMEMORY%", (Player player) -> String.valueOf(Memory.maxMemory()))
                //Add cpu placeholders
                .addInternalPlaceholder("%AVAILABLEPROCESSORS%", (Player player) -> String.valueOf(CPU.availableProcessors()))
                .addInternalPlaceholder("%CPUPROCESSLOAD%", (Player player) -> new DecimalFormat("###.##").format(CPU.getProcessCpuLoad(plugin) * 100))
                .addInternalPlaceholder("%CPUSYSTEMLOAD%", (Player player) -> new DecimalFormat("###.##").format(CPU.getSystemCpuLoad(plugin) * 100));
        //Add spark placeholders
        if (SparkUtils.isInstalled(plugin)) {
            languageManager.addInternalPlaceholder("%MSPTMINUTEAVERAGE%", (Player player) -> new DecimalFormat("###.##").format(SparkUtils.MSPT()));
        }
    }
}