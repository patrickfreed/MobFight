package me.patrickfreed.mobfight;
<<<<<<< HEAD

=======
import me.patrickfreed.mobfight.Commands.Addleader;
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
import me.patrickfreed.mobfight.Commands.Creategame;
import me.patrickfreed.mobfight.Commands.Define;
import me.patrickfreed.mobfight.Commands.Delete;
import me.patrickfreed.mobfight.Commands.End;
import me.patrickfreed.mobfight.Commands.Join;
import me.patrickfreed.mobfight.Commands.Leave;
<<<<<<< HEAD
import me.patrickfreed.mobfight.Commands.List;
=======
import me.patrickfreed.mobfight.Commands.Removeleader;
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
import me.patrickfreed.mobfight.Commands.Removeplayer;
import me.patrickfreed.mobfight.Commands.Setspawn;
import me.patrickfreed.mobfight.Commands.Setwarp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MobFightCommands implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd,String commandlabel, String[] args) {

		if (cmd.getName().compareToIgnoreCase("Mobfight")==0 && sender instanceof Player) {
			if(args.length == 0){
				return false;
			}
			MobFightPlayer player = new MobFightPlayer((Player)sender);
			if (args[0].equalsIgnoreCase("setspawn")) {
				return Setspawn.run(args, player);
			}
			else if (args[0].equalsIgnoreCase("creategame")) {
				return Creategame.run(args, player);
			} 
			else if (args[0].equalsIgnoreCase("leave")){
				return Leave.run(args, player);
			}
<<<<<<< HEAD
=======
			else if (args[0].equalsIgnoreCase("addleader")){
				return Addleader.run(args, player);
			}
			else if(args[0].equalsIgnoreCase("removeleader")){
				return Removeleader.run(args, player);
			}
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
			else if(args[0].equalsIgnoreCase("join")){
				return Join.run(args, player);
			}
			else if (args[0].equalsIgnoreCase("end")){
				return End.run(args, player);
			}
			else if (args[0].equalsIgnoreCase("delete")){
				return Delete.run(args, player);
			}
			else if (args[0].equalsIgnoreCase("removeplayer")){
				return Removeplayer.run(args, player);
			}
			else if (args[0].equalsIgnoreCase("setwarp")){
				return Setwarp.run(args, player);
			}
			else if (args[0].equalsIgnoreCase("define")){
				return Define.run(args, player);
			}
<<<<<<< HEAD
			else if (args[0].equalsIgnoreCase("list")){
				return List.run(args, player);
			}
		}
		return false;
	}
}
=======
		}
		return false;
	}
}
>>>>>>> e9f7e1aed198fa61a637ef41a26d54400465817a
