package net.gamesketch.bukkit.gsgeneral.Listeners;

import java.util.Timer;
import java.util.TimerTask;

import net.gamesketch.bukkit.gsgeneral.PREFIX.*;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class PlayerJoinListener extends PlayerListener {
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
}
