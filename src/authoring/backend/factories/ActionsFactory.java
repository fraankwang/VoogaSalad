package authoring.backend.factories;

import java.util.List;

import engine.backend.rules.EntityAction;
import engine.backend.rules.IAction;
import engine.backend.rules.LevelAction;

public class ActionsFactory {

	public ActionsFactory() {
		
	}
	
	public IAction createAction(List<String> info) {
		if(info.size() > 2){
			return createEntityAction(info);
		}
		return createLevelAction(info);
	}
	
	private LevelAction createLevelAction(List<String> info){
		return new LevelAction(info.get(0), info.get(1));
	}
	
	private EntityAction createEntityAction(List<String> info){
		return new EntityAction(info.get(0), info.get(1), info.get(2), info.get(3));
	}

}
