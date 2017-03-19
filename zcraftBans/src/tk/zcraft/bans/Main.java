package tk.zcraft.bans;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import tk.zcraft.bans.cmds.MainCmd;
import tk.zcraft.bans.data.Data;
import tk.zcraft.bans.data.FileManager;
import tk.zcraft.bans.listeners.AsyncPlayerPreLoginListener;

public class Main extends JavaPlugin{
	
	private static Main inst;
	
	@Override
	public void onEnable(){inst =this;
		try {
			FileManager.checkfiles();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		try {
			Data.load();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getCommand("zban").setExecutor(new MainCmd());
		getCommand("zunban").setExecutor(new MainCmd());
		getCommand("zkick").setExecutor(new MainCmd());
		getCommand("ztemp").setExecutor(new MainCmd());
		Bukkit.getPluginManager().registerEvents(new AsyncPlayerPreLoginListener(), this);
		
	}
	@Override
	public void onDisable(){
		try {
			Data.save();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Main getInst(){
		return inst;
	}

}
