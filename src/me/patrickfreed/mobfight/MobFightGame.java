package me.patrickfreed.mobfight;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MobFightGame {
	private String name;
	public MobFightGame(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	@Deprecated
	public File getFile(){
		return new File("plugins/MobFight/Data/Games/", name + ".yml");
	}
	public HashMap<String, String> getOptions(){
		return Util.Games.get(name);
	}
	public boolean exists(){
		if (getFile().exists()){
			return true;
		}
		return false;
	}
	public void end(){
		HashMap<String,String> gamedata = this.getOptions();
		int team1score = Integer.parseInt(gamedata.get("Team1Score"));
		int team2score = Integer.parseInt(gamedata.get("Team2Score"));

		if(team1score > team2score){
			MobFight m = new MobFight();
			 
			List<String> winners = Util.Teams.get(getName()).get("Team1");
			List<String> losers = Util.Teams.get(getName()).get("Team2");
			
			for (int x = 0; x < winners.toArray().length; x++){
				MobFightPlayer player = new MobFightPlayer(m.getServer().getPlayer(winners.get(x)));
				if(gamedata.containsKey("Money")){
					//TODO Add register/economy support
					player.sendMessage("Congratulations! You won the game! You receive " + gamedata.get("Money"));
				}else{
					player.sendMessage(ChatColor.AQUA + "Congratulations! You won the game!");
					player.getCraftPlayer().teleport(getArena().getWarpLocation());
				}
			}
			for (int x = 0; x < losers.toArray().length; x++){
				Player player = m.getServer().getPlayer(losers.get(x));
				if(gamedata.containsKey("Money")){
					//TODO Add register/economy support
					player.sendMessage(ChatColor.RED + "You lost the game! You lose " + gamedata.get("Money"));
				}else{
					player.sendMessage(ChatColor.RED + "You lost the game!");
					player.teleport(getArena().getWarpLocation());
				}
			}
			List<String> listEmpty = new ArrayList<String>();
			Util.Teams.get(getName()).put("Team1", listEmpty);
			Util.Teams.get(getName()).put("Team2", listEmpty);
			gamedata.put("Score", gamedata.get("DefaultScore"));
			gamedata.put("Team1Score", "0");
			gamedata.put("Team2Score", "0");
		}
		else if(team2score > team1score){
			List<String> winners = Util.Teams.get(getName()).get("Team2");
			List<String> losers = Util.Teams.get(getName()).get("Team1");
			
			for (int x = 0; x < winners.toArray().length; x++){
				MobFightPlayer player = new MobFightPlayer(Bukkit.getServer().getPlayer(winners.get(x)));
				if(gamedata.containsKey("Money")){
					//TODO Add register/economy support
					player.sendMessage("Congratulations! You won the game! You receive " + gamedata.get("Money"));
				}else{
					player.sendMessage(ChatColor.AQUA + "Congratulations! You won the game!");
					player.getCraftPlayer().teleport(getArena().getWarpLocation());
				}
			}
			for (int x = 0; x < losers.toArray().length; x++){
				Player player = Bukkit.getServer().getPlayer(losers.get(x));
				if(gamedata.containsKey("Money")){
					//TODO Add register/economy support
					player.sendMessage(ChatColor.RED + "You lost the game! You lose " + gamedata.get("Money"));
				}else{
					player.sendMessage(ChatColor.RED + "You lost the game!");
					player.teleport(getArena().getWarpLocation());
				}
			}
			List<String> listEmpty = new ArrayList<String>();
			Util.Teams.get(getName()).put("Team1", listEmpty);
			Util.Teams.get(getName()).put("Team2", listEmpty);
			gamedata.put("Score", gamedata.get("DefaultScore"));
			gamedata.put("Team1Score", "0");
			gamedata.put("Team2Score", "0");
		}
	}
	@Deprecated
	public String getTeamNumber(String team){
		HashMap<String, String> data = this.getOptions();
		if(data.get("Team1Name").equalsIgnoreCase(team)){
			return "Team1";
		}else if(data.get("Team2Name").equalsIgnoreCase(team)){
			return "Team2";
		}else{
			return null;
		}
	} 

	public MobFightArena getArena(){
		return new MobFightArena(this.getOptions().get("Arena"));
	}
	
	public void delete(){
		Util.Games.remove(getName());
		Util.Teams.remove(getName());
	}
	
}