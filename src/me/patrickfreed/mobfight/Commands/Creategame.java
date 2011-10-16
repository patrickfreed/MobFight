package me.patrickfreed.mobfight.Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Creategame {
	public static boolean run(String[] args, MobFightPlayer player){
		Util util = new Util();
		if ((args.length >= 4 && args.length <= 5) && (player.hasPermission("mobfight.*"))){
			String strGame = args[1];
			String arena = args[2];
			String score = args[3];	
			Bukkit.getServer().broadcastMessage(args[1]);
			MobFightGame game = new MobFightGame(strGame);

			if(game.exists()){
				player.sendMessage(ChatColor.RED + "A game with that name already exists!");
				return true;
			}
			else if (!(Util.Arenas.containsKey(arena))){
				player.sendMessage(ChatColor.RED + "Arena '" + arena + "' does not exist!");
				return true;
			}else{
				Util.Games.put(game.getName(), new HashMap<String, String>());
				HashMap<String, String> data = game.getOptions();
				data.put("Arena", arena);
				data.put("DefaultScore", score);
				data.put("Score", score);
				data.put("Team1.Score", "0");
				data.put("Team2.Score", "0");
				Util.Teams.put(game.getName(), new HashMap<String, List<String>>());
				Util.Teams.get(game.getName()).put("Team1", ( new ArrayList<String>()));
				Util.Teams.get(game.getName()).put("Team2", ( new ArrayList<String>()));

				if (args.length > 5){
					String money = args[4];
					data.put("Money", money);
				}
				
				player.sendMessage(ChatColor.YELLOW + "You just created game '" + game + "' in arena '" + arena + "'!");		
				return true;
			}
		}
		else if(!player.hasPermission("mobfight.admin")){
			player.sendMessage(util.noPermission());
			return true;
		}
		return false;
	}
}