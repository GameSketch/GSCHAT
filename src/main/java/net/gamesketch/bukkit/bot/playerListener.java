package net.gamesketch.bukkit.bot;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class playerListener extends PlayerListener {
	
	public void onPlayerMove(PlayerMoveEvent event) {
		/*
		 * Time freezer.
		 */
		if (GSCHAT.isTimeFrozen) {
			if (event.getPlayer().getWorld().getTime() >= 4000 || event.getPlayer().getWorld().getTime() < 3000)  {
				event.getPlayer().getWorld().setTime(3000);
			}
		}
		/*
		 * Underwater breathing.
		 */
		int air = event.getPlayer().getRemainingAir();
		int maxair = event.getPlayer().getMaximumAir();
		if (air < maxair) {
			ItemStack helmet = event.getPlayer().getInventory().getHelmet();
			ItemStack glass = new ItemStack(20);
			if (helmet.getType().equals(glass.getType())) {
				event.getPlayer().setRemainingAir(maxair);
			}
		}
	}
	/*
	 * Non-ops infinite item prevention.
	 */
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		if (event.isCancelled()) { return; }
		if (event.getItem().getItemStack().getAmount() < 0) {
			if (!event.getPlayer().isOp()) {
				event.setCancelled(true);
				
			}
		}
	}
	
	/*
	 * 
	 * Halting the timefrozen timer if everybody left of the server.
	 */
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (event.getPlayer().getServer().getOnlinePlayers().length < 1) {
			if (GSCHAT.isTimeFrozen) {
				GSCHAT.isTimeFrozen = !GSCHAT.isTimeFrozen;
			}
		}
		
	}
}