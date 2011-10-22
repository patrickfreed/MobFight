package me.patrickfreed.mobfight.Commands;

import java.util.HashMap;
import java.util.List;

import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import com.nijikokun.register.payment.Methods;

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
						int playersRed = Util.Teams.get(game.getName()).get("Red").size();
						int playersBlu = Util.Teams.get(game.getName()).get("Blu").size();

						String Team = "Red";
						if ((playersRed != 0 && playersBlu != 0) && playersRed < playersBlu){
							Team = "Red";
						}else if ((playersRed != 0 && playersBlu != 0) && playersRed < playersBlu){
							Team = "Blu";
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
						player.setMob(mob);
						
						List<String>playerslist = Util.Teams.get(game.getName()).get(Team);
						playerslist.add(player.getName());
						Util.Teams.get(game.getName()).put(Team, playerslist);
						
						if(game.getOptions().get("Money") != null)
							Methods.getMethod().getAccount(player.getName()).subtract(Double.valueOf(game.getOptions().get("Money")));
						
						playerdata.put(player.getName(), new HashMap<String, String>());
		
						playerdata.get(player.getName()).put("Game", game.getName());
						playerdata.get(player.getName()).put("Team", Team);
						player.getCraftPlayer().sendMessage(ChatColor.GREEN + "[MobFight]" + ChatColor.YELLOW + " Disguised as " + mob + " and fighting for " + Team + "!");
						
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