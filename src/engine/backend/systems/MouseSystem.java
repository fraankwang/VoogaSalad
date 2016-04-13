package engine.backend.systems;

import java.util.List;

import engine.backend.components.MouseComponent;
import engine.backend.entities.IEntity;
import engine.controller.Engine2PlayerController;

/**
 * Created by colinduffy on 4/10/16.
 */
public class MouseSystem extends Systemm implements ISystem {
    private Engine2PlayerController myFrontEndAccessController;
    public MouseSystem(Engine2PlayerController myFrontEndAccessController){
        this.myFrontEndAccessController = myFrontEndAccessController;
    }
    @Override
    public void update(List<IEntity> entities) {

            for(IEntity iEntity : entities){
                if(iEntity.hasComponent(getComponentTagResources().getString("Mouse"))){
                    if(myFrontEndAccessController.isMouseClickedEvent()) {
                        updateMouseClickedComponent(iEntity, true);
                    }else updateMouseClickedComponent(iEntity, false);
                }
            }

    }

    private void updateMouseClickedComponent(IEntity iEntity, boolean update){
        MouseComponent toChange  = (MouseComponent)iEntity.getComponent(getComponentTagResources().getString("Mouse"));
        toChange.setClicked(update);
    }
}
