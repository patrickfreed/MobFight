package me.patrickfreed.mobfight.Commands;

import java.io.File;
import java.util.List;

import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.util.config.Configuration;

public class Removeplayer {
	public static boolean run(String[] args, MobFightPlayer player) {
		Utilities util = new Utilities();
		if (args.length == 2){
			File players = new File("plugins/MobFight/Data", "players.yml");
			Configuration playerdata = new Configuration(players);
			String gamename = playerdata.getString(args[1] + ".Game");
			MobFightGame game = new MobFightGame(gamename);
			Configuration gamedata = game.getConfiguration();
			if (player.hasPermission("mobfight.*") || player.isLeader(game)){
				if(game.exists()){
					if(playerdata.getAll().containsKey(args[1])){				
						List<String>team1 = gamedata.getStringList("Team1.Players", null);
						List<String>team2 = gamedata.getStringList("Team1.Players", null);
						if(team1.contains(args[1])){
							team1.remove(args[1]);
							playerdata.removeProperty(args[1]);
							gamedata.setProperty("Team1.Players", team1);
							gamedata.save();
							playerdata.save();
							return true;
						}
						else if(team2.contains(args[1])){
							team2.remove(args[1]);
							playerdata.removeProperty(args[1]);
							gamedata.setProperty("Team2.Players", team2);
							gamedata.save();
							playerdata.save();
							return true;
						}						

					}else{
						player.sendMessage(ChatColor.RED + "That player is not playing in any games!");
						return true;
					}
				}else{
					player.sendMessage(ChatColor.RED + "That game does not exist!");
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
