package me.patrickfreed.mobfight.Commands;

import java.io.File;
<<<<<<< HEAD
import java.util.HashMap;

import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

import org.bukkit.ChatColor;

public class Leave {
	public static boolean run(String[] args, MobFightPlayer player){
		Util util = new Util();
		if (args.length == 1 && (player.hasPermission("mobfight.leave"))){
			File file = new File("plugins/MobFight/Data", "players.yml");
			if (file.exists()){
				HashMap<String, HashMap<String,String>> data = Util.dataPlayer;
				if (data.containsKey(player.getName())){
					player.kick();
=======

import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.util.config.Configuration;

public class Leave {
	public static boolean run(String[] args, MobFightPlayer player){
		Utilities util = new Utilities();
		if (args.length == 1 && (player.hasPermission("mobfight.leave"))){
			File file = new File("plugins/MobFight/Data", "players.yml");
			if (file.exists()){
				Configuration data = new Configuration(file);
				if (data.getAll().containsKey(player.getName())){
					data.removeProperty(player.getName());
					data.save();	
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
					player.sendMessage(ChatColor.YELLOW + "You just left the game!");
					return true;
				}
			}
		}
		else if(!player.hasPermission("mobfight.leave")){
			player.sendMessage(util.noPermission());
			return true;
		}
		return false;
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
