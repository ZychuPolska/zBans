package tk.zcraft.bans.data;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.zcraft.bans.Main;
import tk.zcraft.bans.objects.BannedUser;
import tk.zcraft.bans.objects.TempBannedUser;

public class CfgManager {
	
	private static String banbc = Main.getInst().getConfig().getString("ban-bc-msg");
	private static String bankick = Main.getInst().getConfig().getString("ban-kick-msg");
	private static String kickbc = Main.getInst().getConfig().getString("kick-bc-msg");
	private static String kickmsg = Main.getInst().getConfig().getString("kick-msg");
	
	
	public static String getBanbc(BannedUser u) {
		banbc = banbc.replace("{PLAYER}", u.getName()).replace("{ADMIN}", u.getAdmin()).replace("{REASON}", u.getReason());
		
		return banbc;
	}
	
	
	public static String getBankick(BannedUser u) {
		bankick = bankick.replace("{PLAYER}", u.getName()).replace("{ADMIN}", u.getAdmin()).replace("{REASON}", u.getReason());
		return bankick;
	
	}
	
	public static String getKickbc(Player p, CommandSender sender, String reason) {
		kickbc = kickbc.replace("{PLAYER}", p.getName()).replace("{ADMIN}", sender.getName()).replace("{REASON}", reason==null ? "podstawowy" : reason);
		return kickbc;
	
	}
	
	public static String getKickmsg(Player p, CommandSender sender, String reason) {
		kickmsg = kickmsg.replace("{PLAYER}", p.getName()).replace("{ADMIN}", sender.getName()).replace("{REASON}", reason==null ? "podstawowy" : reason);
		return kickmsg;
	}
	public static String getBanbc(TempBannedUser u) {
		banbc = banbc.replace("{PLAYER}", u.getName()).replace("{ADMIN}", u.getAdmin()).replace("{REASON}", u.getReason());
		return banbc;
	
	
	}
	
	
	public static String getBankick(TempBannedUser u) {
		bankick = bankick.replace("{PLAYER}", u.getName()).replace("{ADMIN}", u.getAdmin()).replace("{REASON}", u.getReason());
		return bankick;
	
	}
	

	
	
	

}
