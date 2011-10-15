package me.patrickfreed.mobfight.Commands;

import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
<<<<<<< HEAD
import me.patrickfreed.mobfight.Util;

public class End {
	public static boolean run(String[] args, MobFightPlayer player){
		Util util = new Util();
		MobFightGame game = new MobFightGame(args[1]);
		if (args.length == 2){
			if (player.hasPermission("mobfight.*")){
=======
import me.patrickfreed.mobfight.Utilities;

public class End {
	public static boolean run(String[] args, MobFightPlayer player){
		Utilities util = new Utilities();
		MobFightGame game = new MobFightGame(args[1]);
		if (args.length == 2){
			if (player.hasPermission("mobfight.*") || player.isLeader(game)){
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
				if(game.exists()){
					game.end();
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
