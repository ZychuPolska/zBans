package tk.zcraft.bans.data;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tk.zcraft.bans.objects.BannedUser;
import tk.zcraft.bans.objects.TempBannedUser;
import tk.zcraft.bans.utils.BannedUserUtils;
import tk.zcraft.bans.utils.TempBannedUserUtils;

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
		stm.executeUpdate("CREATE TABLE IF NOT EXISTS 'users' (uuid varchar(255) not null, admin varchar(255) not null, reason varchar(255) not null, ip varchar(255), name varchar(255) not null, primary key(uuid));");
		stm.executeUpdate("CREATE TABLE IF NOT EXISTS 'tempusers' (uuid varchar(255) not null, admin varchar(255) not null, reason varchar(255) not null, ubtime long not null, name varchar(255) not null, primary key(uuid));");
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
	
	public static void loadBanned() throws SQLException{
		openConnection();
		ResultSet rs = stm.executeQuery("Select * FROM 'users'");
		while(rs.next()){
			BannedUser u = new BannedUser(rs.getString("uuid"));
			u.setAdmin(rs.getString("admin"));
			try{
				u.setIp(InetAddress.getByName(rs.getString("ip")));
			}catch(UnknownHostException e){
				
			}
			u.setReason(rs.getString("reason"));
			u.setName(rs.getString("name"));
			
		}
		ResultSet rst = stm.executeQuery("Select * FROM 'users'");
		while(rst.next()){
			TempBannedUser u = new TempBannedUser(rst.getString("uuid"));
			u.setAdmin(rst.getString("admin"));
			u.setReason(rst.getString("reason"));
			u.setName(rst.getString("name"));
			u.setUbtime(rst.getLong("ubtime"));
			
		}
		closeConnection();
	}
	public static void saveBanned() throws SQLException{
		
		openConnection();
		for(BannedUser u : BannedUserUtils.getUsers()){
			//String toexecute = "INSERT OR IGNORE INTO users (uuid,admin,reason,ip,name) VALUES ('smieszneuuid','maciek','jakis tam powod','null','zyzu')";
			//stm.executeUpdate(toexecute);
			stm.executeUpdate("INSERT OR IGNORE INTO users VALUES ('"+u.getUuid().toString()+"', '"+u.getAdmin()+"', '"+u.getReason()+"', '"+(u.getIp() == null ? null : u.getIp().toString())+"', '"+u.getName()+"');");
			stm.executeUpdate("UPDATE users SET uuid='"+u.getUuid().toString()+"', admin='"+u.getAdmin()+"', reason='"+u.getReason()+"', ip='"+(u.getIp() == null ? null : u.getIp().toString()+"', name='"+u.getName())+
					"' WHERE uuid='" + u.getUuid().toString()+"';");
		}
		for(TempBannedUser u : TempBannedUserUtils.getUsers()){
			stm.executeUpdate("INSERT OR IGNORE INTO users VALUES ('"+u.getUuid().toString()+"', '"+u.getAdmin()+"', '"+u.getReason()+"', '"+u.getUbtime()+"', '"+u.getName()+"');");
			stm.executeUpdate("UPDATE users SET uuid='"+u.getUuid().toString()+"', admin='"+u.getAdmin()+"', reason='"+u.getReason()+"', ubtime='"+u.getUbtime()+"'name='"+u.getName()+
					"' WHERE uuid='" + u.getUuid().toString()+"';");
		}
		//CREATE TABLE IF NOT EXISTS 'tempusers' (uuid varchar(255) not null, admin varchar(255) not null, reason varchar(255) not null, ubtime long not null, name varchar(255) not null, primary key(uuid));
		
		
		closeConnection();
	}
	public static void deleteFromBase(BannedUser u) throws SQLException{
		openConnection();
		stm.executeUpdate("DELETE FROM users WHERE uuid='"+u.getUuid().toString()+"';");
		closeConnection();
	}
	public static void deleteFromBase(TempBannedUser u) throws SQLException{
		openConnection();
		stm.executeUpdate("DELETE FROM tempusers WHERE uuid='"+u.getUuid().toString()+"';");
		closeConnection();
	}

}
