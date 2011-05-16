package net.gamesketch.bukkit.gsgeneral.Listeners;

import org.bukkit.event.Event;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class Listeners {
	private static BlockListener blockPlaceListener = new BlockPlaceListener();
	private static BlockListener blockBreakListener = new BlockBreakListener();
	
	private static PlayerListener playerChatListener = new PlayerChatListener();
	private static PlayerListener playerPickupListener = new PlayerPickupListener();
	private static PlayerListener playerJoinListener = new PlayerJoinListener();
	private static PlayerListener playerLoginListener = new PlayerLoginListener();
	private static PlayerListener playerInteractListener = new PlayerInteractListener();
	
	private static EntityListener entityDamageListener = new EntityDamageListener();
	
	
	public static void init(PluginManager pm, Plugin plugin) {
		pm.registerEvent(Event.Type.PLAYER_CHAT, playerChatListener, Event.Priority.Highest, plugin);
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityDamageListener, Event.Priority.Highest, plugin);
		pm.registerEvent(Event.Type.PLAYER_PICKUP_ITEM, playerPickupListener, Event.Priority.Normal, plugin);
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerJoinListener, Event.Priority.Normal, plugin);
		pm.registerEvent(Event.Type.PLAYER_LOGIN, playerLoginListener, Event.Priority.Normal, plugin);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerInteractListener, Event.Priority.Normal, plugin);
		pm.registerEvent(Event.Type.BLOCK_PLACE, blockPlaceListener, Event.Priority.Highest, plugin);
		pm.registerEvent(Event.Type.BLOCK_BREAK, blockBreakListener, Event.Priority.Normal, plugin);
	}
	
}
