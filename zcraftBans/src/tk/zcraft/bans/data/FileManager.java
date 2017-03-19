package tk.zcraft.bans.data;

import java.io.File;
import java.io.IOException;

import tk.zcraft.bans.Main;

public class FileManager {
	
	private static File main = Main.getInst().getDataFolder();
	private static File cfg = new File(main, "config.yml");
	private static File database = new File(main, "banned.db");
	
	
	public static void checkfiles() throws IOException{
		if(!main.exists()) main.mkdir();
		if(!database.exists()){
			database.createNewFile();
		}
		//if(!cfg.exists()) Main.getInst().saveDefaultConfig();
	}
	
	public static File getMainFolder(){
		return main;
	}

}
