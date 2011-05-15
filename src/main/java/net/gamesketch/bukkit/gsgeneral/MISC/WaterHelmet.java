package net.gamesketch.bukkit.gsgeneral.MISC;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class WaterHelmet {
	
	public static void damageEvent(Player p, EntityDamageEvent event) {
		if (event.getCause().equals(DamageCause.DROWNING)) {
			if (!p.getInventory().getHelmet().getType().equals(Material.GLASS)) return;
			event.setCancelled(true);
			p.setRemainingAir(p.getMaximumAir());
		}
	}
	
}
