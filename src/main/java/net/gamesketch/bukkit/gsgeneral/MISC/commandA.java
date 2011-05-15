package net.gamesketch.bukkit.gsgeneral.MISC;

import java.util.List;

import net.gamesketch.bukkit.gsgeneral.PREFIX.*;
import net.gamesketch.bukkit.gsgeneral.ADMINCHAT.AdminChat;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandA {

	public static boolean performA(CommandSender sender, String[] args) {
    	Player player = (Player)sender;
    	List<Player> adminchat = AdminChat.getList();
    	
    	if (!player.isOp()) { player.sendMessage(ChatColor.RED + "You are no moderator."); return true; }
    	
    	if (args.length < 1) {
    		if (adminchat.contains(player)) { player.sendMessage(ChatColor.GREEN + "You are no longer talking in the Admin Chat"); adminchat.remove(player); }
    		else { player.sendMessage(ChatColor.GREEN + "You are now talking in the Admin Chat."); adminchat.add(player); }
    		return true;
    	}
    	String message = "";
    	for (String s : args) { message = message + s + " "; }
    	message = message.substring(0, message.length() - 1);
    	
    	if (adminchat.contains(player)) {
    		for (Player p : player.getServer().getOnlinePlayers()) {
    			p.sendMessage(Prefixer.getPrefix(player).getPrefix() + " <" + player.getDisplayName() + "> " + message);
    		}
    	} else {
    		for (Player p : player.getServer().getOnlinePlayers()) {
    			if (p.isOp()) {
    				p.sendMessage("[" + ChatColor.GREEN + "/a" + ChatColor.WHITE + "] " + player.getName() + ": " + message);
    			}
    		}
    	}
    	return true;
	}
	
}
