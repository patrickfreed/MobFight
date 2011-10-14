package me.patrickfreed.mobfight.Listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

<<<<<<< HEAD
import me.patrickfreed.mobfight.MobFight;
import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Util;
=======
import org.bukkit.util.config.*;

import me.patrickfreed.mobfight.MobFight;
import me.patrickfreed.mobfight.MobFightGame;
import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Utilities;
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a

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
<<<<<<< HEAD
						MobFightPlayer attacker = new MobFightPlayer((Player)event.getDamager());
=======
						Player attacker = (Player)event.getDamager();
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
						if(player.isTeammateWith(attacker)){
							event.setCancelled(true);
							return;
						}else if(player.isEnemyWith(attacker)){
							lastDamage.put(player.getName(), "PVP:" + attacker);
						}
					}
					else if(event.getDamager() instanceof Arrow){
						Arrow arrow = (Arrow)event.getDamager();
<<<<<<< HEAD
						MobFightPlayer attacker = new MobFightPlayer((Player) arrow.getShooter());
=======
						Player attacker = (Player) arrow.getShooter();
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
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
<<<<<<< HEAD
		Util util = new Util();
=======
		Utilities util = new Utilities();
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
		
		if (event.getEntity() instanceof Player){
			MobFightPlayer player = new MobFightPlayer((Player)event.getEntity());
			
			if(player.isPlaying()){
				String lastdamage = lastDamage.get(player.getName());
				
				if (lastdamage.contains("PVP:")){
					String[] parts = lastDamage.get(player.getName()).split(":");
					MobFightPlayer pvper = new MobFightPlayer(m.getServer().getPlayer(lastDamage.get(parts[1])));
					
<<<<<<< HEAD
					if(player.isEnemyWith(pvper)){
						MobFightGame game = player.getGame();
						int score = Integer.parseInt(game.getOptions().get(player.getTeam() + "Score"));
						game.getOptions().put(player.getTeam() + "Score", Integer.toString(score++));
						pvper.sendMessage(util.killMsg(player));
=======
					if(player.isEnemyWith(pvper.getCraftPlayer())){
						MobFightGame game = new MobFightGame(player.getGame());
						Configuration gamedata = game.getConfiguration();
						int score = gamedata.getInt(game.getTeamNumber(player.getTeam()) + ".Score", 0);
						gamedata.setProperty(game.getTeamNumber(player.getTeam()) + ".Score", score++);
						pvper.sendMessage(util.killMsg(player));
						gamedata.save();
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
					}
				}
			}
		}
	}
}
