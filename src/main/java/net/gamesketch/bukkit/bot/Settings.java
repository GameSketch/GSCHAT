package net.gamesketch.bukkit.bot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Settings {

	public static void Load() {
		File file = new File("plugins/GSGeneral/GS-CHAT.settings");
		if (!file.exists()) {
			System.out.println("[GSGeneral] Couldn't find settings file, creating defauls...");
			BufferedWriter out;
			new File("plugins/GSGeneral/").mkdirs();
			try {
				out = new BufferedWriter(new FileWriter(file));
				
				out.write("#Settings file for GS-CHAT");
				out.write("#");
				out.write("#Enable the /who command");
				out.write("enable-who=true");
				out.write("#");
				out.write("#Enable the /spawn command");
				out.write("enable-spawn=true");
				out.write("#");
				out.write("#Enable the /setspawn command");
				out.write("enable-setspawn=true");
				out.write("#");
				out.write("#Enable the //dist command");
				out.write("enable-dist=true");
				out.write("#");
				out.write("#Enable the /blockhead command");
				out.write("enable-blockhead=true");
				out.write("#");
				out.write("#Enable the /freezetime command");
				out.write("enable-freezetime=true");
				out.write("#");
				out.write("#Enable the underwater helmet");
				out.write("enable-waterhelmet=true");
				out.write("#");
				out.write("#Disallow non-ops to pickup infinite blocks");
				out.write("enable-infpickup=true");
			} catch (IOException e) { System.out.println("[GSGeneral] Error: Couldn't make a new settings file."); System.out.println(e); return; }
			try { out.close(); } catch (IOException e) { System.out.println("[GSGeneral] Error: Couldn't save the settings file."); System.out.println(e); return; }
			System.out.println("[GSGeneral] Settings file made, You'd better edit it.");
		}
		
	try {
		BufferedReader in = new BufferedReader(new FileReader(file));
		String str;
		while ((str = in.readLine()) != null) {
			if (!str.startsWith("#")) {
				if (str.startsWith("enable-who")) { GSGeneral.enableWho = str.endsWith("true"); }
				if (str.startsWith("enable-spawn")) { GSGeneral.enableSpawn = str.endsWith("true"); }
				if (str.startsWith("enable-setspawn")) { GSGeneral.enableSetspawn = str.endsWith("true"); }
				if (str.startsWith("enable-dist")) { GSGeneral.enabledist = str.endsWith("true"); }
				if (str.startsWith("enable-blockhead")) { GSGeneral.enableblockhead = str.endsWith("true"); }
				if (str.startsWith("enable-freezetime")) { GSGeneral.enablefreezetime = str.endsWith("true"); }
				if (str.startsWith("enable-waterhelmet")) { GSGeneral.enablewaterhelmet = str.endsWith("true"); }
				if (str.startsWith("enable-infpickup")) { GSGeneral.enableinfpickup = str.endsWith("true"); }
			}
			
		}
	} catch (IOException e) { System.out.println("[GSGeneral] Error: Couldn't read the settings file."); return; }
	System.out.println("[GSGeneral] Data successfully loaded.");
	}
	
}
