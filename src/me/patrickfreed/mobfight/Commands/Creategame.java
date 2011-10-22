package me.patrickfreed.mobfight.Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.patrickfreed.mobfight.MobFightArena;
import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Creategame {
	public static boolean run(String[] args, MobFightPlayer player){
		Util util = new Util();
		if (args.length >= 4 && args.length <= 5) 
			if (player.hasPermission("mobfight.*")){
				String strGame = args[1];
				String strArena = args[2];
				String score = args[3];	
				Bukkit.getServer().broadcastMessage(args[1]);
				MobFightGame game = new MobFightGame(strGame);
				MobFightArena arena = new MobFightArena(strArena);

				if(game.exists()){
					player.sendMessage(ChatColor.RED + "A game with that name already exists!");
					return true;
				}
				else if (!arena.exists()){
					player.sendMessage(ChatColor.RED + "Arena '" + arena + "' does not exist!");
					return true;
				}
				else if(!arena.isReady()){
					player.sendMessage(ChatColor.RED + "Arena '" + arena + "' does not have spawn points set!");
				}
				else if(arena.isInGame()){
					player.sendMessage(ChatColor.RED + "Arena '" + arena + "' is already in a game!");
				}
				else{
					Util.Games.put(game.getName(), new HashMap<String, String>());
					HashMap<String, String> data = game.getOptions();
					data.put("Arena", strArena);
					data.put("DefaultScore", score);
					data.put("Score", score);
					data.put("Red.Score", "0");
					data.put("Blu.Score", "0");
					Util.Teams.put(game.getName(), new HashMap<String, List<String>>());
					Util.Teams.get(game.getName()).put("Red", ( new ArrayList<String>()));
					Util.Teams.get(game.getName()).put("Blu", ( new ArrayList<String>()));

					if (args.length > 5){
						String money = args[4];
						data.put("Money", money);
					}

					player.sendMessage(ChatColor.YELLOW + "You just created game '" + game.getName() + "' in arena '" + arena + "'!");		
					return true;
				}
			}else{
				player.sendMessage(util.noPermission());
				return true;
			}
		return false;
	}
}