package net.gamesketch.bukkit.gsgeneral.MOTD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.gamesketch.bukkit.gsgeneral.Core;

public class Motd {
	final static File file = new File("plugins/GSGeneral/motd.txt");
	final static File folder = new File("plugins/GSGeneral/");
	
	private static List<String> messages;
	
	public static void init() {
		messages = new LinkedList<String>();
		
		if (!file.exists()) {
			try { 
				folder.mkdirs();
				file.createNewFile();
			} catch (IOException e) { }
			messages.add("No motd.txt found, create one!");
			return;
		}
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String s;
			while ((s = in.readLine()) != null) {
				if (s.startsWith("#")) continue;
				messages.add(s);
			}
			
			if (messages.size() < 1) { messages.add("No lines in motd.txt"); }
		} catch (IOException e) {
			messages = new LinkedList<String>();
			messages.add("Error reading from motd.txt");
			return;
		}
	}
	
	public static void sendMotd(Player p) {
		for (String msg : messages) {
			p.sendMessage("[" + ChatColor.GOLD + "GS" + ChatColor.WHITE + "] " + Core.replaceColours(msg));
		}
	}
}
