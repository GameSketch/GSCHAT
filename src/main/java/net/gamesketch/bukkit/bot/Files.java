package net.gamesketch.bukkit.bot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import org.bukkit.entity.Player;

public class Files {
	
	public static Time getActivity(Player player) {
		String search = player.getName();
		File file = new File("GSCHAT/activity.txt");
		if (!file.exists()) { return null; }
		BufferedReader in;
		try {
		in = new BufferedReader(new FileReader(file));
		} catch (IOException e) { return null; }
		String str;
		try {
		while (!(str = in.readLine()).startsWith(search + "=")) {
			if (str.isEmpty()) break;
		}  } catch (IOException e) { return null; }
		if (str.startsWith(search + "=")) {
			String time = str.replaceFirst(search + "=", "");
			long Date = (long)Integer.parseInt(time);
			try { in.close(); } catch (IOException e) { return null; }
			return new Time(Date);
			
		}
		try { in.close(); } catch (IOException e) { return null; }
		return null;
	
	}
	
	public static boolean addActivity(Player player) throws IOException {
		File file = new File("GSCHAT/activity.txt");
		long date = ((Date)new Time(Calendar.getInstance().getTimeInMillis())).getTime();
		if (!file.exists()) { 
			if (!createFile(file)) { return false; }
		}
		Time theData = getActivity(player);
		if (theData != null) { removeActivity(player); }
		writeActivity(player, date);
			
		return true;
		
	}
	
	private static void writeActivity(Player player, long date) throws IOException {
		File file = new File("GSCHAT/activity.txt");
		String search = player.getName();
		if (!file.exists()) {
			if (!createFile(file)) { return; }
		}
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		out.newLine();
		out.write(search + "=" + date);
		out.close();
		return;
		
	}
	
	private static void removeActivity(Player player) throws IOException {
		File file = new File("GSCHAT/activity.txt");
		File temp = new File("GSCHAT/activity.temp");
		String search = player.getName();
		if (!file.exists()) { return; }
		if (temp.exists()) { temp.delete(); }
		temp.createNewFile();
		BufferedReader in = new BufferedReader(new FileReader(file));
		BufferedWriter out = new BufferedWriter(new FileWriter(temp));
		String str;
		while ((str = in.readLine()) != null) {
			if (!str.contains(search + "=")) {
				out.write(str);
			}
		}
		in.close();
		out.close();
		file.delete();
		temp.renameTo(file);
	}

	public static boolean createFile(File file) {
		if (file.exists()) { return false; }
		try {
			file.createNewFile();
		} catch (IOException e) { return false; }
		
		return true;
	}
	
}
