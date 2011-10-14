package me.patrickfreed.mobfight.Commands;

import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

public class Delete {

	public static boolean run(String[] args, MobFightPlayer player) {
		Util util = new Util();
		MobFightGame game = new MobFightGame(args[1]);
		if (args.length == 2){
			if(player.hasPermission("mobfight.*")){
				if(game.exists()){
					game.end();
					game.delete();
					return true;
				}else{
					player.sendMessage("That game does not exist!");
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