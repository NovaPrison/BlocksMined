package com.novaprison.blocksmined;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Hashtable;
import java.util.Map;

public final class BlocksMined extends JavaPlugin implements Listener {

    private Hashtable<String, Integer> blocks = new Hashtable<>();

    @Override
    public void onEnable() {
        getLogger().info("onEnable has been invoked!");
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("bm")) {
            String player = args.length == 1 ? args[0] : sender.getName();
            sender.sendMessage(ChatColor.YELLOW + player + " has mined " + ChatColor.BOLD + ChatColor.AQUA + (blocks.containsKey(player) ? blocks.get(player) : 0) + ChatColor.YELLOW + " blocks.");

            return true;
        } else if (label.equalsIgnoreCase("bmt")) {
            for (Map.Entry<String, Integer> k : blocks.entrySet()) {
                sender.sendMessage(ChatColor.YELLOW + k.getKey() + ": " + ChatColor.BOLD + ChatColor.AQUA + k.getValue());
            }

            return true;
        }

        return false;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        String name = event.getPlayer().getName();
        int previous = blocks.containsKey(name) ? blocks.get(name) : 0;

        blocks.put(name, previous + 1);
    }
}
