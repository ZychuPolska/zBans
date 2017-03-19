package tk.zcraft.bans.utils;

public class TimeUrils {
	
	public static long parse(String s){
		long l = System.currentTimeMillis();
		try{
			String[] time = s.split(":");
			int i = Integer.parseInt(time[0]);
			if(i<=0){
				System.out.println("[ZBANS] Variable cannot be smaller than 0!");
				return -1;
			}
			String rest = time[1];
			if(rest.equalsIgnoreCase("s")){
				l += i*1000;
			}
			else if(rest.equalsIgnoreCase("m")){
				l += i*1000*60;
			}
			else if(rest.equalsIgnoreCase("h")){
				l += i*1000*60*60;
			}
			else if(rest.equalsIgnoreCase("d")){
				l += i*1000*60*60*24;
			}
			else if(rest.equalsIgnoreCase("msc")){
				l += i*1000*60*60*24*30;
			}
			else if(rest.equalsIgnoreCase("y")){
				l += i*1000*60*60*24*30*365;
			}
			return l;
		}catch(Exception e){
			System.out.println("[ZBANS] Cannot parse time from string!");
		}
		return -1;
	}

}
