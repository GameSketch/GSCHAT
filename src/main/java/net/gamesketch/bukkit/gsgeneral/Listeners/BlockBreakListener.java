package net.gamesketch.bukkit.gsgeneral.Listeners;

import net.gamesketch.bukkit.gsgeneral.GUESTPREV.Guestprev;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;

public class BlockBreakListener extends BlockListener {
	
	public void onBlockBreak(BlockBreakEvent event) {
		Guestprev.callEvent(event);
	}
	
}
