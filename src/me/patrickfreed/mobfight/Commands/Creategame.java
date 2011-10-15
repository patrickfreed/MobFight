package me.patrickfreed.mobfight.Commands;

<<<<<<< HEAD
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
=======
import java.io.File;

import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.util.config.Configuration;

public class Creategame {
	private static File arenafile = new File("plugins/MobFight/Data", "arenas.yml");
	private static Configuration arenas = new Configuration(arenafile);
	public static boolean run(String[] args, MobFightPlayer player){
		Utilities util = new Utilities();
		if ((args.length >= 4 && args.length <= 6) && (player.hasPermission("mobfight.*"))){
			String game = args[1];
			String team1 = args[2];
			String team2 = args[3];
			String arena = args[4];
			String score = args[5];	
			File file = new File("plugins/MobFight/Data", game+".yml");
			Configuration data = new Configuration(file);
			if(file.exists()){
				player.sendMessage(ChatColor.RED + "A game with that name already exists!");
				return true;
			}
			else if (arenas.getNode(arena) == null){
				player.sendMessage(ChatColor.RED + "Arena '" + arena + "' does not exist!");
				return true;
			}else{
				file.getParentFile().mkdirs();
				data.setProperty("Arena", arena);
				data.setProperty("Score-Limit", score);
				data.setProperty("Team1.Name", team1);
				data.setProperty("Team2.Name", team2);
				data.setProperty("Warp.x", player.getCraftPlayer().getLocation().getX());
				data.setProperty("Warp.y", player.getCraftPlayer().getLocation().getY());
				data.setProperty("Warp.z", player.getCraftPlayer().getLocation().getZ());
				data.setProperty("Warp.World", player.getCraftPlayer().getWorld());

				if (args.length > 5){
					String money = args[6];
					data.setProperty("Money", money);
				}
				data.save();
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
				player.sendMessage(ChatColor.YELLOW + "You just created game '" + game + "' in arena '" + arena + "'!");		
				return true;
			}//else
		}//end of permissions check
<<<<<<< HEAD
		else if(!player.hasPermission("mobfight.admin")){
=======
		else if(!player.hasPermission("mobfight.*")){
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
			player.sendMessage(util.noPermission());
			return true;
		}
		return false;
	}
}
