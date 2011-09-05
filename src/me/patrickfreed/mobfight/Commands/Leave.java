package me.patrickfreed.mobfight.Commands;

import java.io.File;

import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.util.config.Configuration;

public class Leave {
	public static boolean run(String[] args, MobFightPlayer player){
		Utilities util = new Utilities();
		if (args.length == 1 && (player.hasPermission("mobfight.leave"))){
			File file = new File("plugins/MobFight/Data", "players.yml");
			if (file.exists()){
				Configuration data = new Configuration(file);
				if (data.getAll().containsKey(player.getName())){
					data.removeProperty(player.getName());
					data.save();	
					player.sendMessage(ChatColor.YELLOW + "You just left the game!");
					return true;
				}
			}
		}
		else if(!player.hasPermission("mobfight.leave")){
			player.sendMessage(util.noPermission());
			return true;
		}
		return false;
	}
}
