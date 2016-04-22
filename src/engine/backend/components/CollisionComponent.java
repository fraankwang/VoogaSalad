/**
 * @author cpd20
 */
package engine.backend.components;

public class CollisionComponent extends Component {
    
	private boolean isCollided;

    public CollisionComponent(){
       isCollided = false;
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
	public String getComponentInfo() {
		return "IsCollided:" + isCollided;
	}

	@Override
	public void update(String dataName, String data) {
		if (dataName.equals("IsCollided")) {
			if (data.equals("True")) {
				isCollided = true;
			} else {
				isCollided = false;
			}
		}
	}

}