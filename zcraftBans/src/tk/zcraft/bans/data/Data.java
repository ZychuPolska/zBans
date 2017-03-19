package tk.zcraft.bans.data;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tk.zcraft.bans.objects.BannedUser;
import tk.zcraft.bans.utils.BannedUserUtils;

public class Data {
	
	private static Connection conn;
	private static Statement stm;
	
	
	private static void openConnection() throws SQLException{
		if(conn != null && !conn.isClosed()) return; 
		conn = DriverManager.getConnection("jdbc:sqlite:"+FileManager.getMainFolder()+"\\banned.db");
		stm = conn.createStatement();
	}
	
	private static void closeConnection() throws SQLException{
		if(conn == null || (conn != null && conn.isClosed())) return;
		conn.close();
	}
	private static void checkTables() throws SQLException{
		openConnection();
		stm.executeUpdate("CREATE TABLE IF NOT EXISTS 'users' (uuid varchar(128) not null, admin varchar(20) not null, reason varchar(100), ip varchar(50), name varchar(20), primary key(uuid));");
		closeConnection();
	}
	
	
	public static void load() throws ClassNotFoundException, UnknownHostException, SQLException{
		Class.forName("org.sqlite.JDBC");
		checkTables();
		loadBanned();
	}
	public static void save() throws SQLException{
		
		checkTables();
		saveBanned();
	}
	
	public static void loadBanned() throws SQLException, UnknownHostException{
		openConnection();
		ResultSet rs = stm.executeQuery("Select * FROM 'users'");
		while(rs.next()){
			BannedUser u = new BannedUser(rs.getString("uuid"));
			u.setAdmin(rs.getString("admin"));
			if(!rs.getString("ip").equalsIgnoreCase("null")){
				u.setIp(InetAddress.getByName(rs.getString("ip")));
			}else{
				u.setIp(null);
			}
			u.setReason(rs.getString("reason"));
			u.setName(rs.getString("name"));
			
		}
		closeConnection();
	}
	public static void saveBanned() throws SQLException{
		
		openConnection();
		for(BannedUser u : BannedUserUtils.getUsers()){
			
			stm.executeUpdate("INSERT OR IGNORE INTO users VALUES ('"+u.getUuid().toString()+"', "+u.getAdmin()+", "+u.getReason()+", "+(u.getIp() == null ? null : u.getIp().toString())+", "+u.getName()+");");
			stm.executeUpdate("UPDATE users SET uuid="+u.getUuid().toString()+", admin="+u.getAdmin()+", reason="+u.getReason()+", ip="+(u.getIp() == null ? null : u.getIp().toString()+", name="+u.getName())+
					"WHERE uuid=" + u.getUuid().toString()+";");
		}
		
		
		closeConnection();
	}

}
