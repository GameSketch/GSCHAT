package net.gamesketch.bukkit.gsgeneral.RULES;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import net.gamesketch.bukkit.gsgeneral.Core;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Rules {
	final static File file = new File("plugins/GSGeneral/rules.txt");
	final static File folder = new File("plugins/GSGeneral/");
	
	public static void init() {
		if (!file.exists()) {
			try {
				folder.mkdirs();
				file.createNewFile();
			} catch (IOException e) { return; }
		}
	}
	
	public static boolean send(CommandSender p, String[] args) {
		if (!file.exists()) {
			try {
				folder.mkdirs();
				file.createNewFile();
			} catch (IOException e) { return false; }
			p.sendMessage(getPrefix() + " No rules file found on the server. ABORT MISSION");
			return true;
		}
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String s;
			List<String> result = new LinkedList<String>();
			
			//get search string
			String search = "[main]";
			if (args.length >= 1) { search = "[" + args[0].toLowerCase() + "]"; }
			
			//read from file
			while ((s = in.readLine()) != null) {
				if (s.startsWith("#")) continue;
				if (!s.startsWith(search)) continue; 
				s = s.replace((CharSequence)search, "");
				result.add(Core.replaceColours(s));
			}
			
			if (result.isEmpty()) { p.sendMessage(getPrefix() + " No rules found for that category"); return true; }
			
			for (String msg : result) {
				p.sendMessage(getPrefix() + " " + msg);
			}
			
		} catch (IOException e) { p.sendMessage(getPrefix() + " Error in rules file."); return false; }
		return true;
	}
		
	private static String getPrefix() {
		return "[" + ChatColor.GREEN + "GS" + ChatColor.WHITE + "]";
	}
	
}
