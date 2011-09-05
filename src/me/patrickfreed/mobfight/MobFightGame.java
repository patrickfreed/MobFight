package me.patrickfreed.mobfight;

import java.io.File;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

public class MobFightGame {
	private String name;
	public MobFightGame(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public File getFile(){
		return new File("plugins/MobFight/Data", name + ".yml");
	}
	public Configuration getConfiguration(){
		return new Configuration(getFile());
	}
	public boolean exists(){
		if (getFile().exists()){
			return true;
		}
		return false;
	}
	public Location getWarpLocation(){
		MobFight m = new MobFight();
		Configuration gamedata = this.getConfiguration();
		Double x = gamedata.getDouble("Warp.x", 1);
		Double y = gamedata.getDouble("Warp.y", 1);
		Double z = gamedata.getDouble("Warp.z", 1);
		String world = gamedata.getString("Warp.World");;
		World worldy = m.getServer().getWorld(world);
		Location location = new Location(worldy, x,y,z);
		return location;
	}
	public void end(){
		Configuration gamedata = this.getConfiguration();
		int team1score = gamedata.getInt("Team1.Score", 0);
		int team2score = gamedata.getInt("Team2.Score", 0);

		if(team1score > team2score){
			MobFight m = new MobFight();
			List<String> winnerslist = gamedata.getStringList("Team1.Players", null);
			Object[] winners = winnerslist.toArray();
			List<String> loserlist = gamedata.getStringList("Team1.Players", null);
			Object[] losers = loserlist.toArray();
			for (int x = 0; x < winners.length; x++){
				MobFightPlayer player = new MobFightPlayer(m.getServer().getPlayer(winners[x].toString()));
				if(gamedata.getAll().containsKey("Money")){
					//TODO Add register/economy support
					player.sendMessage("Congratulations! You won the game! You receive " + gamedata.getInt("Money", 0));
				}else{
					player.sendMessage(ChatColor.AQUA + "Congratulations! You won the game!");
					player.getCraftPlayer().teleport(getWarpLocation());
				}
			}
			for (int x = 0; x < losers.length; x++){
				Player player = m.getServer().getPlayer(losers[x].toString());
				if(gamedata.getAll().containsKey("Money")){
					//TODO Add register/economy support
					player.sendMessage(ChatColor.RED + "You lost the game! You lose " + gamedata.getInt("Money", 0));
				}else{
					player.sendMessage(ChatColor.RED + "You lost the game!");
					player.teleport(getWarpLocation());
				}
			}
			gamedata.setProperty("Team1.Players", null);
			gamedata.setProperty("Team2.Players", null);
			gamedata.setProperty("Score", 0);
			gamedata.setProperty("Team1.Score", 0);
			gamedata.setProperty("Team2.Score", 0);
		}
		else if(team2score > team1score){
			MobFight m = new MobFight();
			List<String> winnerslist = gamedata.getStringList("Team2.Players", null);
			Object[] winners = winnerslist.toArray();
			List<String> loserlist = gamedata.getStringList("Team1.Players", null);
			Object[] losers = loserlist.toArray();
			for (int x = 0; x < winners.length; x++){
				Player player = m.getServer().getPlayer(winners[x].toString());
				if(gamedata.getAll().containsKey("Money")){
					//TODO Add register/economy support
					player.sendMessage("Congratulations! You won the game! You receive " + gamedata.getInt("Money", 0));
				}else{
					player.sendMessage(ChatColor.AQUA + "Congratulations! You won the game!");
					player.teleport(getWarpLocation());
				}
			}
			for (int x = 0; x < losers.length; x++){
				Player player = m.getServer().getPlayer(losers[x].toString());
				if(gamedata.getAll().containsKey("Money")){
					//TODO Add register/economy support
					player.sendMessage(ChatColor.RED + "You lost the game! You lose " + gamedata.getInt("Money", 0));
				}else{
					player.sendMessage(ChatColor.RED + "You lost the game!");
					player.teleport(getWarpLocation());
				}
			}
			gamedata.setProperty("Team1.Players", null);
			gamedata.setProperty("Team2.Players", null);
			gamedata.setProperty("Score", 0);
			gamedata.setProperty("Team1.Score", 0);
			gamedata.setProperty("Team2.Score", 0);
		}else{
			//TODO Add tie dialogue and economy support here
		}
	}
	public String getTeamNumber(String team){
		Configuration data = this.getConfiguration();
		if(data.getString("Team1.Name").equalsIgnoreCase(team)){
			return "Team1";
		}else if(data.getString("Team1.Name").equalsIgnoreCase(team)){
			return "Team2";
		}else{
			return null;
		}
	} 
	public void delete(){
		getFile().delete();
	}
}