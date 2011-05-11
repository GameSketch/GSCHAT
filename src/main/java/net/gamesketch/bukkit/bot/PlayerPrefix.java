package net.gamesketch.bukkit.bot;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerPrefix {
	int color = 16;
	String prefix = "";
	
	String player;
	
	public PlayerPrefix(String player) {
		this.player = player;
	}
	public PlayerPrefix(String player, String prefix, int color) {
		this.player = player;
		this.prefix = prefix;
		this.color = color;
	}
	
	
	public String getPrefix() {
		if (prefix.isEmpty()) { return ""; }
		return "[" + ChatColor.getByCode(color) + prefix + ChatColor.WHITE + "]";
	}
	public String getRawPrefix() {
		return prefix;
	}
	public Player getPlayer() { return GSGeneral.server.getPlayer(player); }
	public String getPlayerName() { return player; }
	public String getPrefixText() { return prefix; }
	public String getColor() { return "" + color; }
}
