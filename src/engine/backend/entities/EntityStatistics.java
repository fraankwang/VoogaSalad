package engine.backend.entities;

import java.util.HashMap;
import java.util.Map;

public class EntityStatistics {
	
	Map<String, String> myStatsMap;
	
	public EntityStatistics(){
		myStatsMap = new HashMap<String, String>();
	}
	
	public void addStat(String stat){
		String[] arr = stat.split(",");
		for(String str : arr){
			String[] newArr = str.split(":");
			myStatsMap.put(newArr[0], newArr[1]);
 		}
	}
	
	public Map<String, String> getStatsMap(){
		return myStatsMap;
	}
	
	public List<String> getList(){
		return myEntityStats;
	}
	
}
