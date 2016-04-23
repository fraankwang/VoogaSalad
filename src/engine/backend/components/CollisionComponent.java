/**
 * @author cpd20
 */
package engine.backend.components;

public class CollisionComponent extends Component implements IComponent{
    
	private boolean isCollided;

    public CollisionComponent(){
       isCollided = false;
    }
    
    public CollisionComponent(CollisionComponent component) {
    	this.isCollided = component.isCollided;
    }
    
    public boolean isCollided() {
        return isCollided;
    }

    public void setCollided(boolean collided) {
        isCollided = collided;
    }

	@Override
	public void initWithParams(String[] params) {
        //default
        isCollided = false;
	}

}