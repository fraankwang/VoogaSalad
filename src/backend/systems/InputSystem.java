package backend.systems;

import backend.FrontEndAccessController;
import backend.game_object.components.InputComponent;
import backend.game_object.entities.IEntity;
import java.util.List;

/**
 * Created by colinduffy on 4/10/16.
 */
public class InputSystem extends Systemm implements ISystem {
    private FrontEndAccessController myFrontEndAccessController;
    private static final String COMPONENT_TAG = "Input";
    public InputSystem(FrontEndAccessController myFrontEndAccessController){
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
        InputComponent toChange  = (InputComponent)iEntity.getComponent(getComponentTagResources().getString(COMPONENT_TAG));
        toChange.setClicked(update);
    }
}

