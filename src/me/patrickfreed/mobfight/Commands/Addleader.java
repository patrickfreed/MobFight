package me.patrickfreed.mobfight.Commands;

import java.util.List;

import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.util.config.Configuration;

public class Addleader {
	public static boolean run(String[]args, MobFightPlayer player){
		Utilities util = new Utilities();
		MobFightGame game = new MobFightGame(args[1]);
		if(args.length == 3 && (player.hasPermission("mobfight.*") || player.isLeader(game))){
			Configuration gameconfig = game.getConfiguration();
			if(game.exists()){
				List<String> list = gameconfig.getStringList("Leaders", null);
				if(!list.contains(args[2])){
					list.add(args[2].toLowerCase());
					gameconfig.setProperty("Leaders", list);
					gameconfig.save();
					return true;
				}else{
					player.sendMessage(ChatColor.RED + "Player '" + args[2] + "' is already a leader!");
					return true;
				}
			}
		}
		else if(!player.hasPermission("mobfight.*")){
			player.sendMessage(util.noPermission());
			return true;
		}
		return false;
	}
}
