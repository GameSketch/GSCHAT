package net.gamesketch.bukkit.gsgeneral.Listeners;

import net.gamesketch.bukkit.gsgeneral.MISC.FireInGodModeFix;
import net.gamesketch.bukkit.gsgeneral.MISC.WaterHelmet;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class EntityDamageListener extends EntityListener {
	
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player)event.getEntity();
			
			WaterHelmet.damageEvent(player, event);
			FireInGodModeFix.damageEvent(player, event);
		}
	}
	
}
