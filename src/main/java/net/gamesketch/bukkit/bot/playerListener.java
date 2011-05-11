package net.gamesketch.bukkit.bot;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
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
		if (GSGeneral.isTimeFrozen) {
			if (GSGeneral.enablefreezetime) { 
				if (event.getPlayer().getWorld().getTime() >= 4000 || event.getPlayer().getWorld().getTime() < 3000)  {
					event.getPlayer().getWorld().setTime(3000);
				}
			}
		}
		/*
		 * Underwater breathing.
		 */
		if (GSGeneral.enablewaterhelmet) { 
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
			if (GSGeneral.isTimeFrozen) {
				GSGeneral.isTimeFrozen = !GSGeneral.isTimeFrozen;
			}
		}
		
	}
	
	/*
	 * GS Motd
	 */
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final Timer timer = new Timer();
        timer.schedule(
        	new TimerTask() {
        		public void run() {
        			player.performCommand("motd");
        			player.performCommand("who");
        			timer.cancel();
        		}
        	}
        , 2500);
        if (Prefixer.getPrefix(event.getPlayer()) == null) { Prefixer.prefixes.add(new PlayerPrefix(event.getPlayer().getName())); }
    }
    
    /*
     * Admin Chat
     */
    public void onPlayerChat(PlayerChatEvent event) {
    	Player player = event.getPlayer();
    	Player[] online = event.getPlayer().getServer().getOnlinePlayers();

    	if (GSGeneral.adminchat.contains(player) && !player.isOp()) { GSGeneral.adminchat.remove(player); }
    	if (GSGeneral.adminchat.contains(player)) {
    		event.setCancelled(true);
    		
    		for (Player p : online) {
    			if (p.isOp()) {
    				p.sendMessage("[" + ChatColor.GREEN + "/a" + ChatColor.WHITE + "] " + player.getName() + ": " + event.getMessage());
    			}
    		}
    		
    	}
    	if (event.isCancelled()) { return; }
    	
    	String prefix = Prefixer.getPrefix(event.getPlayer()).getPrefix();
    	if (prefix == null || prefix.length() < 1) { return; }
    	event.setFormat(prefix + ' ' + event.getFormat());
    }
    public void onPlayerInteract(PlayerInteractEvent event) {
    	if (!GSGeneral.enableLightning) { return; }
    	if (!event.getPlayer().isOp()) { return; }
    	if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) { return; }
    	if (!event.getPlayer().getItemInHand().getType().equals(Material.GOLD_SWORD)) { return; }
    	Player player = event.getPlayer();
    	Location target = player.getLocation();
    	for (Block b : player.getLineOfSight(null, 200)) {
    		if (!b.getType().equals(Material.AIR)) { target = b.getLocation(); break; }
    	}
    	player.getWorld().strikeLightning(target);
    }
}