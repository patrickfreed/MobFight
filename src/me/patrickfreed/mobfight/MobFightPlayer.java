package me.patrickfreed.mobfight;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

public class MobFightPlayer {
	private Player player;
	private File players = new File("plugins/MobFight/Data", "players.yml");
	private Configuration playerdata = new Configuration(players);
	public MobFightPlayer(Player p){
		this.player = p;
	}
	public MobFightPlayer(String s){
		MobFight m = new MobFight();
		player = m.getServer().getPlayer(s);
	}
	public String getName(){
		return player.getName();
	}
	public boolean isPlaying(){
		return playerdata.getAll().containsKey(getName());
	}
	public String getGame(){
		return playerdata.getString(getName() + ".Game");
	}
	public boolean isEnemyWith(Player player){
		if(playerdata.getAll().containsKey(player.getName()) && playerdata.getAll().containsKey(getName())){
			if(playerdata.getString(player.getName() + ".Game").equalsIgnoreCase(playerdata.getString(player.getName() + ".Game"))){
				if (playerdata.getString(player.getName() + "Team") != playerdata.getString(getName() + ".Team")){
					return true;
				}
			}
		}
		return false;
	}
	public boolean isTeammateWith(Player player){
		if(players.exists()){
			if(playerdata.getAll().containsKey(player) && playerdata.getAll().containsKey(getName())){
				if(playerdata.getString(player + ".Team").equalsIgnoreCase(playerdata.getString(getName() + ".Team"))){
					return true;
				}
			}
		}
		return false;
	}
	public boolean isLeader(MobFightGame game){
		Configuration gameconfig = game.getConfiguration();
		if(game.exists()){
			if (gameconfig.getStringList("Leaders", null).contains(player)){
				return true;
			}
		}
		return false;
	}
	public Player getCraftPlayer(){
		return player;
	}
	public String getTeam(){
		if(playerdata.getAll().containsKey(player.getName())){
			return playerdata.getString(player.getName() + ".Team");
		}
		return null;
	}
	public File getGameFile(){
		return new File("plugins/MobFight/Data", getGame());
	}
	public boolean hasPermission(String node){
		return player.hasPermission(node) || player.isOp();
	}
	public void sendMessage(String message){
		player.sendMessage(ChatColor.GREEN + "[Mobfight] " + message);
	}
}