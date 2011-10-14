package me.patrickfreed.mobfight.Commands;

import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
<<<<<<< HEAD
import me.patrickfreed.mobfight.Util;
=======
import me.patrickfreed.mobfight.Utilities;
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a

public class Delete {

	public static boolean run(String[] args, MobFightPlayer player) {
<<<<<<< HEAD
		Util util = new Util();
		MobFightGame game = new MobFightGame(args[1]);
		if (args.length == 2){
			if(player.hasPermission("mobfight.*")){
=======
		Utilities util = new Utilities();
		MobFightGame game = new MobFightGame(args[1]);
		if (args.length == 2){
			if(player.hasPermission("mobfight.*")|| player.isLeader(game)){
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
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
<<<<<<< HEAD
}
=======

}
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
