package net.gamesketch.bukkit.gsgeneral.Listeners;

import net.gamesketch.bukkit.gsgeneral.ADMINCHAT.AdminChat;
import net.gamesketch.bukkit.gsgeneral.PREFIX.Prefixer;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class PlayerChatListener extends PlayerListener {

	public void onPlayerChat(PlayerChatEvent event) {
		AdminChat.chatEvent(event);
		Prefixer.chatEvent(event);
	}
	
}
