package engine.backend.systems;

import engine.backend.components.UserInputComponent;
import engine.backend.entities.IEntity;
import engine.controller.EngineController;

import java.util.List;

/**
 * Created by colinduffy on 4/10/16.
 */
public class MouseSystem extends Systemm implements ISystem {
    private EngineController myFrontEndAccessController;
    public MouseSystem(EngineController myFrontEndAccessController){
        this.myFrontEndAccessController = myFrontEndAccessController;
    }
    @Override
    public void update(List<IEntity> entities) {

            for(IEntity iEntity : entities){
                if(iEntity.hasComponent(getComponentTagResources().getString("Mouse"))){
                    if(false) {
                        updateMouseClickedComponent(iEntity, true);
                    }else updateMouseClickedComponent(iEntity, false);
                }
            }

    }

    private void updateMouseClickedComponent(IEntity iEntity, boolean update){
        UserInputComponent toChange  = (UserInputComponent) iEntity.getComponent(getComponentTagResources().getString("UserInput"));
        toChange.setClicked(update);
    }
}
