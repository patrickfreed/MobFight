package me.patrickfreed.mobfight;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import me.patrickfreed.mobfight.Listeners.MobFightPlayerListener;

<<<<<<< HEAD
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
=======
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
import org.bukkit.event.*;

public class MobFight extends JavaPlugin{

<<<<<<< HEAD
	public static YamlConfiguration config;
	public boolean willDisable = false;
	public static List<String> list;
	public Util util = new Util();
=======
	public static Configuration config;
	public boolean willDisable = false;
	public static List<String> list;
	public Utilities util = new Utilities();
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
	@Override
	public void onDisable() {
		System.out.println("[" + this.getDescription().getName() + "] Disabled!");
	}

	@Override
	public void onEnable() {
<<<<<<< HEAD
		System.out.println("[" + this.getDescription().getName() + "] Enabled!");	
		File conf = makeConfig(new File(getDataFolder(), "config.yml"));
		if (conf.exists()) {
			config = YamlConfiguration.loadConfiguration(new File("plugins/MobFight", "config.yml"));
=======
		System.out.println("[" + this.getDescription().getName() + "] Enabled!");
		
		//make config.yml
		File conf = makeConfig(new File(getDataFolder(), "config.yml"));
		if (conf.exists()) {
			config = getConfiguration();
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
			System.out.println("[" + this.getDescription().getName() + "] Config loaded successfully!");
		} else {
			System.out.println("[" + this.getDescription().getName() + "] Error loading config, disabling...");
			willDisable = true;
		}	
		
<<<<<<< HEAD
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, new MobFightPlayerListener(), Priority.Normal, this);	
		this.getCommand("MobFight").setExecutor(new MobFightCommands());	
		final Server server = this.getServer();
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for (World world : server.getWorlds()){
					for (Entity entity : world.getEntities()){
						if ((entity instanceof Player)){
							MobFightPlayer player = new MobFightPlayer((Player)entity);
							if (player.isPlaying()){
								if(!player.isInArena()){
									player.kick();
								}
							}
						}
					}
				}
			}
		}, 120L, 120L);
		
=======
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, new MobFightPlayerListener(), Priority.Normal, this);	
		this.getCommand("MobFight").setExecutor(new MobFightCommands());	
		util.keepPlayersInArena(this);
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
		if(willDisable)
			getServer().getPluginManager().disablePlugin(this);
	}

	/**
	 * @author krinsdeath
	 * @param file
	 * @return File
	 */
	private File makeConfig(File file) {
		if (!file.exists()) {
			System.out.println("[" + this.getDescription().getName() + "] Generating config...");
			new File(file.getParent()).mkdirs();
			InputStream in = MobFight.class.getResourceAsStream("/resources/config.yml");
			if (in != null) {
				FileOutputStream out = null;
				try {
					out = new FileOutputStream(file);
					byte[] buffer = new byte[2048];
					int length = 0;
					while ((length = in.read(buffer)) > 0) {
						out.write(buffer, 0, length);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						in.close();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return file;
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
