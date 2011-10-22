package me.patrickfreed.mobfight.Commands;

import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

public class End {
	public static boolean run(String[] args, MobFightPlayer player){
		Util util = new Util();
		MobFightGame game = new MobFightGame(args[1]);
		if (args.length == 2){
			if (player.hasPermission("mobfight.admin")){
				if(game.exists()){
					game.end();
					player.sendMessage("Started a new game in arena '" + game.getArena().getName() +"'!");
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
