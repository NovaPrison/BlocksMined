package com.novaprison.blocksmined;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Hashtable;
import java.util.UUID;

public final class BlocksMined extends JavaPlugin implements BMAPI, Listener {

    private Hashtable<UUID, Integer> blocks = new Hashtable<>();

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
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You must be a player to send this or specify a player");
                return true;
            }

            Player p = args.length == 1 ? Bukkit.getPlayer(args[0]) : (Player) sender;
            sender.sendMessage(ChatColor.YELLOW + p.getName() + " has mined " + ChatColor.BOLD + ChatColor.AQUA + getMined(p) + ChatColor.YELLOW + " blocks.");

            return true;
        } else if (label.equalsIgnoreCase("bmt")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                sender.sendMessage(ChatColor.YELLOW + p.getName() + ": " + ChatColor.BOLD + ChatColor.AQUA + getMined(p));
            }

            return true;
        }

        return false;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        mine(event.getPlayer(), 1);
    }

    @Override
    public int getMined(Player p) {
        return getMined(p.getUniqueId());
    }

    @Override
    public int getMined(UUID uuid) {
        return blocks.containsKey(uuid) ? blocks.get(uuid) : 0;
    }

    @Override
    public void setMined(Player p, int amount) {
        setMined(p.getUniqueId(), amount);
    }

    @Override
    public void setMined(UUID uuid, int amount) {
        blocks.put(uuid, amount);
    }

    @Override
    public void mine(Player p, int count) {
        mine(p.getUniqueId(), count);
    }

    @Override
    public void mine(UUID uuid, int count) {
        setMined(uuid, getMined(uuid)+count);
    }
}
