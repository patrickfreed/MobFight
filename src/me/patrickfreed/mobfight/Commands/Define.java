package me.patrickfreed.mobfight.Commands;

<<<<<<< HEAD
import java.util.HashMap;

import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;
=======
import java.io.File;
import java.io.IOException;

import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Utilities;
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
import me.patrickfreed.mobfight.Listeners.MobFightPlayerListener;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.util.BlockVector;
<<<<<<< HEAD

public class Define {
	public static boolean run(String[] args, MobFightPlayer player) {
		HashMap<String, HashMap<String,String>> arenadata = Util.Arenas;
		Util util = new Util();
=======
import org.bukkit.util.config.Configuration;

public class Define {
	public static boolean run(String[] args, MobFightPlayer player) {
		Utilities util = new Utilities();
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
		BlockVector block1 = MobFightPlayerListener.block1;
		BlockVector block2 = MobFightPlayerListener.block2;
		World world = MobFightPlayerListener.world;
		if (args.length == 2){
			if (player.hasPermission("mobfight.*")){
				if (util.areSet()){
					if(util.areEligible(block1, block2)){
<<<<<<< HEAD
						if(!arenadata.containsKey(args[1])){
								arenadata.put(args[1], new HashMap<String, String>());
								arenadata.get(args[1]).put("World", world.getName());
								arenadata.get(args[1]).put("Corner-1.x", Integer.toString(block1.getBlockX()));
								arenadata.get(args[1]).put("Corner-1.y", Integer.toString(block1.getBlockY()));
								arenadata.get(args[1]).put("Corner-1.z", Integer.toString(block1.getBlockZ()));
								arenadata.get(args[1]).put("Corner-2.x", Integer.toString(block2.getBlockX()));
								arenadata.get(args[1]).put("Corner-2.y", Integer.toString(block2.getBlockY()));
								arenadata.get(args[1]).put("Corner-2.z", Integer.toString(block2.getBlockZ()));
								arenadata.get(args[1]).put("Warp.x", Double.toString(player.getCraftPlayer().getLocation().getX()));
								arenadata.get(args[1]).put("Warp.y", Double.toString(player.getCraftPlayer().getLocation().getY()));
								arenadata.get(args[1]).put("Warp.z", Double.toString(player.getCraftPlayer().getLocation().getZ()));
								
								player.sendMessage("You successfully created arena '" + args[1] + "'!");	
								return true;
=======
						File arena = new File("plugins/MobFight/Data/Arenas", args[1] + ".yml");
						Configuration arenadata = new Configuration(arena);
						if(!arena.exists()){
							try {
								arena.createNewFile();
								arenadata.setProperty("Name", args[1]);
								arenadata.setProperty("World", world.getName());
								arenadata.setProperty("Corner-1.x", block1.getBlockX());
								arenadata.setProperty("Corner-1.y", block1.getBlockY());
								arenadata.setProperty("Corner-1.z", block1.getBlockZ());
								arenadata.setProperty("Corner-2.x", block2.getBlockX());
								arenadata.setProperty("Corner-2.y", block2.getBlockY());
								arenadata.setProperty("Corner-2.z", block2.getBlockZ());
								player.sendMessage("You successfully created arena '" + args[1] + "'!");
								return true;
							} catch (IOException e) {
								e.printStackTrace();
							}
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
						}else{
							player.sendMessage("That arena already exists!");
							return true;
						}
					}else{
						player.sendMessage(ChatColor.RED + "One of the points has to be 3 blocks higher or lower than the other!");
						return true;
					}
				}else{
					player.sendMessage(ChatColor.RED + "You need to set 2 points first!");
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