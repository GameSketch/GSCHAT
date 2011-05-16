package net.gamesketch.bukkit.gsgeneral.ADMINCHAT;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

public class AdminChat {
	private static List<Player> players;
	
	public static void init() {
		players = new LinkedList<Player>();
	}
	
	public static void chatEvent(PlayerChatEvent event) {
		if (players.contains(event.getPlayer())) {
			if (!event.getPlayer().isOp()) { players.remove(event.getPlayer()); return; }
			event.setCancelled(true);
			for (Player p : event.getPlayer().getServer().getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(parseFormat(event.getPlayer().getDisplayName(), event.getMessage()));
				}
			}
		}
	}
	
	public static List<Player> getList() {
		return players;
	}
	
	public static String parseFormat(String name, String message) {
		return "[" + ChatColor.DARK_RED + "/a" + ChatColor.WHITE + "] " + ChatColor.RED + name + ChatColor.WHITE + ": " + ChatColor.GOLD + message;
	}
	
}
