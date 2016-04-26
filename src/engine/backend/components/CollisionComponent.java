/**
 * @author cpd20
 */
package engine.backend.components;

/**
 * Creates a collision component that can be added to an entity to allow for collision detection of that entity.
 * @author 
 *
 */
public class CollisionComponent extends Component{
    
	private boolean isCollided;

    public CollisionComponent(){
       isCollided = false;
    }
    
    /**
     * Initializes a collision component from an existing collision component.
     * @param component
     */
    public CollisionComponent(CollisionComponent component) {
    	this.isCollided = component.isCollided;
    }
    
    /**
     * 
     * @return The boolean for whether or not there has been a collision.
     */
    public boolean isCollided() {
        return isCollided;
    }

    /**
     * Sets the collision boolean to the boolean paramter collided.
     * @param collided
     */
    public void setCollided(boolean collided) {
        isCollided = collided;
    }

	@Override
	public void initWithParams(String[] params) {
        //default
        isCollided = false;
	}

}