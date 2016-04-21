package engine.backend.systems;

import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import engine.backend.components.MouseComponent;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.controller.EngineController;

/**
 * Created by colinduffy on 4/10/16.
 */
public class MouseSystem implements ISystem {
    private EngineController myFrontEndAccessController;
    public MouseSystem(EngineController myFrontEndAccessController){
        this.myFrontEndAccessController = myFrontEndAccessController;
    }
    @Override
    public void update(Level myLevel, InGameEntityFactory myEntityFactory, ResourceBundle myComponentTagResources) {
    		Collection<IEntity> entities = myLevel.getEntities().values();
            for(IEntity iEntity : entities){
                if(iEntity.hasComponent(myComponentTagResources.getString("Mouse"))){
                    if(false) {
                        updateMouseClickedComponent(iEntity, true,  myComponentTagResources);
                    }else updateMouseClickedComponent(iEntity, false,  myComponentTagResources);
                }
            }

    }

    private void updateMouseClickedComponent(IEntity iEntity, boolean update, ResourceBundle myComponentTagResources){
        MouseComponent toChange  = (MouseComponent)iEntity.getComponent(myComponentTagResources.getString("Mouse"));
        toChange.setClicked(update);
    }
}
