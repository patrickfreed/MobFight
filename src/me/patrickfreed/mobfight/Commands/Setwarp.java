package me.patrickfreed.mobfight.Commands;

import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.util.config.Configuration;

public class Setwarp {
	public static boolean run(String[] args, MobFightPlayer player) {
		if(args.length == 2){
			MobFightGame game = new MobFightGame(args[1]);
			Utilities util = new Utilities();
			Configuration gamedata = game.getConfiguration();
			if(game.exists()){
				if(player.hasPermission("mobfight.*") || player.isLeader(game)){
					gamedata.setProperty("Warp.x", player.getCraftPlayer().getLocation().getX());
					gamedata.setProperty("Warp.y", player.getCraftPlayer().getLocation().getY());
					gamedata.setProperty("Warp.z", player.getCraftPlayer().getLocation().getZ());
					return true;
				}else{
					player.sendMessage(util.noPermission());
					return true;
				}
			}else{
				player.sendMessage(ChatColor.RED + "That game does not exist!");
				return true;
			}
		}
		return false;
	}
}
