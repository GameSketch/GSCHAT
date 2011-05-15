package net.gamesketch.bukkit.gsgeneral.MISC;


import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandGs {

	public static boolean performGs(CommandSender sender, String[] args) {
    	Player player = (Player)sender;
    	if (args.length < 1) { player.sendMessage(ChatColor.RED + "You can't support nothing!"); return false; }
    	else {
    		StringBuilder msg = new StringBuilder();
    		for (String arg : args) {
    			msg.append(arg);
    			msg.append(" ");
    		}
    		
    		Player[] online = player.getServer().getOnlinePlayers();
    		for (Player p : online) {
    			if (p.isOp()) {
    				p.sendMessage("[" + ChatColor.DARK_GREEN + "SUPPORT: " + player.getName() + ChatColor.WHITE + "] " + ChatColor.GOLD + msg);
    			}
    		}
    	}
    	return true;
	}
	
}
