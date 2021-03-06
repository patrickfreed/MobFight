package me.patrickfreed.mobfight.commands;

import java.util.HashMap;

import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;
import me.desmin88.mobdisguise.api.MobDisguiseAPI;

public class Leave {
	public static boolean run(String[] args, MobFightPlayer player){
		Util util = new Util();
		if (args.length == 1)
			if	(player.hasPermission("mobfight.leave")){
				HashMap<String, HashMap<String,String>> data = Util.dataPlayer;
				if (data.containsKey(player.getName())){
					player.kick("You left the game!");
					MobDisguiseAPI.undisguisePlayer(player.getCraftPlayer(), player.getMob().getName());
					return true;
				}
		}else{
			player.sendMessage(util.noPermission());
			return true;
		}
		return false;
	}
}