package me.patrickfreed.mobfight.mobs;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import me.patrickfreed.mobfight.MobFightPlayer;

public class MobFightSpider implements MobFightMob{
	
	private MobFightPlayer player;
	
	public MobFightSpider(MobFightPlayer p){
		this.player = p;
	}
	@Override
	public String getName() {
		return "spider";
	}

	@Override
	public int getFullHealth() {
		return 20;
	}

	@Override
	public int getStandardAttackDamage() {
		return 3;
	}

	@Override
	public int getSpecialAttackDamage() {
		return 1;
	}

	@Override
	public void specialAttack(MobFightPlayer victim) {
		player.damage(4);
		if(player.getCraftPlayer().isDead()){
			player.sendMessage(ChatColor.RED + "You do not have enough strength after spraying your web, you died!");
			player.getGame().sendDeathMessage(victim, player);
			return;
		}else{
			victim.getCraftPlayer().getLocation().getBlock().setType(Material.WEB);
			victim.damage(getSpecialAttackDamage());
			if(victim.getCraftPlayer().isDead()){
				player.getGame().sendDeathMessage(victim, player);
			}
		}
	}

	@Override
	public void normalAttack(MobFightPlayer victim) {
		victim.damage(getStandardAttackDamage());
		if(victim.getCraftPlayer().isDead())
			victim.getGame().sendDeathMessage(player, victim);
	}

}
