package me.patrickfreed.mobfight.mobs;

import org.bukkit.ChatColor;

import me.patrickfreed.mobfight.MobFightPlayer;

public class MobFightCow implements MobFightMob{

	MobFightPlayer player;
	
	public MobFightCow(MobFightPlayer p){
		this.player = p;
	}
	@Override
	public String getName() {
		return "cow";
	}

	@Override
	public int getFullHealth() {
		return 25;
	}

	@Override
	public int getStandardAttackDamage() {
		return 1;
	}

	@Override
	public int getSpecialAttackDamage() {
		player.damage(5);
		return 5;
	}
	@Override
	public void specialAttack(MobFightPlayer victim) {
		player.damage(5);
		
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
