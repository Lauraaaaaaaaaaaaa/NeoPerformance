package com.neomechanical.neoperformance.commands;

import com.neomechanical.neoperformance.NeoPerformance;
import com.neomechanical.neoperformance.performanceOptimiser.utils.Tps;
import com.neomechanical.neoperformance.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter, Tps {
    private final ArrayList<SubCommand> subcommands = new ArrayList<>();

    private final String parentCommand = "neoperformance";
    public CommandManager(){
        Plugin plugin = NeoPerformance.getInstance();
        subcommands.add(new HaltCommand(plugin));
        subcommands.add(new HelpCommand(this));
        subcommands.add(new ReloadCommand());
        subcommands.add(new BypassCommand());
    }

    public void addCommand(SubCommand subCommand) {
        subcommands.add(subCommand);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player p){
            if (args.length > 0) {
                    for (int i = 0; i < getSubcommands().size(); i++) {
                        if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
                            if (sender.hasPermission(getSubcommands().get(i).getPermission())) {
                                getSubcommands().get(i).perform((Player) sender, args);
                            } else {
                                sender.sendMessage(MessageUtil.color("&c&lYou do not have permission to use this command"));
                            }
                            return true;
                        }
                    }
                    //If the command is not found, send a message to the player
                    sender.sendMessage(MessageUtil.color("&c&lCommand not found, try /np help"));
                    return true;
                }
                else {
                    if (p.hasPermission(parentCommand+ ".admin")) {
                        MessageUtil messageUtil = new MessageUtil();
                        messageUtil.neoMessage().addMessage("  &7Is server halted: " + fancyIsServerHalted())
                        .addMessage("  &7Server tps: " + getFancyTps())
                        .addMessage("  &7Server halts at: " + "&a&l"+getHaltTps())
                        .addMessage("  &7Player count: " + "&a&l"+Bukkit.getOnlinePlayers().size())
                        .sendMessage(p);
                        return true;
                    }
                }
        }


        return true;
    }
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> list = new ArrayList<>();
        if (sender instanceof Player){
            for (int i = 0; i < getSubcommands().size(); i++){
                if (args.length > 0){
                    list = getSubcommands().get(i).tabSuggestions();
                }
            }
        }
        return list;
    }
    public void init(NeoPerformance plugin){
        plugin.getCommand(parentCommand).setExecutor(this);
    }
    public ArrayList<SubCommand> getSubcommands(){
        return subcommands;
    }

}
