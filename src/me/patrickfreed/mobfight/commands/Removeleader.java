package me.patrickfreed.mobfight.Commands;

import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

import org.bukkit.ChatColor;
@Deprecated
public class Removeleader {
	public static boolean run(String[] args, MobFightPlayer player){
		Util util = new Util();
		MobFightPlayer leader = new MobFightPlayer(args[2]);
		if(args.length == 3){
			MobFightGame game = new MobFightGame(args[1]);
			if(player.hasPermission("mobfight.*") || player.isLeader(game)){
				if(game.exists()){
					if(leader.isLeader(game)){
						//Configuration gamedata = game.getConfiguration();
						//List<String> list = gamedata.getStringList("Leaders", null);
						//list.remove(args[2]);
						//gamedata.setProperty("Leaders", list);
						//gamedata.save();
						return true;
					}
				}else{
					player.sendMessage(ChatColor.RED + "Game '" + args[1] + "' does not exist!");
					return true;
				}
			}else{
				player.sendMessage(util.noPermission());
				return true;
			}
		}
		return false;
	}
}
