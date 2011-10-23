package me.patrickfreed.mobfight.mobs;

import me.patrickfreed.mobfight.MobFightPlayer;

public interface MobFightMob {
	
	public String getName();

	public int getFullHealth();
	
	public int getStandardAttackDamage();

	public int getSpecialAttackDamage();
	
	public void specialAttack(MobFightPlayer victim);
	
	public void normalAttack(MobFightPlayer victim);
}
