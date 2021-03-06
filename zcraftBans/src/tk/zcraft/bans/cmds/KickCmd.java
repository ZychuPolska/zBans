package tk.zcraft.bans.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.zcraft.bans.data.CfgManager;

public class KickCmd {
	
	
	public void kick(CommandSender sender, Command cmd, String label, String[] args){
		if(!sender.hasPermission("zban.kick")){
			sender.sendMessage("Brak uprawnien");
			return;
		}
		if(args.length == 0){
			sender.sendMessage("Poprawne uzycie: /zkick (nick) (powod)");
			return;
		}
		if(args.length == 1){
			if(Bukkit.getPlayer(args[0]) == null){
				sender.sendMessage("Nie ma tekiego gracza");
				return;
			}
			Player p = Bukkit.getPlayer(args[0]);
			Bukkit.broadcastMessage(CfgManager.getKickbc(p, sender, null));
			p.kickPlayer(CfgManager.getKickmsg(p, sender, null));
			
		}else{
			if(Bukkit.getPlayer(args[0]) == null){
				sender.sendMessage("Nie ma tekiego gracza");
				return;
			}
			Player p = Bukkit.getPlayer(args[0]);
			String reason = "";
			StringBuilder sb = new StringBuilder(reason);
			for(int i = 1;i<args.length;i++){
				sb.append(args[i]).append(" ");
			}
			Bukkit.broadcastMessage(CfgManager.getKickbc(p, sender, sb.toString()));
			p.kickPlayer(CfgManager.getKickmsg(p, sender, sb.toString()));
		}
	}

}
