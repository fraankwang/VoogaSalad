package engine.backend.systems;

import engine.backend.components.UserInputComponent;
import engine.backend.entities.IEntity;
import engine.controller.Engine2PlayerController;

import java.util.List;

/**
 * Created by colinduffy on 4/12/16.
 */
public class UserInputSystem extends Systemm implements ISystem{
    private Engine2PlayerController myFrontEndAccessController;
    private static final String COMPONENT_TAG = "UserInput";
    public UserInputSystem(Engine2PlayerController myFrontEndAccessController){
        this.myFrontEndAccessController = myFrontEndAccessController;
    }

    @Override
    public void update(List<IEntity> entities) {

        for (IEntity iEntity : entities) {
            if (iEntity.hasComponent(getComponentTagResources().getString(COMPONENT_TAG))) {
                checkInput(iEntity);
            }
        }
    }

    private void checkInput(IEntity iEntity) {
        if(myFrontEndAccessController.isMouseClickedEvent()) {
            updateMouseClickedData(iEntity, true);
        }else updateMouseClickedData(iEntity, false);
    }

    private void updateMouseClickedData(IEntity iEntity, boolean update){
        UserInputComponent toChange  = (UserInputComponent)iEntity.getComponent(getComponentTagResources().getString(COMPONENT_TAG));
        toChange.setClicked(update);
    }
}
