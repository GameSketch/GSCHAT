package net.gamesketch.bukkit.gsgeneral.GUESTPREV;

import net.gamesketch.bukkit.gsgeneral.PREFIX.*;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class Guestprev {
	public static void callEvent(BlockPlaceEvent event) {
		Player p = event.getPlayer();
		boolean isGuest = isGuest(p);
		
		if (isGuest) { event.setCancelled(true); }
	}
	public static void callEvent(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		boolean isGuest = isGuest(p);
		
		if (!isGuest) return;
		if (event.getClickedBlock() == null) { return; }
		if (event.getClickedBlock().getTypeId() == 0) { return; }
		
		int clicked = event.getClickedBlock().getTypeId();
		boolean shouldCancel = false;
		if (clicked == 46 || clicked == 54 || clicked == 58 || clicked == 62 || clicked == 95) { shouldCancel = true; }
		
		if (shouldCancel) {
			event.setCancelled(true);
			p.sendMessage("Guests can't open container blocks.");
		}
	}
	public static void callEvent(PlayerPickupItemEvent event) {
		Player p = event.getPlayer();
		boolean isGuest = isGuest(p);
		
		if (!isGuest) return;
		
		event.setCancelled(true);
	}
	public static void callEvent(BlockBreakEvent event) {
		Player p = event.getPlayer();
		boolean isGuest = isGuest(p);
		
		if (!isGuest) return;
		event.setCancelled(true);
		event.getBlock().setType(event.getBlock().getType());
	}
	
	
	
	
	public static boolean isGuest(Player p) {
		String prefix = Prefixer.getPrefix(p).getPrefix();
		if (prefix == null || prefix.length() < 1) { return true; }
		if (Prefixer.getPrefix(p).getPrefix().length() <= 1) { return true; }
		return false;
	}
}
