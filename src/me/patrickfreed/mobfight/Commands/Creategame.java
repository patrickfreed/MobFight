package me.patrickfreed.mobfight.Commands;

import java.util.ArrayList;
import java.util.HashMap;

import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

import org.bukkit.ChatColor;

public class Creategame {
	public static boolean run(String[] args, MobFightPlayer player){
		Util util = new Util();
		if ((args.length >= 4 && args.length <= 5) && (player.hasPermission("mobfight.*"))){
			String strGame = args[1];
			String arena = args[2];
			String score = args[3];	
			MobFightGame game = new MobFightGame(strGame);
			HashMap<String, String> data = game.getOptions();
			
			if(game.exists()){
				player.sendMessage(ChatColor.RED + "A game with that name already exists!");
				return true;
			}
			else if (!(Util.Arenas.containsKey(arena))){
				player.sendMessage(ChatColor.RED + "Arena '" + arena + "' does not exist!");
				return true;
			}else{
				data.put("Arena", arena);
				data.put("Score-Limit", score);
				data.put("Score-Limit", score);
				Util.Teams.get(game.getName()).put("Team1", ( new ArrayList<String>()));
				Util.Teams.get(game.getName()).put("Team1", ( new ArrayList<String>()));
				// TODO Add to Arena class
				
				if (args.length > 5){
					String money = args[4];
					data.put("Money", money);
				}
				player.sendMessage(ChatColor.YELLOW + "You just created game '" + game + "' in arena '" + arena + "'!");		
				return true;
			}//else
		}//end of permissions check
		else if(!player.hasPermission("mobfight.admin")){
			player.sendMessage(util.noPermission());
			return true;
		}
		return false;
	}
}
