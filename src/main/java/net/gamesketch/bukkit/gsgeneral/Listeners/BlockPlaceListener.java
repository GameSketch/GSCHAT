package net.gamesketch.bukkit.gsgeneral.Listeners;

import net.gamesketch.bukkit.gsgeneral.GUESTPREV.Guestprev;

import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener extends BlockListener {
	
	public void onBlockPlace(BlockPlaceEvent event) {
		Guestprev.callEvent(event);
	}
}
