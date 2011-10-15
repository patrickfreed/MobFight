package me.patrickfreed.mobfight.Commands;

import java.util.HashMap;

import me.patrickfreed.mobfight.MobFightArena;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

import org.bukkit.Location;

public class Setspawn {

	public static boolean run(String args[], MobFightPlayer player){
		Util util = new Util();
		MobFightArena arena = new MobFightArena(args[1]);
		if(args.length == 2){ 
			if (player.hasPermission("mobfight.admin")){
				String team = args[2];
				Location location = player.getCraftPlayer().getLocation();	
				HashMap<String, String> data = Util.Arenas.get(arena.getName());
				if (arena.exists()){
					
					double x = location.getX();
					double y = location.getY();
					double z = location.getZ();

					data.put(team + ".Location.x", Double.toString(x));
					data.put(team + ".Location.y", Double.toString(y));
					data.put(team + ".Location.z", Double.toString(z));
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