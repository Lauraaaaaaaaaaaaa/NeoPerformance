package com.neomechanical.neoperformance.commands;

import com.neomechanical.neoconfig.neoutils.commands.Command;
import com.neomechanical.neoperformance.NeoPerformance;
import com.neomechanical.neoperformance.performance.smart.smartReport.SmartReport;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SmartReportCommand extends Command {
    private final NeoPerformance plugin;

    public SmartReportCommand(NeoPerformance plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "report";
    }

    @Override
    public String getDescription() {
        return "Generate a report of your severs overall performance";
    }

    @Override
    public String getSyntax() {
        return "/np report";
    }

    @Override
    public String getPermission() {
        return "neoperformance.report";
    }

    @Override
    public boolean playerOnly() {
        return false;
    }

    @Override
    public void perform(CommandSender commandSender, String[] args) {
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("subjects")) {
                new SmartReport(plugin).getPerformanceReportSubjects().sendReport(commandSender);
                return;
            }
        }
        new SmartReport(plugin).getPerformanceReportOverview().sendReport(commandSender);
    }

    @Override
    public List<String> tabSuggestions() {
        return Collections.singletonList("subjects");
    }

    @Override
    public Map<String, List<String>> mapSuggestions() {
        return null;
    }
}
