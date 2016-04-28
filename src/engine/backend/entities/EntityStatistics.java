package engine.backend.entities;

import java.util.ArrayList;
import java.util.List;

public class EntityStatistics {
	
	List<String> myEntityStats;
	
	public EntityStatistics(){
		myEntityStats = new ArrayList<String>();
	}
	
	public void addStat(String stat){
		myEntityStats.add(stat);
	}
	
	public List<String> getList(){
		return myEntityStats;
	}
	
}
