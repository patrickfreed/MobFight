package me.patrickfreed.mobfight.Commands;

import java.util.HashMap;
import java.util.List;

import me.desmin88.mobdisguise.api.MobDisguiseAPI;
import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Join {
	public static boolean run(String[]args, MobFightPlayer player){
		
		Util util = new Util();
		
		if (args.length == 3){
			if (player.hasPermission("mobfight.join")){
				String gamestring = args[1];
				String mob = args[2];
				MobFightGame game = new MobFightGame(gamestring);
				
				if (game.exists()){
					HashMap<String, HashMap<String, String>> playerdata = Util.dataPlayer;
					if (!player.isPlaying()){
						int Team1Players = Util.Teams.get(game.getName()).get("Team1").size();
						int Team2Players = Util.Teams.get(game.getName()).get("Team2").size();

						String Team = "Team1";
						if ((Team1Players != 0 && Team2Players != 0) && Team1Players < Team2Players){
							Team = "Team1";
						}else if ((Team1Players != 0 && Team2Players != 0) && Team1Players < Team2Players){
							Team = "Team2";
						}
						
						if(!util.isMob(mob)){
							player.sendMessage(ChatColor.RED + "That is not an eligible mob!");
							return true;
						}

						double x = Double.valueOf(game.getArena().getOptions().get(Team + ".Location.x"));
						double y = Double.valueOf(game.getArena().getOptions().get(Team + ".Location.y"));
						double z = Double.valueOf(game.getArena().getOptions().get(Team + ".Location.z"));
						Location location = new Location(player.getCraftPlayer().getWorld(), x,y,z);
						
						player.getCraftPlayer().teleport(location);
						MobDisguiseAPI.disguisePlayer(player.getCraftPlayer(), mob.toLowerCase());
						
						List<String>playerslist = Util.Teams.get(game.getName()).get(Team);
						playerslist.add(player.getName());
						Util.Teams.get(game.getName()).put(Team, playerslist);
						
						//TODO add max player setting and Economy support
						
						playerdata.put(player.getName(), new HashMap<String, String>());
						playerdata.get(player.getName()).put("Mob", mob);
						playerdata.get(player.getName()).put("Game", game.getName());
						playerdata.get(player.getName()).put("Team", Team);
						player.getCraftPlayer().sendMessage(ChatColor.GREEN + "[MobFight]" + ChatColor.YELLOW + " Disguised as " + mob + " and fighting for team '" + Team.substring(4) + "'!");
						
						return true;
						
					}else{
						player.sendMessage(ChatColor.RED + " You are already in game '" + playerdata.get(player.getName()).get("Game") + "'.");						
						return true;
					}
				}
			}
		}else{
			player.sendMessage(util.noPermission());
			return true;
		}
		return false;
	}
}