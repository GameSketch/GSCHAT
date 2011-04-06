package net.gamesketch.bukkit.bot;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;


/**
* Replacements of old GS-CHAT commands.
*
* @author Streammz
* 
* 
* 
*/
public class GSCHAT extends JavaPlugin {
	private final PlayerListener playerListener = new playerListener();
	public static boolean isTimeFrozen;
    public void onDisable() {
        //PluginManager pm = getServer().getPluginManager();
    }

    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        PluginDescriptionFile pdfFile = this.getDescription();
        pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Priority.Normal, this);
    	isTimeFrozen = false;
        // Print a startup message to the console, so we know it was started.
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        String commandName = command.getName().toLowerCase();

        if (commandName.equals("who")) {
        	  Player[] online = sender.getServer().getOnlinePlayers();
          	  String list = "";
        	  int length = online.length - 1;
        	  int on = 0;
        	  for (Player current : online) {
        	    if (current == null) { on++;
        	    } else {
        	      list = list + (on >= length ? current.getName() : new StringBuilder().append(current.getName()).append(", ").toString());
        	      on++; } } int serverlist = online.length;
        	  String serverliststring = Integer.toString(serverlist);
        	
        	sender.sendMessage("Connected players (" + serverliststring + "): " + list);
        	return true;
        }
        if (commandName.equals("/spawn") || commandName.equals("spawn")) {
        	Player player = (Player)sender;
        	player.teleportTo(player.getWorld().getSpawnLocation());
        	player.sendMessage("Welcome to the spawn!");
        	return true;
        }
        if (commandName.equals("setspawn")) {
        	Player player = (Player)sender;
        	Location playerloc = player.getLocation();
        	if (!player.isOp()) {
        		player.sendMessage("You can't set the spawn!");
        		return false;
        	}
        	player.getWorld().setSpawnLocation((int)playerloc.getX(), (int)playerloc.getY(), (int)playerloc.getZ());
            player.sendMessage("You've set the spawn at your position.");
        	return true;
        }
        if (commandName.equals("/dist")) {
        	Player player = (Player)sender;
        	Location spawnloc = player.getWorld().getSpawnLocation();
        	Location playerloc = player.getLocation();
        	
        	
        	Vector spawnvect = new Vector();
        	spawnvect.setX(spawnloc.getX());
        	spawnvect.setY(spawnloc.getY());
        	spawnvect.setZ(spawnloc.getZ());
        	
        	Vector playervect = new Vector();
        	playervect.setX(playerloc.getX());
        	playervect.setY(playerloc.getY());
        	playervect.setZ(playerloc.getZ());
        	
        	String distance = Integer.toString((int)playervect.distance(spawnvect));
            
        	player.sendMessage("The distance from you to the spawn is " + distance + " blocks.");
        	return true;
        }
        if (commandName.equals("blockhead")) {
            boolean success = BlockHead.perform(sender, args);
            if (success) { return true; }
            else { return false; }
        }
        
        if (commandName.equals("freezetime")) {
        	Player player = (Player)sender;
        	if (!player.isOp()) {
        		player.sendMessage(ChatColor.RED + "You don't have the permissions to freeze time!");
        		return false;
        	}
        	if (isTimeFrozen) {
            	isTimeFrozen = !isTimeFrozen;
            	player.sendMessage(ChatColor.AQUA + "The time isn't frozen anymore.");
            	return true;
        	} else {
        		isTimeFrozen = !isTimeFrozen;
        		player.sendMessage(ChatColor.AQUA + "The time is frozen until you reset now.");
        		return true;
        	}
        }
        
       
        return false;
    }

}
