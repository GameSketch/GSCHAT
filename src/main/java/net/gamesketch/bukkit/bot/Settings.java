package net.gamesketch.bukkit.bot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Settings {

	public static void Load() {
		File file = new File("plugins/GSGeneral/GSGeneral.settings");
		if (!file.exists()) {
			System.out.println("[GSGeneral] Couldn't find settings file, creating defauls...");
			BufferedWriter out;
			new File("plugins/GSGeneral/").mkdirs();
			try {
				out = new BufferedWriter(new FileWriter(file));
				
				write(out, "#Settings file for GSGeneral");
				write(out, "#");
				write(out, "#Enable the /who command");
				write(out, "enable-who=true");
				write(out, "#");
				write(out, "#Enable the /spawn command");
				write(out, "enable-spawn=true");
				write(out, "#");
				write(out, "#Enable the /setspawn command");
				write(out, "enable-setspawn=true");
				write(out, "#");
				write(out, "#Enable the //dist command");
				write(out, "enable-dist=true");
				write(out, "#");
				write(out, "#Enable the /blockhead command");
				write(out, "enable-blockhead=true");
				write(out, "#");
				write(out, "#Enable the /freezetime command");
				write(out, "enable-freezetime=true");
				write(out, "#");
				write(out, "#Enable the underwater helmet");
				write(out, "enable-waterhelmet=true");
				write(out, "#");
				write(out, "#Disallow non-ops to pickup infinite blocks");
				write(out, "enable-infpickup=true");
				write(out, "#");
				write(out, "#Enable the /gs support");
				write(out, "enable-support=true");
				write(out, "#");
				write(out, "#Enable the /rules");
				write(out, "enable-rules=true");
				write(out, "#");
				write(out, "#Enable the /a admin chat");
				write(out, "enable-adminchat=true");
				write(out, "#");
				write(out, "#Minutes between each anouncement (-1 = never)");
				write(out, "anounce-timer=15");
			} catch (IOException e) { System.out.println("[GSGeneral] Error: Couldn't make a new settings file."); System.out.println(e); return; }
			try { out.close(); } catch (IOException e) { System.out.println("[GSGeneral] Error: Couldn't save the settings file."); System.out.println(e); return; }
			System.out.println("[GSGeneral] Settings file made, You'd better edit it.");
		}
		
	try {
		BufferedReader in = new BufferedReader(new FileReader(file));
		String str;
		while ((str = in.readLine()) != null) {
			if (!str.startsWith("#")) {
				str = str.toLowerCase();
				if (str.startsWith("enable-who")) { GSGeneral.enableWho = !str.endsWith("false"); }
				if (str.startsWith("enable-spawn")) { GSGeneral.enableSpawn = !str.endsWith("false"); }
				if (str.startsWith("enable-setspawn")) { GSGeneral.enableSetspawn = !str.endsWith("false"); }
				if (str.startsWith("enable-dist")) { GSGeneral.enabledist = !str.endsWith("false"); }
				if (str.startsWith("enable-blockhead")) { GSGeneral.enableblockhead = !str.endsWith("false"); }
				if (str.startsWith("enable-freezetime")) { GSGeneral.enablefreezetime = !str.endsWith("false"); }
				if (str.startsWith("enable-waterhelmet")) { GSGeneral.enablewaterhelmet = !str.endsWith("false"); }
				if (str.startsWith("enable-infpickup")) { GSGeneral.enableinfpickup = !str.endsWith("false"); }
			    if (str.startsWith("enable-support")) { GSGeneral.enableReport = !str.endsWith("false"); }
			    if (str.startsWith("enable-rules")) { GSGeneral.enableRules = !str.endsWith("false"); }
			    if (str.startsWith("enable-adminchat")) { GSGeneral.enableAdminchat = !str.endsWith("false"); }
			    if (str.startsWith("anounce-timer")) { GSGeneral.anouncetimer = (Integer.parseInt(str.replace((CharSequence)"anounce-timer=",(CharSequence)""))) * 60000; 
			    if (GSGeneral.anouncetimer < 60) { GSGeneral.enableanouncer = false; } }
			}
			
		}
	} catch (IOException e) { System.out.println("[GSGeneral] Error: Couldn't read the settings file."); return; }
	System.out.println("[GSGeneral] Data successfully loaded.");
	}
	
	private static void write(BufferedWriter out, String str) throws IOException {
		out.write(str);
		out.newLine();
		out.flush();
	}
	
}


