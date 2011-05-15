package net.gamesketch.bukkit.gsgeneral.MISC;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ThunderMod {

	public static void interactEvent(PlayerInteractEvent event) {
    	if (!event.getPlayer().isOp()) { return; }
    	if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) { return; }
    	if (!event.getPlayer().getItemInHand().getType().equals(Material.GOLD_SWORD)) { return; }
    	Player player = event.getPlayer();
    	Location target = player.getLocation();
    	for (Block b : player.getLineOfSight(null, 200)) {
    		if (!b.getType().equals(Material.AIR)) { target = b.getLocation(); break; }
    	}
    	player.getWorld().strikeLightning(target);
	}
	
}
