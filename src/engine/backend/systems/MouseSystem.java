package engine.backend.systems;

<<<<<<< HEAD
import engine.backend.components.UserInputComponent;
=======
import java.util.List;
import java.util.ResourceBundle;

import engine.backend.components.MouseComponent;
>>>>>>> origin/authoring_backend
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.controller.EngineController;

import java.util.List;

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
    		List<IEntity> entities = myLevel.getEntities();
            for(IEntity iEntity : entities){
                if(iEntity.hasComponent(myComponentTagResources.getString("Mouse"))){
                    if(false) {
                        updateMouseClickedComponent(iEntity, true,  myComponentTagResources);
                    }else updateMouseClickedComponent(iEntity, false,  myComponentTagResources);
                }
            }

    }

<<<<<<< HEAD
    private void updateMouseClickedComponent(IEntity iEntity, boolean update){
        UserInputComponent toChange  = (UserInputComponent) iEntity.getComponent(getComponentTagResources().getString("UserInput"));
=======
    private void updateMouseClickedComponent(IEntity iEntity, boolean update, ResourceBundle myComponentTagResources){
        MouseComponent toChange  = (MouseComponent)iEntity.getComponent(myComponentTagResources.getString("Mouse"));
>>>>>>> origin/authoring_backend
        toChange.setClicked(update);
    }
}
