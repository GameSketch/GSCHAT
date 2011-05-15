package net.gamesketch.bukkit.gsgeneral.MISC;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandSetspawn {

	public static boolean performSetspawn(CommandSender sender) {
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
	
}
