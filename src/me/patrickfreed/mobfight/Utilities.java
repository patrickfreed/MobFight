package me.patrickfreed.mobfight;

import java.io.File;
import java.io.IOException;
import java.util.List;

import me.desmin88.mobdisguise.api.MobDisguiseAPI;
import me.patrickfreed.mobfight.Listeners.MobFightPlayerListener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockVector;
import org.bukkit.util.config.Configuration;

public class Utilities {
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
	public void onRightClickSign(Sign sign, MobFightPlayer player){
		String gamestring = sign.getLine(1);
		String teamstring = sign.getLine(2);
		String mob = sign.getLine(3);
		if(sign.getLines().length == 4){	
			//is this a mobFight sign?
			if (sign.getLine(0).trim().compareToIgnoreCase("[MobFight]") == 0) {
				MobFightGame game = new MobFightGame(gamestring);
				File players = new File("plugins/MobFight/Data", "players.yml");
				//does the game exist and does the player have permission?
				if (game.exists() && (player.hasPermission("mobfight.join"))){
					Configuration playerdata = new Configuration(players);
					Configuration gamedata = game.getConfiguration();
					if (players.exists()){
						//Let's see if the player is on the list
						if (!player.isPlaying()){
							if (game.getTeamNumber(teamstring) != null){
								double x = gamedata.getDouble(game.getTeamNumber(teamstring) + ".Location.x", player.getCraftPlayer().getLocation().getX());
								double y = gamedata.getDouble(game.getTeamNumber(teamstring) + ".Location.x", player.getCraftPlayer().getLocation().getY());
								double z = gamedata.getDouble(game.getTeamNumber(teamstring) + ".Location.x", player.getCraftPlayer().getLocation().getZ());
								
								Location location = new Location(player.getCraftPlayer().getWorld(), x,y,z);
								player.getCraftPlayer().teleport(location);
								MobDisguiseAPI.disguisePlayer(player.getCraftPlayer(), mob.toLowerCase());
								
								List<String>playerslist = gamedata.getStringList(game.getTeamNumber(teamstring) + ".Players", null);
								playerslist.add(player.getName());
								gamedata.setProperty(game.getTeamNumber(teamstring) + ".Players", playerslist);
								//TODO add max player setting and Economy support
								playerdata.setProperty(player.getName() + ".mob", mob);
								playerdata.setProperty(player.getName() + ".game", gamestring);
								player.getCraftPlayer().sendMessage(ChatColor.GREEN + "[MobFight]" + ChatColor.YELLOW + " Disguised as " + mob + " and fighting for team '" + teamstring + "'!");
								playerdata.save();
								gamedata.save();
								//execute the else if the player is on the list
							}
						}else{
							player.sendMessage(ChatColor.RED + " You are already in game '" + playerdata.getString(player.getName()) + "'.");						
						}//else end
						//the players file doesn't exist yet
					}else{
						try {
							players.createNewFile();
							playerdata.setProperty(player.getName() + ".game", gamestring);
							playerdata.setProperty(player.getName() + ".mob", mob);
							playerdata.save();
						} catch (IOException e) {
							e.printStackTrace();
						}//catch
					}//else players file deosnt exist
				}//end - if game exists and player has permission
				//error handling - if the game doesnt exist
				else if (!game.exists()){
					player.sendMessage(ChatColor.RED + "That game does not exist.");
					return;
				}
				//error handling - if the player doesn't have permission
				else if (!player.hasPermission("mobfight.join")){
					player.sendMessage(ChatColor.RED + "You do not have permission to join games.");
					return;
				}else{
					return;
				}
			}//if [mobfight] end
		}//if sign.getlines end
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
		    	for (World world : instance.getServer().getWorlds())
					for (Entity entity : world.getEntities())
						if ((entity instanceof Player))
							if (entity.getLocation().getBlockX() > 5){
								
							}
		    }
		}, 1200L, 1200L);
	}	
}
