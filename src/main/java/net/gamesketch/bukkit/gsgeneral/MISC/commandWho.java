package net.gamesketch.bukkit.gsgeneral.MISC;

import net.gamesketch.bukkit.gsgeneral.GUESTPREV.Guestprev;
import net.gamesketch.bukkit.gsgeneral.PREFIX.Prefixer;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandWho {

	public static void sendWho(CommandSender p) {
		String result = "";
		for (Player cur : p.getServer().getOnlinePlayers()) {
			String curstring = colorize(cur.getDisplayName(), ChatColor.GRAY);
			
			if (Prefixer.getPrefix(cur).getChatColor() != ChatColor.WHITE) { curstring = colorize(cur.getDisplayName(), Prefixer.getPrefix(cur).getChatColor()); }
			if (Guestprev.isGuest(cur)) { curstring = colorize(cur.getDisplayName(), ChatColor.GRAY); }
			if (cur.isOp()) { curstring = colorize(cur.getDisplayName(), ChatColor.RED); }
		
			result = result + curstring;
		}
		
		result = result.substring(0,result.length() - 2);
		p.sendMessage(ChatColor.GREEN + "Current players (" + p.getServer().getOnlinePlayers().length + "): " + result);
	}
	
	
	private static String colorize(String s, ChatColor c) {
		return c + s + ChatColor.WHITE + ", ";
	}
}
