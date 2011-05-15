package net.gamesketch.bukkit.gsgeneral.ANOUNCER;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import net.gamesketch.bukkit.gsgeneral.Core;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class Anouncer {
	public static Timer timer;
	final static File file = new File("plugins/GSGeneral/anouncement.txt");
	final static File folder = new File("plugins/GSGeneral/");
	
	public static void init(final Server server) {
		TimerTask task = new TimerTask() {
			public void run() {
				send(server.getOnlinePlayers());
			}
		};
		timer = new Timer();
		timer.schedule(task, 2500, 900000);
	}
	
	public static void send(Player[] online) {
		if (!file.exists()) {
			try {
				folder.mkdirs();
				file.createNewFile();
			} catch (IOException e) { }
		}
		String msg = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String s;
			while ((s = in.readLine()) != null) {
				if (!s.startsWith("#")) {
					msg = Core.replaceColours(s);
					break;
				}
			}
		} catch (IOException e) { }
		if (msg == null) { return; }
		for (Player p : online) {
			p.sendMessage("[" + ChatColor.GOLD + "GS" + ChatColor.WHITE + "] " + msg);
		}
		return;
	}
	
}
