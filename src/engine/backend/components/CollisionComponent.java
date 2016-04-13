/**
 * @author cpd20
 */
package engine.backend.components;

import java.util.List;

public class CollisionComponent extends Component implements IComponent{
    
	private boolean isCollided;

    public CollisionComponent(){

    }

    public boolean isCollided() {
        return isCollided;
    }

    public void setCollided(boolean collided) {
        isCollided = collided;
    }

    @Override
    public String getTag(){
        return "Collision";
    }

	@Override
	public void initWithParams(String[] params) {
        //default
        isCollided = false;
	}

}