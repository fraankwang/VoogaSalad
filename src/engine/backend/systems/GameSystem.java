package engine.backend.systems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import engine.backend.entities.IEntity;
import engine.backend.systems.Events.IEvent;


public abstract class GameSystem extends Observable implements ISystem{
	
	public void addToEventMap(Map<String, Set<Integer>> myEventMap, IEvent event, Collection<IEntity> entities) {
		Set<Integer> myIDs = new HashSet<Integer>();
		entities.forEach(e -> myIDs.add(e.getID()));
		if(myEventMap.containsKey(event.getEventID())){
			myEventMap.get(event.getEventID()).addAll(myIDs);
		}
		else{
			myEventMap.put(event.getEventID(), myIDs);
		}
	}

}
