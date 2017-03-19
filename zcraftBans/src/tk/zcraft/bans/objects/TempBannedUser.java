package tk.zcraft.bans.objects;

import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import tk.zcraft.bans.utils.TempBannedUserUtils;

public class TempBannedUser {
	
	private UUID uuid;
	private String name;
	private String admin;
	private String reason;
	private long ubtime;
	
	public TempBannedUser(String uuid){
		this.uuid = UUID.fromString(uuid);
		TempBannedUserUtils.addBan(this);
	}
	
	public TempBannedUser(Player p, String admin, String reason, long ub){
		this.uuid = p.getUniqueId();
		this.admin = admin;
		this.name = p.getName();
		this.reason = reason;
		this.ubtime = ub;
		TempBannedUserUtils.addBan(this);
	}
	public TempBannedUser(OfflinePlayer p, String admin, String reason, long ub){
		this.uuid = p.getUniqueId();
		this.admin = admin;
		this.name = p.getName();
		this.reason = reason;
		this.ubtime = ub;
		TempBannedUserUtils.addBan(this);
	}
	public UUID getUuid() {
		return uuid;
	}
	public String getName() {
		return name;
	}
	public String getAdmin() {
		return admin;
	}
	public String getReason() {
		return reason;
	}
	public long getUbtime() {
		return ubtime;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setUbtime(long ubtime) {
		this.ubtime = ubtime;
	}
	
	
	public static TempBannedUser get(String name){
		for(TempBannedUser u : TempBannedUserUtils.getUsers()){
			if(u.getName().equals(name)) return u;
		}
		return null;
	}
	public static TempBannedUser get(UUID uuid){
		for(TempBannedUser u : TempBannedUserUtils.getUsers()){
			if(u.getUuid().equals(uuid)) return u;
		}
		return null;
	}

}
