package net.gamesketch.bukkit.gsgeneral.MISC;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandSpawn {
	
	public static void performSpawn(CommandSender sender) {
    	Player player = (Player)sender;
    	Location spawn = player.getWorld().getSpawnLocation();
    	spawn.setX(spawn.getX() - 0.5);
    	spawn.setZ(spawn.getZ() - 0.5);
    	player.teleport(spawn);
    	player.sendMessage("Welcome to the spawn!");
    	return;
	}
	
}
