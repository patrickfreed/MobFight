package me.patrickfreed.mobfight.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
<<<<<<< HEAD
=======
import org.bukkit.block.Sign;
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.util.BlockVector;

import me.patrickfreed.mobfight.MobFightPlayer;
<<<<<<< HEAD
=======
import me.patrickfreed.mobfight.Utilities;
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a

public class MobFightPlayerListener extends PlayerListener {
	public static BlockVector block1;
	public static BlockVector block2;
	public static World world;
	public void onPlayerInteract(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
<<<<<<< HEAD
=======
		Utilities util = new Utilities();
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
		MobFightPlayer player = new MobFightPlayer(event.getPlayer());
		//is this action a right click action?
		switch (event.getAction()) {
		case RIGHT_CLICK_BLOCK: {
<<<<<<< HEAD
=======

>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
			if(player.getCraftPlayer().getItemInHand().getType() == Material.BOOK){
				if(player.hasPermission("mobfight.*")){
					block2 = new BlockVector(block.getX(), block.getY(), block.getZ());
					world = block.getWorld();
					player.sendMessage(ChatColor.YELLOW + "Second point set.");
					return;	
<<<<<<< HEAD
				}
			}
=======
			}
			}
			if (event.getClickedBlock().getType() == Material.SIGN_POST) {
				Sign sign = (Sign) event.getClickedBlock().getState();
				util.onRightClickSign(sign, player);
			}//sign post end
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
		}//case end
		case LEFT_CLICK_BLOCK: {
			if(player.getCraftPlayer().getItemInHand().getType() == Material.BOOK){
				if(player.hasPermission("mobfight.*")){
					block1 = new BlockVector(block.getX(), block.getY(), block.getZ());
					world = block.getWorld();
					player.sendMessage(ChatColor.YELLOW + "First point set.");
					return;
				}
			}
		}
		}//switch end
	}//method end
}//class end