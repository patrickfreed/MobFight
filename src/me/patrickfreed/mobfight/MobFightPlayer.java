package me.patrickfreed.mobfight;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MobFightPlayer {
	private Player player;
	private HashMap<String, HashMap<String, String>> playerdata = Util.dataPlayer;
=======
import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

public class MobFightPlayer {
	private Player player;
	private File players = new File("plugins/MobFight/Data", "players.yml");
	private Configuration playerdata = new Configuration(players);
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
	public MobFightPlayer(Player p){
		this.player = p;
	}
	public MobFightPlayer(String s){
<<<<<<< HEAD
		player = Bukkit.getServer().getPlayer(s);
=======
		MobFight m = new MobFight();
		player = m.getServer().getPlayer(s);
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
	}
	public String getName(){
		return player.getName();
	}
<<<<<<< HEAD
	public boolean isPlaying(){	
		return playerdata.containsKey(getName());
	}
	public MobFightGame getGame(){
		return new MobFightGame(playerdata.get(getName()).get("Game"));
	}
	public boolean isEnemyWith(MobFightPlayer attacker){
		if(playerdata.containsKey(attacker.getName()) && playerdata.containsKey(getName())){
			if(playerdata.get(attacker.getName()).get("Game").equalsIgnoreCase(getGame().getName())){
				return playerdata.get(attacker.getName()).get("Team") != playerdata.get(getName()).get("Team");	
			}
		}
	return false;
	}
	public boolean isTeammateWith(MobFightPlayer player){
			if(player.isPlaying() && isPlaying()){
				if(player.getTeam().equalsIgnoreCase(getTeam())){
					return true;
				}
			}
		return false;
	}
	@Deprecated
	public boolean isLeader(MobFightGame game){
		HashMap<String, String> gameconfig = game.getOptions();
		if(game.exists()){
			if (Util.contains(gameconfig.get("Leaders").split(":"), player.getName())){
=======
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
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
				return true;
			}
		}
		return false;
	}
	public Player getCraftPlayer(){
		return player;
	}
	public String getTeam(){
<<<<<<< HEAD
		if(playerdata.containsKey(player.getName())){
			return playerdata.get(player.getName()).get("Team");
		}
		return null;
	}
=======
		if(playerdata.getAll().containsKey(player.getName())){
			return playerdata.getString(player.getName() + ".Team");
		}
		return null;
	}
	public File getGameFile(){
		return new File("plugins/MobFight/Data", getGame());
	}
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
	public boolean hasPermission(String node){
		return player.hasPermission(node) || player.isOp();
	}
	public void sendMessage(String message){
		player.sendMessage(ChatColor.GREEN + "[Mobfight] " + message);
	}
<<<<<<< HEAD
	public boolean isInArena(){
		int pX = getCraftPlayer().getLocation().getBlockX();
		int pY = getCraftPlayer().getLocation().getBlockY();
		int pZ = getCraftPlayer().getLocation().getBlockZ();
		
		MobFightArena arena = getGame().getArena();
		Location firstCorner = arena.getCorner(1);
		int fX = firstCorner.getBlockX();
		int fY = firstCorner.getBlockY();
		int fZ = firstCorner.getBlockZ();
		
		Location secondCorner = arena.getCorner(2);
		int sX = secondCorner.getBlockX();
		int sY = secondCorner.getBlockY();
		int sZ = secondCorner.getBlockZ();
		
		if(((pX >= fX && pX <= sX) || pX <= fX && pX >= sX))
			if(((pY >= fY && pY <= sY) || pY <= fY && pY >= sY))
				if(((pZ >= fZ && pZ <= sZ) || pZ <= fZ && pZ >= sZ))
					return true;
		return false;
	}
	public void kick(){
		MobFightGame game = getGame();
		List<String> playerlist = Util.Teams.get(getGame().getName()).get(getTeam());

		if(playerlist.contains(getName()))
			playerlist.remove(getName());

		Util.Teams.get(getGame().getName()).put(getTeam(), playerlist);

		player.teleport(game.getArena().getWarpLocation());
		playerdata.remove(getName());	
	}
=======
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
}