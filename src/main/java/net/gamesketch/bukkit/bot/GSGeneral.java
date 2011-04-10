package net.gamesketch.bukkit.bot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class GSGeneral extends JavaPlugin
{
  private final PlayerListener playerListener = new playerListener();
  private List<String> message;
  public static List<Player> adminchat;
  public static boolean isTimeFrozen;
  public static boolean enableWho = true;
  public static boolean enableSpawn = true;
  public static boolean enableSetspawn = true;
  public static boolean enabledist = true;
  public static boolean enableangle = true;
  public static boolean enableblockhead = true;
  public static boolean enablefreezetime = true;
  public static boolean enablewaterhelmet = true;
  public static boolean enableinfpickup = true;
  public static boolean enableRules = true;
  public static boolean enableReport = true;
  public static boolean enableAdminchat = true;
  public static boolean enableanouncer = true;
  public static long anouncetimer = 900000;

  public void onDisable()
  {
  }

  public void onEnable()
  {
	  
	
	 //MOTD
      message = new LinkedList<String>();
      try {
          File dataFile = getDataFile("motd.txt", false);
          if (!dataFile.exists()) {
              message.add("The MOTD needs to be set!");
              writeOutMOTD(dataFile);
          } else {
              readInMOTD(dataFile);
          }
      } catch (IOException e) {
          message.add("ERROR: Something is wrong with the MOTD file!");
      }
	  
	  
	  //Regular stuff
      
    adminchat = new LinkedList<Player>();
    PluginManager pm = getServer().getPluginManager();
    PluginDescriptionFile pdfFile = getDescription();
    pm.registerEvent(Event.Type.PLAYER_MOVE, this.playerListener, Event.Priority.Normal, this);
    pm.registerEvent(Event.Type.PLAYER_PICKUP_ITEM, this.playerListener, Event.Priority.Normal, this);
    pm.registerEvent(Event.Type.PLAYER_QUIT, this.playerListener, Event.Priority.Normal, this);
    pm.registerEvent(Event.Type.PLAYER_JOIN, this.playerListener, Event.Priority.Normal, this);
    pm.registerEvent(Event.Type.PLAYER_CHAT, this.playerListener, Event.Priority.Highest, this); 
    isTimeFrozen = false;
    Settings.Load();
    if (!Rules.checkFile()) { System.out.println("Unable to load rules file."); }
    Anouncer.Load(getServer());

    System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
  }

  public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
  {
    String commandName = command.getName().toLowerCase();

    if (commandName.equals("who")) {
    	if (!GSGeneral.enableWho) { return true; }
    		
      Player[] online = sender.getServer().getOnlinePlayers();
      String list = "";
      int length = online.length - 1;
      int on = 0;
      for (Player current : online)
        if (current == null) { on++;
        } else {
          list = list + (on >= length ? current.getName() : new StringBuilder().append(current.getName()).append(", ").toString());
          on++;
        } int serverlist = online.length;
      String serverliststring = Integer.toString(serverlist);

      sender.sendMessage(ChatColor.GREEN + "Connected players (" + serverliststring + "): " + ChatColor.GRAY + list);
      return true;
    }
    if ((commandName.equals("/spawn")) || (commandName.equals("spawn"))) {
    	if (!GSGeneral.enableSpawn) { return true; }
      Player player = (Player)sender;
      Location spawn = player.getWorld().getSpawnLocation();
      spawn.setX(spawn.getX() - 0.5);
      spawn.setZ(spawn.getZ() - 0.5);
      player.teleport(spawn);
      player.sendMessage("Welcome to the spawn!");
      return true;
    }
    if (commandName.equals("setspawn")) {
    	if (!GSGeneral.enableSetspawn) { return true; }
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
    if ((commandName.equals("/dist")) || (commandName.equals("/angle"))) {
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
      String angle = Integer.toString((int)playervect.angle(spawnvect));

      if (commandName.equals("/dist")) {
    	  if (!GSGeneral.enabledist) {
    		  player.sendMessage("The distance from you to the spawn is " + distance + " blocks.");
    	  }
        return true;
      }
      if (commandName.equals("/angle")) {
    	  if (!GSGeneral.enableangle) {
    		  player.sendMessage("The angle from you to the spawn is " + angle + " degrees.");
    	  }
    	return true;
      }
      return false;
    }
    if (commandName.equals("blockhead")) {
    	if (!GSGeneral.enableblockhead) { return true; }
      boolean success = BlockHead.perform(sender, args);
      return success;
    }

    if (commandName.equals("freezetime")) {
    	if (!GSGeneral.enablefreezetime) { return true; }
      Player player = (Player)sender;
      if (!player.isOp()) {
        player.sendMessage(ChatColor.RED + "You don't have the permissions to freeze time!");
        return false;
      }
      if (isTimeFrozen) {
        isTimeFrozen = !isTimeFrozen;
        player.sendMessage(ChatColor.AQUA + "The time isn't frozen anymore.");
        return true;
      }
      isTimeFrozen = !isTimeFrozen;
      player.sendMessage(ChatColor.AQUA + "The time is frozen until you reset now.");
      return true;
    }
    
    if (commandName.equals("rules")) {
    	if (!GSGeneral.enableRules) { return true; }
    	if (!Rules.send((Player)sender, args)) { return false; }
    	return true;
    }
    if (commandName.equals("gs")) {
    	Player player = (Player)sender;
    	if (!GSGeneral.enableReport) { return true; }
    	if (args.length < 1) { player.sendMessage(ChatColor.RED + "You can't support nothing!"); return false; }
    	else {
    		StringBuilder msg = new StringBuilder();
    		for (String arg : args) {
    			msg.append(arg);
    			msg.append(" ");
    		}
    		
    		Player[] online = player.getServer().getOnlinePlayers();
    		for (Player p : online) {
    			if (p.isOp()) {
    				p.sendMessage("[" + ChatColor.DARK_GREEN + "SUPPORT: " + player.getName() + ChatColor.WHITE + "] " + ChatColor.GOLD + msg);
    			}
    		}
    	}
    	return true;
    }
    
    if (commandName.equals("a")) {
    	Player player = (Player)sender;
    	if (!GSGeneral.enableAdminchat) { return true; }
    	if (!player.isOp()) { player.sendMessage(ChatColor.RED + "You are no moderator."); return true; }
        if (args.length < 1) { 
        	if (adminchat.contains(player)) {
        		adminchat.remove(player);
        		player.sendMessage(ChatColor.GREEN + "You " + ChatColor.RED + "aren't" + ChatColor.GREEN + " talking in the /a chat anymore");
        	}
        	else {
        		adminchat.add(player);
        		player.sendMessage(ChatColor.GREEN + "You are now talking in the /a chat."); 
        	}
        	return true; 
        }
        else {
        	Player[] online = player.getServer().getOnlinePlayers();
        	StringBuilder themessage = new StringBuilder();
        	for (String s : args) {
        		themessage.append(s);
        		themessage.append(" ");
        	}
        	for (Player p : online) {
    			if (p.isOp()) {
    				p.sendMessage("[" + ChatColor.GREEN + "/a" + ChatColor.WHITE + "] " + player.getName() + ": " + themessage);
    			}
    		}
        	return true;
        	
        }
    }
    

    
    if (commandName.equals("setmotd")) {
        if (args.length == 0) {
            sender.sendMessage("Please enter a message.");
            return true;
        }
        // build the message from the arguments, one by one
        StringBuilder builder = new StringBuilder();
        for (String s : args) {
            builder.append(s);
            builder.append(" ");
        }
        // return the built message, minus the last space that was appended
        if (builder.length() > 0) {
            // reset message and set it to the new message
            message = new LinkedList<String>();
            message.add(builder.substring(0, builder.length() - 1));
            try {
                writeOutMOTD(getDataFile("motd.txt", false));
            } catch (IOException e) {
                System.out.println("Could not write MOTD.");
            }
        }
        return true;
    }
    if (commandName.equals("motd")) {
        Player player = (Player)sender;
        for (String str : getMessage()) {
        	str = str.
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

            player.sendMessage(ChatColor.RED + "[MOTD]" + ChatColor.WHITE + " " + str);
        }
        return true;
    }
    
    

    return false;
  }
  
  /*
   * MOTD
   * STUFF
   * HERE
   */
  public List<String> getMessage() {
      return message;
  }
  
  public static List<Player> getAdminChat() {
	  return adminchat;
  }
  
  private void readInMOTD(File dataFile) throws FileNotFoundException, IOException {
      BufferedReader in = new BufferedReader(new FileReader(dataFile));
      String str;
      while ((str = in.readLine()) != null) {
      	if (!str.startsWith("#")) {
              message.add(str);
      	}
      }
      in.close();
  }
  
  private void writeOutMOTD(File dataFile) throws IOException {
      BufferedWriter out = new BufferedWriter(new FileWriter(dataFile));
      for (String str : message) {
          out.write(str);
      }
      out.close();
  }
  
  private boolean createDataDirectory() {
      File file = new File("plugins/GSGeneral/");
      if (!file.isDirectory()){
          if (!file.mkdirs()) {
              // failed to create the non existent directory, so failed
              return false;
          }
      }
      return true;
  }
  
  private File getDataFile(String filename, boolean mustAlreadyExist) {
      if (createDataDirectory()) {
          File file = new File("plugins/GSGeneral/" + filename);
          if (mustAlreadyExist) {
              if (file.exists()) {
                  return file;
              }
          } else {
              return file;
          }
      }
      return null;
  }
}