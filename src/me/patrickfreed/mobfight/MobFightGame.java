package me.patrickfreed.mobfight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.nijikokun.register.payment.Methods;

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

	public HashMap<String, String> getOptions(){
		return Util.Games.get(name);
	}

	public boolean exists(){
		return Util.Games.containsKey(getName());
	}

	public void end(){
		HashMap<String,String> gamedata = this.getOptions();
		int scoreRed = Integer.parseInt(gamedata.get("Red.Score"));
		int scoreBlue = Integer.parseInt(gamedata.get("Blu.Score"));
		String strWinner = null, strLoser = null;

		if(scoreRed > scoreBlue){
			strWinner = "Red";
			strLoser = "Blu";
		}
		else if(scoreBlue > scoreRed){
			strWinner = "Blu";
			strLoser = "Red";
		}else{
			List<String> playersRed = Util.Teams.get(getName()).get("Red");
			List<String> playersBlu = Util.Teams.get(getName()).get("Blu");

			for (int x = 0; x < playersRed.size(); x++){
				MobFightPlayer player = new MobFightPlayer(Bukkit.getServer().getPlayer(playersRed.get(x)));
				if(gamedata.containsKey("Money")){
					Methods.getMethod().getAccount(playersRed.get(x)).add(Double.valueOf(gamedata.get("Money")) * 2);
					player.sendMessage("Congratulations! You won the game! You receive " + gamedata.get("Money"));
				}else{
					player.sendMessage(ChatColor.AQUA + "Congratulations! You won the game!");
					player.getCraftPlayer().teleport(getArena().getWarpLocation());
				}
			
			}

			for (int x = 0; x < playersBlu.size(); x++){
				MobFightPlayer player = new MobFightPlayer(Bukkit.getServer().getPlayer(playersBlu.get(x)));
				if(gamedata.containsKey("Money")){
					Methods.getMethod().getAccount(playersRed.get(x)).add(Double.valueOf(gamedata.get("Money")) * 2);
					player.sendMessage("Tie game!, here is your money back.");
				}else{
					player.sendMessage(ChatColor.AQUA + "Tie Game! You're all losers!");
					player.getCraftPlayer().teleport(getArena().getWarpLocation());
				}
			}
			
			List<String> listEmpty = new ArrayList<String>();
			Util.Teams.get(getName()).put("Red", listEmpty);
			Util.Teams.get(getName()).put("Blu", listEmpty);
			gamedata.put("Score", gamedata.get("DefaultScore"));
			gamedata.put("Red.Score", "0");
			gamedata.put("Blu.Score", "0");
			
			return;
		}
		MobFight m = new MobFight();

		List<String> winners = Util.Teams.get(getName()).get(strWinner);
		List<String> losers = Util.Teams.get(getName()).get(strLoser);

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

			List<String> listEmpty = new ArrayList<String>();
			Util.Teams.get(getName()).put("Red", listEmpty);
			Util.Teams.get(getName()).put("Blu", listEmpty);
			gamedata.put("Score", gamedata.get("DefaultScore"));
			gamedata.put("Red.Score", "0");
			gamedata.put("Blu.Score", "0");
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