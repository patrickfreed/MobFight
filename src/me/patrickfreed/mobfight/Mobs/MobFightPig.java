package me.patrickfreed.mobfight.Mobs;

import me.patrickfreed.mobfight.MobFightPlayer;

public class MobFightPig implements MobFightMob{
	
	private MobFightPlayer player;
	
	public MobFightPig(MobFightPlayer p){
		this.player = p;
	}
	
	public MobFightPig(String name){
		this.player = new MobFightPlayer(name);
	}

	public String getName(){
		return "pig";
	}

	public MobFightPlayer getPlayer(){
		return player;
	}

	public int getFullHealth(){
		return 13;
	}
	
	public int getStandardAttackDamage(){
		return 1;
	}

	public int getSpecialAttackDamage(){
		player.damage(3);
		return 3;
	}
	
}
