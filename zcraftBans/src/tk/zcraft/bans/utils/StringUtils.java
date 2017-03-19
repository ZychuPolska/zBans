package tk.zcraft.bans.utils;

import org.bukkit.ChatColor;

public class StringUtils {
	
	public static String fixColor(String s){
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	
}
