package tk.zcraft.bans.cmds;

import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import tk.zcraft.bans.objects.BannedUser;
import tk.zcraft.bans.utils.BannedUserUtils;

public class UnBanCommand {
	
	public void unBan(CommandSender sender, Command cmd, String label, String[] args){
		if(!sender.hasPermission("zban.unban")){
			sender.sendMessage("Brak uprawnien");
			return;
		}
		if(args.length != 1){
			sender.sendMessage("§cPoprawne uzycie: /zunban (nick)");
			return;
		}
		
		BannedUser u = BannedUser.get(args[0]);
		if(u==null){
			sender.sendMessage("§cTaki gracz nie jest zbanowany!");
			return;
		}
		try {
			BannedUserUtils.unBan(u);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		sender.sendMessage("§aOdbanowano gracza "+ args[0]);
	}

}
