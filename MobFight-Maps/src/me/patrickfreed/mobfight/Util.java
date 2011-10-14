package me.patrickfreed.mobfight;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import me.patrickfreed.mobfight.Listeners.MobFightPlayerListener;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockVector;

public class Util {
	public static HashMap<String, HashMap<String, List<String>>> Teams = new HashMap<String, HashMap<String, List<String>>>();
	public static HashMap<String, HashMap<String, String>> dataPlayer = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, HashMap<String, String>> Arenas = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, HashMap<String, String>> Games = new HashMap<String, HashMap<String, String>>();
	public String noPermission(){
		return ChatColor.RED + "You don't have permission to do that!";
	}
	public void displayHelp(Player player){
		player.sendMessage(ChatColor.GREEN + "------MobFight Help------");
		player.sendMessage("/mobfight help - Shows list of commands");
		player.sendMessage("/mobfight caijgsod");
		//TODO add rest of commands
	}
	public String killMsg(MobFightPlayer player){
		return ChatColor.YELLOW + "You just killed " + ChatColor.AQUA + player.getName() + ChatColor.YELLOW + "and gained " + MobFight.config.getInt("Points-Per-Kill", 1) + " point(s)!";	
	}

	public boolean areSet() {
		return MobFightPlayerListener.block1 != null && MobFightPlayerListener.block2 != null;
	}
	public boolean areEligible(BlockVector block1, BlockVector block2){
		int eligibility = 3;
		int actual = Math.abs(block1.getBlockY() - block2.getBlockY());
		return actual >= eligibility;	
	}
	public void keepPlayersInArena(final MobFight instance){
		instance.getServer().getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {
			public void run() {
				MobFightPlayer player = null;
				for (World world : instance.getServer().getWorlds())
					for (Entity entity : world.getEntities()){
						if ((entity instanceof Player))
							player = new MobFightPlayer((Player)entity);
						if (player.isPlaying()){
							if(!player.isInArena()){
								player.kick();
							}
						}
					}
			}
		}, 1200L, 1200L);
	}	
	public boolean isMob(String mob){
		List<String> Mobs = Arrays.asList("zombie", "pig", "skeleton", "cow", "chicken", "spider", "creeper"); 
		
		if(Mobs.contains(mob)){
			return true;
		}	
		return false;
	}
	public static boolean contains(String[] args, String s){
		for(int x = 0; x < args.length; x++){
			if(args[x].contains(s)){
				return true;
			}
		}
		return false;
	}
}
