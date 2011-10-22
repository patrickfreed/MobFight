package me.patrickfreed.mobfight.Mobs;

import me.patrickfreed.mobfight.MobFightPlayer;

public interface MobFightMob {
	
	public String getName();

	public MobFightPlayer getPlayer();

	public int getFullHealth();
	
	public int getStandardAttackDamage();

	public int getSpecialAttackDamage();
}
