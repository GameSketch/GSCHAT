package net.gamesketch.bukkit.gsgeneral;

import net.gamesketch.bukkit.gsgeneral.ADMINCHAT.AdminChat;
import net.gamesketch.bukkit.gsgeneral.ANOUNCER.Anouncer;
import net.gamesketch.bukkit.gsgeneral.Listeners.Listeners;
import net.gamesketch.bukkit.gsgeneral.MISC.commandA;
import net.gamesketch.bukkit.gsgeneral.MISC.commandBlockhead;
import net.gamesketch.bukkit.gsgeneral.MISC.commandGs;
import net.gamesketch.bukkit.gsgeneral.MISC.commandGsa;
import net.gamesketch.bukkit.gsgeneral.MISC.commandPrefix;
import net.gamesketch.bukkit.gsgeneral.MISC.commandReloadgs;
import net.gamesketch.bukkit.gsgeneral.MISC.commandSetspawn;
import net.gamesketch.bukkit.gsgeneral.MISC.commandSpawn;
import net.gamesketch.bukkit.gsgeneral.MISC.commandWho;
import net.gamesketch.bukkit.gsgeneral.MOTD.Motd;
import net.gamesketch.bukkit.gsgeneral.PREFIX.Prefixer;
import net.gamesketch.bukkit.gsgeneral.RULES.Rules;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
	public static Server server;
	
	public void onDisable() {
		Anouncer.timer.cancel();
		Prefixer.Save();
		System.out.println("Disabled GSGeneral");
	}

	public void onEnable() {
		server = getServer();
		
		Motd.init();
		Rules.init();
		AdminChat.init();
		Anouncer.init(getServer());
		Prefixer.init();
		Listeners.init(getServer().getPluginManager(), this);
		

	    PluginDescriptionFile pdfFile = getDescription();
	    System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");

	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
	    String commandName = command.getName().toLowerCase();
	    
	    if (commandName.equals("who")) { commandWho.sendWho(sender); return true; }
	    if (commandName.equals("spawn")) { commandSpawn.performSpawn(sender); return true; }
	    if (commandName.equals("setspawn")) { return commandSetspawn.performSetspawn(sender); }
	    if (commandName.equals("blockhead")) { return commandBlockhead.performBlockhead(sender, args); }
	    if (commandName.equals("prefix")) { return commandPrefix.performPrefix(sender, args); }
	    if (commandName.equals("rules")) { return Rules.send(sender, args); }
	    if (commandName.equals("gs")) { return commandGs.performGs(sender, args); }
	    if (commandName.equals("gsa")) { return commandGsa.performGsa(sender, args); }
	    if (commandName.equals("motd")) { Motd.sendMotd((Player)sender); return true; }
	    if (commandName.equals("a")) { return commandA.performA(sender, args); }
	    if (commandName.equals("reloadgs")) { commandReloadgs.perform(sender, args); return true; } 
	    return true;
	}
	
	
	
	
	//static methods for all scripts
	public static String replaceColours(String s) {
		return s.
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
	}
}
