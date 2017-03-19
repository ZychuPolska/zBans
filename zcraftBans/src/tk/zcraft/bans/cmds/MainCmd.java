package tk.zcraft.bans.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCmd implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("zban")){
			new BanCommand().ban(sender, cmd, label, args);
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("zunban")){
			new UnBanCommand().unBan(sender, cmd, label, args);
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("zkick")){
			new KickCmd().kick(sender, cmd, label, args);
			return true;
		}if(cmd.getName().equalsIgnoreCase("ztemp")){
			new TempCommand().temp(sender, cmd, label, args);
		}
		
		return false;
	}
	
	

}
