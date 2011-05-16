package net.gamesketch.bukkit.gsgeneral.MISC;

import org.bukkit.command.CommandSender;

import net.gamesketch.bukkit.gsgeneral.PREFIX.PlayerPrefix;
import net.gamesketch.bukkit.gsgeneral.PREFIX.Prefixer;

public class commandPrefix {
	
	public static boolean performPrefix(CommandSender sender, String[] args) {
    	if (!sender.isOp()) { return true; }
    	String targetname = "";
    	if (args.length < 3) { return false; }
      	targetname = args[0].toLowerCase();
      	
    	PlayerPrefix prefix = Prefixer.getPrefixByName(targetname);
    	Prefixer.prefixes.remove(prefix);
    	
    	prefix = new PlayerPrefix(targetname, args[1], Prefixer.toInt(args[2]));
    	
    	Prefixer.prefixes.add(prefix);
    	sender.sendMessage("Done. Added " + targetname + " as " + prefix.getPrefix());
    	Prefixer.Save();
    	return true;
	}
	
}
