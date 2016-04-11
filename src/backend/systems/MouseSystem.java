package backend.systems;

import backend.FrontEndAccessController;
import backend.game_object.entities.IEntity;

import java.util.List;

/**
 * Created by colinduffy on 4/10/16.
 */
public class MouseSystem extends Systemm implements ISystem {
    private FrontEndAccessController myFrontEndAccessController;
    public MouseSystem(FrontEndAccessController myFrontEndAccessController){
        this.myFrontEndAccessController = myFrontEndAccessController;
    }
    @Override
    public void update(List<IEntity> entities) {

    }
}
