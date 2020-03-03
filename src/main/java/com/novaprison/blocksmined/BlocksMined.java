package com.novaprison.blocksmined;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Hashtable;
import java.util.Set;

public final class BlocksMined extends JavaPlugin implements Listener {

    private Hashtable<String, Integer> blocks = new Hashtable<>();
    private Set<String> keys = blocks.keySet();

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
        	if (args.length != 1) {
        		sender.sendMessage(ChatColor.RED + "Expected 1 argument.");
			} else {
				sender.sendMessage(ChatColor.YELLOW + args[0] + " has mined " + ChatColor.BOLD + ChatColor.AQUA + blocks.get(args[0]) + ChatColor.YELLOW + " blocks.");
			}

            return true;
        } else if (label.equalsIgnoreCase("bmt")) {
            for (String k : keys) {
                sender.sendMessage(blocks.get(k) + "\n");
            }

            return true;
        }

        return false;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player play = event.getPlayer();

        if (!play.hasPlayedBefore()) {
            blocks.put(play.getName(), 0);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player play = event.getPlayer();
        String name = play.getName();
        int bm = blocks.get(name);
        blocks.remove(name);
        bm++;
        blocks.put(name, bm);
    }
}
