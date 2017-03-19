package tk.zcraft.bans.objects;

import java.net.InetAddress;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import tk.zcraft.bans.utils.BannedUserUtils;

public class BannedUser {
	
	private UUID uuid;
	private String name;
	private String admin;
	private InetAddress ip;
	private String reason;
	
	public BannedUser(String uuid){
		this.uuid = UUID.fromString(uuid);
		BannedUserUtils.addBan(this);
	}
	public BannedUser(Player p, String admin, String reason){
		this.uuid = p.getUniqueId();
		this.name = p.getName();
		this.admin = admin;
		this.ip = p.getAddress().getAddress();
		this.reason = reason;
		BannedUserUtils.addBan(this);
	}
	public BannedUser(OfflinePlayer p, String admin, String reason){
		this.uuid = p.getUniqueId();
		this.admin = admin;
		this.name = p.getName();
		this.ip = null;
		this.reason = reason;
		BannedUserUtils.addBan(this);
	}
	public UUID getUuid() {
		return uuid;
	}
	public String getAdmin() {
		return admin;
	}
	public InetAddress getIp() {
		return ip;
	}
	public String getReason() {
		return reason;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public void setIp(InetAddress ip) {
		this.ip = ip;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public static BannedUser get(String name){
		for(BannedUser u : BannedUserUtils.getUsers()){
			if(u.getName().equals(name)){
				return u;
			}
			else{
				return null;
			}
		}
		return null;
	}
	public static BannedUser get(UUID uuid){
		for(BannedUser u : BannedUserUtils.getUsers()){
			if(u.getUuid().equals(uuid)){
				return u;
			}
		}
		return null;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
