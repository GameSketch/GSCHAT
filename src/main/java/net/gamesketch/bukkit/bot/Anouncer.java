package net.gamesketch.bukkit.bot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class Anouncer {

	static Timer timer;
	
	public static void Load(final Server server) {
		
		/*
		 * Prepare timer
		 */
		timer = new Timer();
		timer.schedule(new TimerTask() { public void run() {
				try { send(server.getOnlinePlayers()); } catch (IOException e) { }
				Anouncer.schedule(server);
	    } }, 1000);
	}
	
	private static void schedule(final Server server) {
		timer = new Timer();
		timer.schedule(new TimerTask() { public void run() {
				try { send(server.getOnlinePlayers()); } catch (IOException e) { }
				Anouncer.schedule(server);
	    } }, GSGeneral.anouncetimer);
	}
	
	private static void send(Player[] online) throws IOException {
		File file = new File("plugins/GSGeneral/anouncement.txt");
		File folder = new File("plugins/GSGeneral/");
		
		if (!file.exists()) {
			folder.mkdirs();
			file.createNewFile();
		}
		
		BufferedReader in = new BufferedReader(new FileReader(file));
		String s;
		String msg = "";
		while ((s = in.readLine()) != null) {
			if (!s.startsWith("#")) {
				msg = s.
		    	replaceAll("@red@", ChatColor.RED + "").
		    	replaceAll("@yellow@", ChatColor.YELLOW + "").
		    	replaceAll("@gold@", ChatColor.GOLD + "").
		    	replaceAll("@green@", ChatColor.GREEN + "").
		    	replaceAll("@darkgreen@", ChatColor.DARK_GREEN + "").
		    	replaceAll("@blue@", ChatColor.BLUE + "").
		    	replaceAll("@darkblue@", ChatColor.DARK_BLUE + "").
		    	replaceAll("@@", ChatColor.WHITE + "").
		    	replaceAll("@gray@", ChatColor.GRAY + "").
		    	replaceAll("@darkgray@", ChatColor.DARK_GRAY + "").
		    	replaceAll("@aqua@", ChatColor.AQUA + "").
		    	replaceAll("@darkaqua@", ChatColor.DARK_AQUA + "").
		    	replaceAll("@black@", ChatColor.BLACK + "").
		    	replaceAll("@darkpurple@", ChatColor.DARK_PURPLE + "").
		    	replaceAll("@darkred@", ChatColor.DARK_RED + "").
		    	replaceAll("@pink@", ChatColor.LIGHT_PURPLE + "");
				break;
			}
		}
		if (msg == null) { return; }
		for (Player p : online) {
			p.sendMessage("[" + ChatColor.YELLOW + "GS" + ChatColor.WHITE + "] " + msg);
		}
		return;
		
		
	}
}
