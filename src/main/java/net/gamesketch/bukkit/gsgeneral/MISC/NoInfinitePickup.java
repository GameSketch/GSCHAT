package net.gamesketch.bukkit.gsgeneral.MISC;

import org.bukkit.event.player.PlayerPickupItemEvent;

public class NoInfinitePickup {

	public static void pickupEvent(PlayerPickupItemEvent event) {
		if (event.getItem().getItemStack().getAmount() < 0) {
			if (event.getPlayer().isOp()) { return; }
			event.setCancelled(true);
		}
	}
	
}
