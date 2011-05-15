package net.gamesketch.bukkit.gsgeneral.MISC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class BanTxtFileFix {

	public static void loginEvent(PlayerLoginEvent event) {
        try {
        	BufferedReader in = new BufferedReader(new FileReader(new File("banned-players.txt")));
        	String s;
        	while ((s = in.readLine()) != null) {
        		if (s.split(" ")[0].equalsIgnoreCase(event.getPlayer().getName())) {
        			String banreason = "";
        			if (s.split(" ").length > 1) {
	        			for (String cur : s.split(" ")) {
	        				banreason = banreason + cur + " ";
	        			}
	        			banreason = banreason.substring(0,banreason.length() - 1).replaceFirst("[a-zA-Z0-9_]+ ", "");
        			} else { banreason = "Banned without reason"; }
        			event.disallow(Result.KICK_BANNED, "Banned for: \"" + banreason + "\"");        			
        			for (Player p : event.getPlayer().getServer().getOnlinePlayers()) {
        				if (p.isOp()) { p.sendMessage(ChatColor.GRAY + "(Console: " + event.getPlayer().getName() + "tried to join but is banned."); }
        			}
        			return;
        		}
        	}
        } catch (IOException e) { }
	}
	
}
