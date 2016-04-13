package backend.systems;

import backend.FrontEndAccessController;
import backend.game_object.entities.IEntity;

import java.util.List;

/**
 * Created by colinduffy on 4/10/16.
 */
public class KeyBoardInputSystem extends Systemm implements ISystem {
    private FrontEndAccessController myFrontEndAccessController;
	public KeyBoardInputSystem(FrontEndAccessController myFrontEndAccessController) {
		this.myFrontEndAccessController = myFrontEndAccessController;
	}

	@Override
	public void update(List<IEntity> entities) {


	}
}
