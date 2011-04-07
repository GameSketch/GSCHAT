package net.gamesketch.bukkit.bot;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/*
 * Performs the blockhead command
 */
public class BlockHead {
	
  public static boolean perform(CommandSender sender, String[] args) {
	Player target;
  	if (!sender.isOp()) {
		sender.sendMessage(ChatColor.RED + "You don't have the right permissions to execute this command!");
		return false;
	}
  	if (args.length < 2) { target = (Player)sender; }
  	else {
  		if (sender.getServer().getPlayer(args[1]) instanceof Player) {
  			target = sender.getServer().getPlayer(args[1]);
  		}
  		else { target = (Player)sender; }
  	}
	int itemid = 0;
	
    try { 
    	itemid = Integer.parseInt(args[0]);
    }
    catch (NumberFormatException e) { return false; }
    if (itemid >= 255) { return false; }
    
	ItemStack helm = new ItemStack(itemid, 1);
	target.getInventory().setHelmet(helm);
	target.sendMessage("Enjoy your new block head!");
	Player commandSender = (Player)sender;
	if (target.getName() != commandSender.getName()) {
		commandSender.sendMessage("You've given " + target.getName() + " the block " + args[0] + " as helmet.");
	}
	return true;
  }


  
}
