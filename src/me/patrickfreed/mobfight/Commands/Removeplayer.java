package me.patrickfreed.mobfight.Commands;

import me.patrickfreed.mobfight.MobFightPlayer;

public class Removeplayer {
	public static boolean run(String[] args, MobFightPlayer player) {
		if (args.length == 2){
			if(player.isPlaying()){
				player.kick();
				player.sendMessage("You've been kicked from game '" + player.getGame().getName() + "'");
			}
		}
		return false;
	}
}