package net.gamesketch.bukkit.gsgeneral.Listeners;

import net.gamesketch.bukkit.gsgeneral.MISC.BanTxtFileFix;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener extends PlayerListener {
	
	public void onPlayerLogin(PlayerLoginEvent event) {
		BanTxtFileFix.loginEvent(event);
	}
}
