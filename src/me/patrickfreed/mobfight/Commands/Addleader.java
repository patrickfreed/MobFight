package me.patrickfreed.mobfight.Commands;

<<<<<<< HEAD
import java.util.Arrays;
import java.util.HashMap;
=======
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
import java.util.List;

import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
<<<<<<< HEAD
import me.patrickfreed.mobfight.Util;

import org.bukkit.ChatColor;

@Deprecated
public class Addleader {
	public static boolean run(String[]args, MobFightPlayer player){
		Util util = new Util();
		MobFightGame game = new MobFightGame(args[1]);
		if(args.length == 3 && player.hasPermission("mobfight.*")){
			HashMap<String, String> gameconfig = game.getOptions();
			if(game.exists()){
				String[] arrayList = gameconfig.get("Leaders").split(":");
				List<String> list = Arrays.asList(arrayList);
				if(!list.contains(args[2])){
					list.add(args[2].toLowerCase());
					String strList = "";
					arrayList = (String[]) list.toArray();
					for(int x = 0; x < arrayList.length; x++){
						strList = strList.concat(arrayList[x]+":");
					}
					gameconfig.put("Leaders", strList);
=======
import me.patrickfreed.mobfight.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.util.config.Configuration;

public class Addleader {
	public static boolean run(String[]args, MobFightPlayer player){
		Utilities util = new Utilities();
		MobFightGame game = new MobFightGame(args[1]);
		if(args.length == 3 && (player.hasPermission("mobfight.*") || player.isLeader(game))){
			Configuration gameconfig = game.getConfiguration();
			if(game.exists()){
				List<String> list = gameconfig.getStringList("Leaders", null);
				if(!list.contains(args[2])){
					list.add(args[2].toLowerCase());
					gameconfig.setProperty("Leaders", list);
					gameconfig.save();
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
					return true;
				}else{
					player.sendMessage(ChatColor.RED + "Player '" + args[2] + "' is already a leader!");
					return true;
				}
			}
		}
		else if(!player.hasPermission("mobfight.*")){
			player.sendMessage(util.noPermission());
			return true;
		}
		return false;
	}
}
