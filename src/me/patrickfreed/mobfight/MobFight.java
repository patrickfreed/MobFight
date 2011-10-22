package me.patrickfreed.mobfight;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import me.patrickfreed.mobfight.Listeners.MobFightPlayerListener;
import me.patrickfreed.mobfight.Listeners.MobFightServerListener;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MobFight extends JavaPlugin{

	public static YamlConfiguration config;
	public boolean willDisable = false;
	public static List<String> list;
	public Util util = new Util();
	@Override
	public void onDisable() {
		System.out.println("[" + this.getDescription().getName() + "] Disabled!");
		util.save(Util.Arenas, "arenas");
	}

	@Override
	public void onEnable() {
		System.out.println("[" + this.getDescription().getName() + "] Enabled!");	
		Util.Arenas = Util.load("arenas");
/*		File conf = makeConfig(new File("plugins/MobFight", "config.yml"));
/
* 		if (conf.exists()) {
*
*			config = YamlConfiguration.loadConfiguration(new File("plugins/MobFight", "config.yml"));
*			System.out.println("[" + this.getDescription().getName() + "] Config loaded successfully!");
*		} else {
*			System.out.println("[" + this.getDescription().getName() + "] Error loading config, disabling...");
*			willDisable = true;
*		}	
*/		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, new MobFightPlayerListener(), Priority.Normal, this);	
		pm.registerEvent(Event.Type.PLUGIN_ENABLE, new MobFightServerListener(), Priority.Normal, this);
		this.getCommand("MobFight").setExecutor(new MobFightCommands());	

		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for (World world : Bukkit.getServer().getWorlds()){
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
		}, 80L, 80L);
		
		if(willDisable)
			getServer().getPluginManager().disablePlugin(this);
	}

	/**
	 * @author krinsdeath
	 * @param file
	 * @return File
	 */
	@SuppressWarnings("unused")
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
}