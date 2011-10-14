package me.patrickfreed.mobfight.Commands;

import java.util.HashMap;

import me.patrickfreed.mobfight.MobFightArena;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

import org.bukkit.ChatColor;

public class Setwarp {
	public static boolean run(String[] args, MobFightPlayer player) {
		if(args.length == 2){
			MobFightArena arena = new MobFightArena(args[1]);
			Util util = new Util();
			HashMap<String, String> dataArena = arena.getOptions();
			if(arena.exists()){
				if(player.hasPermission("mobfight.admin")){
					dataArena.put("Warp.x", Double.toString(player.getCraftPlayer().getLocation().getX()));
					dataArena.put("Warp.y", Double.toString(player.getCraftPlayer().getLocation().getY()));
					dataArena.put("Warp.z", Double.toString(player.getCraftPlayer().getLocation().getZ()));
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