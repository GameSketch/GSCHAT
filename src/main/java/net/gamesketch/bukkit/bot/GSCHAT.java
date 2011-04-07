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

public class GSCHAT extends JavaPlugin
{
  private final PlayerListener playerListener = new playerListener();
  public static boolean isTimeFrozen;

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

    System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
  }

  public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
  {
    String commandName = command.getName().toLowerCase();

    if (commandName.equals("who")) {
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
      Player player = (Player)sender;
      player.teleport(player.getWorld().getSpawnLocation());
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
        player.sendMessage("The distance from you to the spawn is " + distance + " blocks.");
        return true;
      }
      if (commandName.equals("/angle")) {
        player.sendMessage("The angle from you to the spawn is " + angle + " degrees.");
        return true;
      }
      return false;
    }
    if (commandName.equals("blockhead")) {
      boolean success = BlockHead.perform(sender, args);
      return success;
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
      }
      isTimeFrozen = !isTimeFrozen;
      player.sendMessage(ChatColor.AQUA + "The time is frozen until you reset now.");
      return true;
    }

    return false;
  }
}