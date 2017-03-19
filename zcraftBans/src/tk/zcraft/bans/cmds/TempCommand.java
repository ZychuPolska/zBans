package tk.zcraft.bans.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.zcraft.bans.objects.TempBannedUser;
import tk.zcraft.bans.utils.TimeUrils;

public class TempCommand {
	
	public void temp(CommandSender sender, Command cmd, String label, String[] args){
		if(!sender.hasPermission("zban.temp")){
			sender.sendMessage("no perm");
			return;
		}
		if(args.length==0 || args.length == 1){
			sender.sendMessage("/ztemp (nick) (czas) (powod)");
			return;
		}
		if(Bukkit.getPlayer(args[0]) == null){
			sender.sendMessage("nie ma takiego gracza!");
			return;
		}
		Player p = Bukkit.getPlayer(args[0]);
		long l = TimeUrils.parse(args[1]);
		if(args.length == 2){
			if(l==-1){
				sender.sendMessage("zla data");
				return;
			}
			new TempBannedUser(p, sender.getName(), "podstawowy", l);
			sender.sendMessage("Zbanowano gracza");
			Bukkit.broadcastMessage("TIMEEEEEEE" + String.valueOf(l) + "      "+System.currentTimeMillis());
			
		}else{
			if(l==-1){
				sender.sendMessage("zla data");
				return;
			}
			String reason = "";
			StringBuilder sb = new StringBuilder(reason);
			for(int i = 2; i<args.length; i++){
				sb.append(args[i]).append(" ");
			}
			new TempBannedUser(p, sender.getName(), sb.toString(), l);
			sender.sendMessage("Zbanowano gracza");
			Bukkit.broadcastMessage("TIMEEEEEEE" + String.valueOf(l) + "      "+System.currentTimeMillis());
			p.kickPlayer("[TEMP] \n"+ sb.toString());
		}
	}

}
