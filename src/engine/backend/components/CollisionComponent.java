/**
 * @author cpd20
 */
package engine.backend.components;

public class CollisionComponent extends Component {
    
	private boolean isCollided;
<<<<<<< HEAD
    private double myX, myY, myWidth, myHeight;
    private static final String TAG = "Collision";

    /**
     * Collision Component stores the data required to determine if given entity is colliding with another entity
     * relevant information includes x & y coordinates, width and height.  The key data point is the boolean
     * isCollided, which stores whether or not the entity has collided.  It is default to false and updated to true
     * once the Collision system detects a collison
     * @param myX
     * @param myY
     * @param myWidth
     * @param myHeight
     */
    public CollisionComponent(double myX, double myY, double myWidth, double myHeight){
        this.myX = myX;
        this.myY = myY;
        this.myWidth = myWidth;
        this.myHeight = myHeight;
        //default
       isCollided = false;
    }


    public double getMyX() {
        return myX;
    }


    public double getMyY() {
        return myY;
    }


    public double getMyHeight() {
        return myHeight;
    }


    public double getMyWidth() {
        return myWidth;
    }

=======

    public CollisionComponent(){
       isCollided = false;
    }

>>>>>>> origin/authoring_backend
    public boolean isCollided() {
        return isCollided;
    }


    public void setCollided(boolean collided) {
        isCollided = collided;
    }

<<<<<<< HEAD
    @Override
    public String getTag(){
        return TAG;
    }

=======
>>>>>>> origin/engine_backend_systems_rk145
	@Override
	public String getComponentInfo() {
		return "IsCollided:" + isCollided;
	}

	@Override
	public void update(String dataName, String data) {
		if (dataName.equals("IsCollided")) {
			if (data.equals("True") || data.equals("true") || data.equals("1") || data.equals("Yes") || data.equals("yes")) {
				isCollided = true;
			} else {
				isCollided = false;
			}
		}
	}

}