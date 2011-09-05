package me.patrickfreed.mobfight.Commands;

import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Join {
	public static boolean run(String[]args, MobFightPlayer player){
		Utilities util = new Utilities();
		if (args.length == 2){
			if (player.hasPermission("mobfight.join")){
				String gamestring = args[1];
				MobFightGame game = new MobFightGame(gamestring);
				if(game.exists()){
					Location location = game.getWarpLocation();
					player.getCraftPlayer().teleport(location);
					player.sendMessage(ChatColor.YELLOW + "Pick a team to start playing!");
					return true;
				}else{
					player.sendMessage(ChatColor.RED + "That game does not exist!");
				}
			}else{
				player.sendMessage(util.noPermission());
			}
		}
		return false;
	}
}
