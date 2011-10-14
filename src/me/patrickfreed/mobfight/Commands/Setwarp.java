package me.patrickfreed.mobfight.Commands;

<<<<<<< HEAD
import java.util.HashMap;

import me.patrickfreed.mobfight.MobFightArena;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

import org.bukkit.ChatColor;
=======
import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.util.config.Configuration;
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a

public class Setwarp {
	public static boolean run(String[] args, MobFightPlayer player) {
		if(args.length == 2){
<<<<<<< HEAD
			MobFightArena arena = new MobFightArena(args[1]);
			Util util = new Util();
			HashMap<String, String> dataArena = arena.getOptions();
			if(arena.exists()){
				if(player.hasPermission("mobfight.admin")){
					dataArena.put("Warp.x", Double.toString(player.getCraftPlayer().getLocation().getX()));
					dataArena.put("Warp.y", Double.toString(player.getCraftPlayer().getLocation().getY()));
					dataArena.put("Warp.z", Double.toString(player.getCraftPlayer().getLocation().getZ()));
=======
			MobFightGame game = new MobFightGame(args[1]);
			Utilities util = new Utilities();
			Configuration gamedata = game.getConfiguration();
			if(game.exists()){
				if(player.hasPermission("mobfight.*") || player.isLeader(game)){
					gamedata.setProperty("Warp.x", player.getCraftPlayer().getLocation().getX());
					gamedata.setProperty("Warp.y", player.getCraftPlayer().getLocation().getY());
					gamedata.setProperty("Warp.z", player.getCraftPlayer().getLocation().getZ());
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
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
<<<<<<< HEAD
}
=======
}
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
