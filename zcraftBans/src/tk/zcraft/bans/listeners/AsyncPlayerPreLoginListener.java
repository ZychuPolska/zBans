package tk.zcraft.bans.listeners;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import tk.zcraft.bans.objects.BannedUser;
import tk.zcraft.bans.objects.TempBannedUser;
import tk.zcraft.bans.utils.TempBannedUserUtils;

public class AsyncPlayerPreLoginListener implements Listener{
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void onLogin(AsyncPlayerPreLoginEvent e){
		BannedUser u = BannedUser.get(e.getUniqueId());
		TempBannedUser tu = TempBannedUser.get(e.getUniqueId());
		if(u != null){
			e.setKickMessage(u.getReason());
			e.setLoginResult(Result.KICK_BANNED);
		}
		if( tu != null){
			if(System.currentTimeMillis() <= tu.getUbtime()){
				Bukkit.broadcastMessage("time < getubtime");
				e.setKickMessage(tu.getReason());
				e.setLoginResult(Result.KICK_BANNED);
			}else{
				try {
					Bukkit.broadcastMessage("unban");
					TempBannedUserUtils.unBan(tu);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		}
	}

}
