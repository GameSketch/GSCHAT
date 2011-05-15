package net.gamesketch.bukkit.gsgeneral.MISC;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandGsa {

	public static boolean performGsa(CommandSender sender, String[] args) {
    	Player player = (Player)sender;
    	if (!player.isOp()) { return true; }
    	if (args.length < 2) { player.sendMessage(ChatColor.RED + "Invalid arguments"); return false; }
    	else {
    		Player target = player.getServer().getPlayer(args[0]);
    		if (target instanceof Player) {
    			StringBuilder msg = new StringBuilder();
    			int i = 0;
    			for (String arg : args) {
    				if (i >= 1) {
    					msg.append(arg).append(" ");
    				}
    				i += 1;
    			}
    			target.sendMessage("[" + ChatColor.DARK_GREEN + "SUPPORT: " + player.getName() + ChatColor.WHITE + "] " + ChatColor.GOLD + msg);
    			player.sendMessage("Support send to " + target.getName());
    			return true;
    		}
    		return false;
    	}
	}
	
}
