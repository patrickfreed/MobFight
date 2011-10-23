package me.patrickfreed.mobfight.mobs;

import java.util.Random;

import org.bukkit.ChatColor;

import me.patrickfreed.mobfight.MobFightPlayer;

public class MobFightZombie implements MobFightMob{

	private MobFightPlayer player;
	
	public MobFightZombie(MobFightPlayer p){
		this.player = p;
	}
	@Override
	public String getName() {
		return "zombie";
	}

	@Override
	public int getFullHealth() {
		return 20;
	}

	@Override
	public int getStandardAttackDamage() {
		return 5;
	}

	@Override
	public int getSpecialAttackDamage() {
		return 2;
	}

	@Override
	public void specialAttack(MobFightPlayer victim) {
		Random n = new Random();
		if(n.nextInt(1) == 0){
			player.getCraftPlayer().setHealth(player.getHealth() + victim.getHealth()/2);
			victim.damage(victim.getHealth());
		}else{
			player.damage(player.getHealth());
			player.sendMessage(ChatColor.RED + "You tried to eat " + victim.getName() +  "'s brains, but he stabbed you in the throat!");		
		}
	}

	@Override
	public void normalAttack(MobFightPlayer victim) {
		// TODO Auto-generated method stub
		
	}

}
