package net.gamesketch.bukkit.gsgeneral.Listeners;

import net.gamesketch.bukkit.gsgeneral.GUESTPREV.Guestprev;
import net.gamesketch.bukkit.gsgeneral.MISC.NoInfinitePickup;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupListener extends PlayerListener {

	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		NoInfinitePickup.pickupEvent(event);
		Guestprev.callEvent(event);
	}
	
}
