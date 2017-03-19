package tk.zcraft.bans.cmds;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.zcraft.bans.objects.BannedUser;

public class BanCommand {
	
	public void ban(CommandSender sender, Command cmd, String label, String[] args){
		if(!sender.hasPermission("zban.ban")){
			sender.sendMessage("§cBrak uprawnien");
			return;
		}
		if(args.length == 0){
			sender.sendMessage("§cPoprawne uzycie: /zban (nick) (powod)");
			return;
		}
		if(args.length == 1){
			if(Bukkit.getPlayer(args[0]) != null){
				Player p = Bukkit.getPlayer(args[0]);
				BannedUser u = new BannedUser(p, (sender.getName().equalsIgnoreCase("CONSOLE") ? "konsola" : sender.getName()), "podstawowy");
				p.kickPlayer("DEFAULT");
				return;
			}else{
				OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
				BannedUser u = new BannedUser(p, (sender.getName().equalsIgnoreCase("CONSOLE") ? "konsola" : sender.getName()), "podstawowy");
				sender.sendMessage("§cZbanowano gracza offline!");
				return;
			}
		}
		String reason = "";
		StringBuilder sb = new StringBuilder(reason);
		for(int i=1;i<args.length;i++){
			sb.append(args[1]);
		}
		if(Bukkit.getPlayer(args[0]) != null){
			Player p = Bukkit.getPlayer(args[0]);
			BannedUser u = new BannedUser(p, (sender.getName().equalsIgnoreCase("CONSOLE") ? "konsola" : sender.getName()), reason);
			p.kickPlayer("BANMSG\n"+reason);
			return;
		}else{
			OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
			BannedUser u = new BannedUser(p, (sender.getName().equalsIgnoreCase("CONSOLE") ? "konsola" : sender.getName()), reason);
			sender.sendMessage("§cZbanowano gracza offline!");
			return;
		}
	}

}
