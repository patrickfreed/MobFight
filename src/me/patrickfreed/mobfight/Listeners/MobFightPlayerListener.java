package me.patrickfreed.mobfight.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.util.BlockVector;

import me.patrickfreed.mobfight.MobFightPlayer;
import me.patrickfreed.mobfight.Utilities;

public class MobFightPlayerListener extends PlayerListener {
	public static BlockVector block1;
	public static BlockVector block2;
	public static World world;
	public void onPlayerInteract(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		Utilities util = new Utilities();
		MobFightPlayer player = new MobFightPlayer(event.getPlayer());
		//is this action a right click action?
		switch (event.getAction()) {
		case RIGHT_CLICK_BLOCK: {

			if(player.getCraftPlayer().getItemInHand().getType() == Material.BOOK){
				if(player.hasPermission("mobfight.*")){
					block2 = new BlockVector(block.getX(), block.getY(), block.getZ());
					world = block.getWorld();
					player.sendMessage(ChatColor.YELLOW + "Second point set.");
					return;	
			}
			}
			if (event.getClickedBlock().getType() == Material.SIGN_POST) {
				Sign sign = (Sign) event.getClickedBlock().getState();
				util.onRightClickSign(sign, player);
			}//sign post end
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