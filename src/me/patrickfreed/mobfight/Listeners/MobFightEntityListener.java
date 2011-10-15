package me.patrickfreed.mobfight.Listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.patrickfreed.mobfight.MobFight;
import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

public class MobFightEntityListener extends EntityListener{
	public Map<String,String> lastDamage = new HashMap<String,String>();
	public ArrayList<String> lastDamagePlayer = new ArrayList<String>();
	public void onEntityDamage(EntityDamageEvent damageEvent){
		if (damageEvent.isCancelled()){
			return;
		}
		Entity entity = damageEvent.getEntity();
		if(entity instanceof Player){
			MobFightPlayer player = new MobFightPlayer((Player)entity);
			if(damageEvent instanceof EntityDamageByEntityEvent){
				EntityDamageByEntityEvent event = (EntityDamageByEntityEvent)damageEvent;
				lastDamage.put(player.getName(), "none");
				switch (event.getCause()){
				case ENTITY_ATTACK:{
					if(event.getDamager()instanceof Player){
						MobFightPlayer attacker = new MobFightPlayer((Player)event.getDamager());
						if(player.isTeammateWith(attacker)){
							event.setCancelled(true);
							return;
						}else if(player.isEnemyWith(attacker)){
							lastDamage.put(player.getName(), "PVP:" + attacker);
						}
					}
					else if(event.getDamager() instanceof Arrow){
						Arrow arrow = (Arrow)event.getDamager();
						MobFightPlayer attacker = new MobFightPlayer((Player) arrow.getShooter());
						if(player.isTeammateWith(attacker)){
							event.setCancelled(true);
							return;
						}else if(player.isEnemyWith(attacker)){
							lastDamage.put(player.getName(), "PVP:" + attacker);
							return;
						}
					}
				}
				}
			}
		}
	}
	public void onEntityDeath(EntityDeathEvent event){
		MobFight m = new MobFight();
		Util util = new Util();
		
		if (event.getEntity() instanceof Player){
			MobFightPlayer player = new MobFightPlayer((Player)event.getEntity());
			
			if(player.isPlaying()){
				String lastdamage = lastDamage.get(player.getName());
				
				if (lastdamage.contains("PVP:")){
					String[] parts = lastDamage.get(player.getName()).split(":");
					MobFightPlayer pvper = new MobFightPlayer(m.getServer().getPlayer(lastDamage.get(parts[1])));
					
					if(player.isEnemyWith(pvper)){
						MobFightGame game = player.getGame();
						int score = Integer.parseInt(game.getOptions().get(player.getTeam() + "Score"));
						game.getOptions().put(player.getTeam() + "Score", Integer.toString(score++));
						pvper.sendMessage(util.killMsg(player));
					}
				}
			}
		}
	}
}
