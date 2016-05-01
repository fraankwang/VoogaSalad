package engine.backend.game_object;

import engine.backend.rules.IAction;

public interface IModifiable {

	public void applyAction(IAction action);

}
