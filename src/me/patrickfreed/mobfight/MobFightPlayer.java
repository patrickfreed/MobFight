package me.patrickfreed.mobfight;

import java.util.HashMap;
import java.util.List;

import me.desmin88.mobdisguise.api.MobDisguiseAPI;
import me.patrickfreed.mobfight.mobs.MobFightMob;
import me.patrickfreed.mobfight.mobs.MobFightPig;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MobFightPlayer{
	private Player player;
	private HashMap<String, HashMap<String, String>> playerdata = Util.dataPlayer;
	
	public MobFightPlayer(Player p){
		this.player = p;
	}
	public MobFightPlayer(String s){
		player = Bukkit.getServer().getPlayer(s);
	}
	public String getName(){
		return player.getName();
	}
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

	public Player getCraftPlayer(){
		return player;
	}
	public String getTeam(){
		if(playerdata.containsKey(player.getName())){
			return playerdata.get(player.getName()).get("Team");
		}
		return null;
	}
	public boolean hasPermission(String node){
		return player.hasPermission(node) || player.isOp();
	}
	public void sendMessage(String message){
		player.sendMessage(ChatColor.GREEN + "[Mobfight] " + message);
	}
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
		
		sendMessage("You have been kicked from the game!");
	}
	public void kick(String reason){
		MobFightGame game = getGame();
		List<String> playerlist = Util.Teams.get(getGame().getName()).get(getTeam());

		if(playerlist.contains(getName()))
			playerlist.remove(getName());

		Util.Teams.get(getGame().getName()).put(getTeam(), playerlist);

		player.teleport(game.getArena().getWarpLocation());
		playerdata.remove(getName());	
		
		sendMessage(reason);
	}
	
	public HashMap<String, String> getOptions(){
		return Util.dataPlayer.get(getName());
	}
	
	public MobFightMob getMob(){
		return new MobFightPig(this);
	}

	public void setMob(String mob){
		if(new MobFightPig(getName()) instanceof MobFightMob)
			Bukkit.getServer().broadcastMessage("It is an instanceof");
		
		if(mob.equalsIgnoreCase("pig")){
			MobFightPig pig = new MobFightPig(this);
			getCraftPlayer().setHealth(pig.getFullHealth());	
		}else if(mob.equalsIgnoreCase("cow")){
			MobFightPig pig = new MobFightPig(this);
			getCraftPlayer().setHealth(pig.getFullHealth());
		}
		//TODO Add rest of mobs
		
		Util.dataPlayer.put(getName(), new HashMap<String, String>());
		getOptions().put("Mob", mob);
		MobDisguiseAPI.disguisePlayer(getCraftPlayer(), mob);
	}

	public int getHealth(){
		return getCraftPlayer().getHealth();
	}
	
	public void damage(int amount){
		getCraftPlayer().damage((int)(amount*1.5));
	}

}