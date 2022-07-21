package com.neomechanical.neoperformance.commands;

import com.neomechanical.neoperformance.NeoPerformance;
import com.neomechanical.neoperformance.performanceOptimiser.utils.Tps;
import com.neomechanical.neoperformance.utils.messages.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CommandManager implements CommandExecutor, TabCompleter, Tps {
    private final ArrayList<SubCommand> subcommands = new ArrayList<>();
    private final NeoPerformance plugin = NeoPerformance.getInstance();
    private final String parentCommand = "neoperformance";
    public CommandManager(){
        subcommands.add(new HaltCommand());
        subcommands.add(new HelpCommand(this));
        subcommands.add(new ReloadCommand());
        subcommands.add(new BypassCommand());
        subcommands.add(new ChunksCommand());
        subcommands.add(new SmartClearCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
            if (args.length > 0) {
                    for (int i = 0; i < getSubcommands().size(); i++) {
                        if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
                            if (getSubcommands().get(i).playerOnly() && !(sender instanceof Player)) {
                                MessageUtil.sendMM(sender, plugin.getLanguageManager().getString("commandGeneric.errorNotPlayer", null));
                                return true;
                            }
                            if (sender.hasPermission(getSubcommands().get(i).getPermission())) {
                                getSubcommands().get(i).perform(sender, args);
                            } else {
                                MessageUtil.sendMM(sender, plugin.getLanguageManager().getString("commandGeneric.errorNoPermission", null));
                            }
                            return true;
                        }
                    }
                    //If the command is not found, send a message to the player
                MessageUtil.sendMM(sender, plugin.getLanguageManager().getString("commandGeneric.errorCommandNotFound", null));
                    return true;
                }
                else {
                if (sender.hasPermission(parentCommand + ".admin")) {
                    MessageUtil messageUtil = new MessageUtil();
                    messageUtil.neoComponentMessage()
                            .addComponent(plugin.getLanguageManager().getString("main.isServerHalted", null))
                            .addComponent(plugin.getLanguageManager().getString("main.serverTps", null))
                            .addComponent(plugin.getLanguageManager().getString("main.serverHaltsAt", null))
                            .addComponent(plugin.getLanguageManager().getString("main.playerCount", null))
                            .sendNeoComponentMessage(sender);
                    return true;
                }
                }


        return true;
    }
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> list = new ArrayList<>();
            for (int i = 0; i < getSubcommands().size(); i++) {
                SubCommand subCommand = getSubcommands().get(i);
                if (!sender.hasPermission(subCommand.getPermission())) {
                    return null;
                }
                if (args.length == 1) {
                    list.add(subCommand.getName());
                } else if (args.length >= 2) {
                    if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
                        List<String> suggestions = getSubcommands().get(i).tabSuggestions();
                        Map<String, List<String>> mapSuggestions = getSubcommands().get(i).mapSuggestions();
                        List<String> listArgs = List.of(args);
                        String currentArg = listArgs.get(listArgs.size() - 2);
                        if (mapSuggestions != null && mapSuggestions.containsKey(currentArg)) {
                            list.addAll(mapSuggestions.get(currentArg));
                        } else if (suggestions != null) {
                            list.addAll(suggestions);
                        } else {
                            return null;
                        }
                    }
                }
            }
        return list;
    }
    public void init(NeoPerformance plugin){
        Objects.requireNonNull(plugin.getCommand(parentCommand)).setExecutor(this);
    }
    public ArrayList<SubCommand> getSubcommands(){
        return subcommands;
    }

}
