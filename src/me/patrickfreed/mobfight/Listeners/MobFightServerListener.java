package me.patrickfreed.mobfight.Listeners;

import me.patrickfreed.mobfight.Util;

import org.bukkit.Bukkit;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;

import com.nijikokun.register.payment.Methods;

public class MobFightServerListener extends ServerListener{

	@Override
	public void onPluginEnable(PluginEnableEvent event){
		if(!Methods.hasMethod() && Methods.setMethod(Bukkit.getPluginManager())){
			Util.print("Linked with " + Methods.getMethod().getName());
		}
	}
}
