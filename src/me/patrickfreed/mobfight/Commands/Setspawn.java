package me.patrickfreed.mobfight.Commands;

<<<<<<< HEAD
import java.util.HashMap;

import me.patrickfreed.mobfight.MobFightArena;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

import org.bukkit.Location;
=======
import java.io.File;

import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Utilities;
import org.bukkit.Location;
import org.bukkit.util.config.Configuration;
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a

public class Setspawn {

	public static boolean run(String args[], MobFightPlayer player){
<<<<<<< HEAD
		Util util = new Util();
		MobFightArena arena = new MobFightArena(args[1]);
		if(args.length == 2){ 
			if (player.hasPermission("mobfight.admin")){
				String team = args[2];
				Location location = player.getCraftPlayer().getLocation();	
				HashMap<String, String> data = Util.Arenas.get(arena.getName());
				if (arena.exists()){
					
=======
		Utilities util = new Utilities();
		File game = new File("plugins/MobFight/Data", args[1] + ".yml");
		if(args.length == 2){ 
			if (player.hasPermission("mobfight.*")){
				String team = args[2];
				Location location = player.getCraftPlayer().getLocation();	
				Configuration data = new Configuration(game);
				if (game.exists()){
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
					double x = location.getX();
					double y = location.getY();
					double z = location.getZ();

<<<<<<< HEAD
					data.put(team + ".Location.x", Double.toString(x));
					data.put(team + ".Location.y", Double.toString(y));
					data.put(team + ".Location.z", Double.toString(z));
=======
					data.setProperty("Teams." + team + ".Location.x", x);
					data.setProperty("Teams." + team + ".Location.y", y);
					data.setProperty("Teams." + team + ".Location.z", z);
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
					return true;
				}
			}else{
				player.sendMessage(util.noPermission());
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
