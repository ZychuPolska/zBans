package tk.zcraft.bans.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tk.zcraft.bans.data.Data;
import tk.zcraft.bans.objects.BannedUser;

public class BannedUserUtils {
	
	private static List<BannedUser> users = new ArrayList<BannedUser>();
	
	public static List<BannedUser> getUsers(){
		return new ArrayList<BannedUser>(users);
	}
	public static void addBan(BannedUser u){
		if(users.contains(u)) return;
		users.add(u);
	}
	public static void removeBan(BannedUser u){
		if(!users.contains(u)) return;
		users.remove(u);
	}
	public static void unBan(BannedUser u) throws SQLException{
		removeBan(u);
		Data.deleteFromBase(u);
		u.setAdmin(null);
		u.setIp(null);
		u.setName(null);
		u.setReason(null);
		u.setUuid(null);
		
	}

}
