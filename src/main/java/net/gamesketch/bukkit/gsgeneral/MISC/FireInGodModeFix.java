package net.gamesketch.bukkit.gsgeneral.MISC;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

public class FireInGodModeFix {
	
	public static void damageEvent(Player p, EntityDamageEvent event) {
		if (event.isCancelled()) {
			if (p.getFireTicks() > 0) {
				p.setFireTicks(0);
			}
		}
	}
}
