package net.gamesketch.bukkit.gsgeneral.Listeners;

import net.gamesketch.bukkit.gsgeneral.GUESTPREV.Guestprev;
import net.gamesketch.bukkit.gsgeneral.MISC.ThunderMod;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class PlayerInteractListener extends PlayerListener {

	public void onPlayerInteract(PlayerInteractEvent event) {
		ThunderMod.interactEvent(event);
		Guestprev.callEvent(event);
	}
	
}
