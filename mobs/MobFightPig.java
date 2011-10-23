package me.patrickfreed.mobfight.mobs;

import org.bukkit.ChatColor;

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

	@Override
	public void specialAttack(MobFightPlayer victim) {
		player.damage(3);
		
		if(player.getCraftPlayer().isDead()){
			player.sendMessage(ChatColor.RED + "You crushed into him too hard and killed yourself!");
			return;
		}else{
			victim.damage(getSpecialAttackDamage());		
		}
	}

	@Override
	public void normalAttack(MobFightPlayer victim) {
		victim.damage(getStandardAttackDamage());	
	}
	
}
