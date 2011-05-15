package net.gamesketch.bukkit.gsgeneral.MISC;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandWho {

	public static void sendWho(CommandSender p) {
		String result = "";
		for (Player cur : p.getServer().getOnlinePlayers()) {
			if (cur.isOp()) { result = result + ChatColor.RED + cur.getDisplayName() + ChatColor.WHITE + ", "; }
			else { result = result + ChatColor.GRAY + cur.getDisplayName() + ChatColor.WHITE + ", "; }
		}
		
		p.sendMessage(ChatColor.GREEN + "Current players (" + p.getServer().getOnlinePlayers().length + "): " + result);
	}
	
}
