package net.gamesketch.bukkit.bot;

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

  public void onDisable()
  {
  }

  public void onEnable()
  {
    PluginManager pm = getServer().getPluginManager();
    PluginDescriptionFile pdfFile = getDescription();
    pm.registerEvent(Event.Type.PLAYER_MOVE, this.playerListener, Event.Priority.Normal, this);
    pm.registerEvent(Event.Type.PLAYER_PICKUP_ITEM, this.playerListener, Event.Priority.Normal, this);
    pm.registerEvent(Event.Type.PLAYER_QUIT, this.playerListener, Event.Priority.Normal, this);
    isTimeFrozen = false;
    Settings.Load();
    if (!Rules.checkFile()) { System.out.println("Unable to load rules file."); }

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
      player.teleport(player.getWorld().getSpawnLocation());
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

    return false;
  }
}