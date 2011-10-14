package me.patrickfreed.mobfight.Commands;

<<<<<<< HEAD
import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

import org.bukkit.ChatColor;
@Deprecated
public class Removeleader {
	public static boolean run(String[] args, MobFightPlayer player){
		Util util = new Util();
=======
import java.util.List;

import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.util.config.Configuration;

public class Removeleader {
	public static boolean run(String[] args, MobFightPlayer player){
		Utilities util = new Utilities();
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
		MobFightPlayer leader = new MobFightPlayer(args[2]);
		if(args.length == 3){
			MobFightGame game = new MobFightGame(args[1]);
			if(player.hasPermission("mobfight.*") || player.isLeader(game)){
				if(game.exists()){
					if(leader.isLeader(game)){
<<<<<<< HEAD
						//Configuration gamedata = game.getConfiguration();
						//List<String> list = gamedata.getStringList("Leaders", null);
						//list.remove(args[2]);
						//gamedata.setProperty("Leaders", list);
						//gamedata.save();
=======
						Configuration gamedata = game.getConfiguration();
						List<String> list = gamedata.getStringList("Leaders", null);
						list.remove(args[2]);
						gamedata.setProperty("Leaders", list);
						gamedata.save();
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
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
