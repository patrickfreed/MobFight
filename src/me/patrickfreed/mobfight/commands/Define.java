package me.patrickfreed.mobfight.commands;

import java.util.HashMap;

import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;
import me.patrickfreed.mobfight.listeners.MobFightPlayerListener;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.util.BlockVector;

public class Define {
	public static boolean run(String[] args, MobFightPlayer player) {
		HashMap<String, HashMap<String,String>> arenadata = Util.Arenas;
		Util util = new Util();
		BlockVector block1 = MobFightPlayerListener.block1;
		BlockVector block2 = MobFightPlayerListener.block2;
		World world = MobFightPlayerListener.world;
		System.out.println(arenadata);
		if (args.length == 2){
			if (player.hasPermission("mobfight.*")){
				if (util.areSet()){
					if(util.areEligible(block1, block2)){
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
								
								System.out.println("d");
								System.out.println("x" + arenadata.get(args[1]).get("Warp.x"));
								System.out.println("y" + arenadata.get(args[1]).get("Warp.y"));
								System.out.println("z" + arenadata.get(args[1]).get("Warp.z"));
								player.sendMessage("You successfully created arena '" + args[1] + "'!");	
								return true;
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