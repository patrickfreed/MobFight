package me.patrickfreed.mobfight.Commands;

import java.io.File;

import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Utilities;
import org.bukkit.Location;
import org.bukkit.util.config.Configuration;

public class Setspawn {

	public static boolean run(String args[], MobFightPlayer player){
		Utilities util = new Utilities();
		File game = new File("plugins/MobFight/Data", args[1] + ".yml");
		if(args.length == 2){ 
			if (player.hasPermission("mobfight.*")){
				String team = args[2];
				Location location = player.getCraftPlayer().getLocation();	
				Configuration data = new Configuration(game);
				if (game.exists()){
					double x = location.getX();
					double y = location.getY();
					double z = location.getZ();

					data.setProperty("Teams." + team + ".Location.x", x);
					data.setProperty("Teams." + team + ".Location.y", y);
					data.setProperty("Teams." + team + ".Location.z", z);
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
