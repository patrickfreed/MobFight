package me.patrickfreed.mobfight;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class MobFightArena {
	private String name;
	private HashMap<String, HashMap<String, String>> arenas = Util.Arenas;
	public MobFightArena(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public MobFightGame getGame(){
		return new MobFightGame(arenas.get(getName()).get("Game"));
	}

	public boolean isInGame(){
		return getGame() != null;
	}

	public Location getWarpLocation(){

		Double x = Double.valueOf(arenas.get(getName()).get("Warpx"));
		Double y = Double.valueOf(arenas.get(getName()).get("Warpy"));
		Double z = Double.valueOf(arenas.get(getName()).get("Warpz"));
		World worldy = Bukkit.getServer().getWorld(arenas.get(getName()).get("World"));

		return new Location(worldy, x,y,z);
	}

	public Location getCorner(int number){	

		double x = Double.valueOf(arenas.get(getName()).get("Corner-" + number + "x"));
		double y = Double.valueOf(arenas.get(getName()).get("Corner-" + number + "y"));
		double z = Double.valueOf(arenas.get(getName()).get("Corner-" + number + "z"));
		World world = Bukkit.getServer().getWorld(arenas.get(getName()).get("World"));

		return new Location(world, x,y,z);	
	}

	public boolean exists() {	
		return Util.Arenas.containsKey(getName());
	}

	public HashMap<String, String> getOptions(){
		return Util.Arenas.get(getName());
	}
}