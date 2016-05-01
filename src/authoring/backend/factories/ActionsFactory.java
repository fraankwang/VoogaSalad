package authoring.backend.factories;

import java.util.List;

import engine.backend.rules.EntityAction;
import engine.backend.rules.IAction;
import engine.backend.rules.LevelAction;

public class ActionsFactory {

	public ActionsFactory() {

	}

	public IAction createAction(List<String> info) {
		if (info.get(0).equals("Entity")) {
			return createEntityAction(info);
		}
		return createLevelAction(info);
	}

	private LevelAction createLevelAction(List<String> info) {
		return new LevelAction(info.get(1), info.get(2));
	}

	private EntityAction createEntityAction(List<String> info) {
		return new EntityAction(info.get(1), info.get(2), info.get(3), info.get(4));
	}

}
