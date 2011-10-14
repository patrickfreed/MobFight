package me.patrickfreed.mobfight.Commands;

import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

public class List {
	public static boolean run(String[] args, MobFightPlayer player){
		player.sendMessage(Util.Games.values().toString());
		return true;
	}
}
