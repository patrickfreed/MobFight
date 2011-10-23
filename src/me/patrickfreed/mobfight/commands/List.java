package me.patrickfreed.mobfight.commands;

import org.bukkit.ChatColor;

import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

public class List {
	public static boolean run(String[] args, MobFightPlayer player){
		player.sendMessage(ChatColor.BLUE + "Games: " + Util.Games.keySet().toString());
		player.sendMessage(ChatColor.BLUE + "Arenas: " + Util.Arenas.keySet().toString());
		return true;
	}
}