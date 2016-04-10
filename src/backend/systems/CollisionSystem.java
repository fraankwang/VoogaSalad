package backend.systems;

import backend.game_object.components.CollisionComponent;
import backend.game_object.entities.IEntity;

import java.util.List;

/**
 * Created by colinduffy on 4/10/16.
 */
public class CollisionSystem extends Systemm implements ISystem {
    @Override
    /**
     * Implemented in O^2 for now.  Will eventually scale down to quadrant checks
     */
    public void update(List<IEntity> entities) {
        for(IEntity outEntity : entities){
            for(IEntity inEntity : entities){
                //default isCollided  = false.  Only update if there is a collision
                if(outEntity.hasComponent("Collision") && inEntity.hasComponent("Collision")){
                    if(checkIntersection((CollisionComponent)outEntity, (CollisionComponent)inEntity)){
                        updateIsCollided((CollisionComponent) outEntity, (CollisionComponent) inEntity);
                    }
                }
            }
                    /*
                    ** May or may not implement this using syntactic sugar.  Java 8 is annoying
                    entities.stream()
                    .map((y) -> updateIsCollided((CollisionComponent)outEntity, (CollisionComponent)y)
                    .filter((x) -> checkIntersection((CollisionComponent)outEntity, (CollisionComponent) x));
                    */
        }


    }

    private void updateIsCollided(CollisionComponent outEntity, CollisionComponent inEntity) {
        outEntity.setCollided(true);
        inEntity.setCollided(true);
    }

    private boolean checkIntersection(CollisionComponent componentOne, CollisionComponent componentTwo) {

        return  componentOne.getMyX() < componentTwo.getMyX() + componentTwo.getMyWidth() &&
                componentOne.getMyX() + componentOne.getMyWidth() > componentTwo.getMyX() &&
                componentOne.getMyY() < componentTwo.getMyY() + componentTwo.getMyHeight() &&
                componentOne.getMyY() + componentOne.getMyHeight() > componentTwo.getMyY();
    }
}
