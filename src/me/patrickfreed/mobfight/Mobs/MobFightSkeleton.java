package me.patrickfreed.mobfight.mobs;

import me.patrickfreed.mobfight.MobFightPlayer;

public class MobFightSkeleton implements MobFightMob {

	private MobFightPlayer player;
	
	public MobFightSkeleton(MobFightPlayer p){
		this.player = p;
	}
	
	@Override
	public String getName() {
		return "skeleton";
	}

	@Override
	public int getFullHealth() {
		return 10;
	}

	@Override
	public int getStandardAttackDamage() {
		return 1;
	}

	
	@Override
	public int getSpecialAttackDamage() {
		return 8;
	}

	@Override
	public void specialAttack(MobFightPlayer victim) {
		if(player.getCraftPlayer().isSneaking()){
			victim.damage(getSpecialAttackDamage());
		}else{
			victim.damage(getStandardAttackDamage());
		}
	}

	@Override
	public void normalAttack(MobFightPlayer victim) {
		victim.damage(getStandardAttackDamage());	
	}
}