package io.github.nick_provost.blocksmined;

import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Hashtable;

public final class BlocksMined extends JavaPlugin implements Listener{
	
	private Hashtable<String, Integer> blocks = new Hashtable<String, Integer>();
	private Set<String> keys = blocks.keySet();
	
	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
	}

	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("bm")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
			sender.sendMessage(ChatColor.YELLOW + args[0] + " has mined " + ChatColor.BOLD + ChatColor.AQUA + blocks.get(args[0]) + ChatColor.YELLOW + " blocks.");
			return true;
		} //If this has happened the function will return true. 
	        // If this hasn't happened the value of false will be returned.
		
		else if(cmd.getName().equalsIgnoreCase("bmt")) {
			for(String k : keys) {
			sender.sendMessage(blocks.get(k)+"\n");
			}
			return true;
		}
		return false;
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		Player play = event.getPlayer();
		if(!play.hasPlayedBefore()) {
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
		blocks.put(name,bm);
	}
	
}
