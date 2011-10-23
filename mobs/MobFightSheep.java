package me.patrickfreed.mobfight.mobs;

import org.bukkit.ChatColor;

import me.patrickfreed.mobfight.MobFightPlayer;

public class MobFightSheep implements MobFightMob {

	MobFightPlayer player;
	
	public MobFightSheep(MobFightPlayer p){
		this.player = p;
	}
	
	@Override
	public String getName() {
		return "sheep";
	}

	@Override
	public int getFullHealth() {
		return 10;
	}

	@Override
	public int getStandardAttackDamage() {
		return 2;
	}

	@Override
	public int getSpecialAttackDamage() {		
		return 2;
	}

	@Override
	public void specialAttack(MobFightPlayer victim) {
		player.damage(5);
		
		if(player.getCraftPlayer().isDead()){
			player.sendMessage(ChatColor.RED + "You crushed into him too hard and killed yourself!");
			return;
		}else{
			victim.damage(getSpecialAttackDamage());
			victim.getCraftPlayer().getLocation().add(0, 6, 0);
		}
		
	}

	@Override
	public void normalAttack(MobFightPlayer victim) {
		// TODO Auto-generated method stub
		
	}

}
