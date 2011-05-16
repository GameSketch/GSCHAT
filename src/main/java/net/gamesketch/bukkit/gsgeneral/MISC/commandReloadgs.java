package net.gamesketch.bukkit.gsgeneral.MISC;

import net.gamesketch.bukkit.gsgeneral.MOTD.Motd;
import net.gamesketch.bukkit.gsgeneral.PREFIX.Prefixer;

import org.bukkit.command.CommandSender;

public class commandReloadgs {
	public static void perform(CommandSender sender, String[] args) {
		if (!sender.isOp()) { return; }
		Prefixer.Load();
		Motd.init();
	}
}
