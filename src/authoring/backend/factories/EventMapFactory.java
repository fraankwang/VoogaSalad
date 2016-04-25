package authoring.backend.factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.backend.rules.EntityAction;

public class EventMapFactory {
	private Map<String, List<EntityAction>> myEventMap;
	
	public EventMapFactory() {
		myEventMap = new HashMap<String, List<EntityAction>>();
	}
	
	public void addToMap(String id, List<String[]> actions){
		List<EntityAction> actionsArray = new ArrayList<EntityAction>();
		for(String[] action : actions){
			EntityAction toAdd = new EntityAction(action[0], action[1], action[2], action[3]);
			actionsArray.add(toAdd);
		}
		myEventMap.put(id, actionsArray);
	}
	
	public Map<String, List<EntityAction>> getEventMap(){
		return myEventMap;
	}
	
	

}
