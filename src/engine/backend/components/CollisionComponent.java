/**
 * @author cpd20
 */
package engine.backend.components;

/**
 * Creates a collision component that can be added to an entity to allow for
 * collision detection of that entity.
 * 
 * @author
 *
 */

public class CollisionComponent extends Component {

	private boolean isCollided;

	public CollisionComponent() {
		isCollided = false;
	}

	/**
	 * Initializes a collision component from an existing collision component.
	 * 
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

	public void setCollided(boolean collided) {
		isCollided = collided;
	}

	@Override
	/**
	 * Returns the boolean with its tag
	 */
	public String getComponentInfo() {
		return "IsCollided:" + isCollided;
	}

	@Override
	/**
	 * Updates the value of the boolean
	 */
	public void update(String dataName, String data) {
		if (dataName.equals("IsCollided")) {
			if (data.equalsIgnoreCase("true") || data.equals("1") || data.equalsIgnoreCase("yes")) {

				isCollided = true;
			} else {
				isCollided = false;
			}
		}
	}

}