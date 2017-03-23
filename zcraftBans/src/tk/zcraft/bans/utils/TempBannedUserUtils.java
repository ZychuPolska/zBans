package tk.zcraft.bans.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tk.zcraft.bans.data.Data;
import tk.zcraft.bans.objects.TempBannedUser;

public class TempBannedUserUtils {
	
	
private static List<TempBannedUser> users = new ArrayList<TempBannedUser>();
	
	public static List<TempBannedUser> getUsers(){
		return new ArrayList<TempBannedUser>(users);
	}
	public static void addBan(TempBannedUser u){
		if(users.contains(u)) return;
		users.add(u);
	}
	public static void removeBan(TempBannedUser u){
		if(!users.contains(u)) return;
		users.remove(u);
	}
	public static void unBan(TempBannedUser u) throws SQLException{
		removeBan(u);
		Data.deleteFromBase(u);
		u.setAdmin(null);
	    u.setUbtime(0);
		u.setName(null);
		u.setReason(null);
		u.setUuid(null);
		
	}

}
