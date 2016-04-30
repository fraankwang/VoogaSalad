package engine.backend.components;

import engine.backend.entities.IEntity;
import engine.backend.utilities.ComponentTagResources;

public class TrackingMovementComponent extends MovementComponent {

	private IEntity myEntityToTrack;
	private double mySpeed;
	private PositionComponent myCurrentPosition;

	public TrackingMovementComponent(TrackingMovementComponent component) {
		super(component);
	}

	// for demo purposes
	public TrackingMovementComponent(double xspeed, double yspeed) {
		super(xspeed, yspeed);
	}
	
	public IEntity getEntityToTrack() {
		return myEntityToTrack;
	}
/**
 * Sets which entity the component is going to track
 * @param myEntityToTrack
 */
	public void setEntityToTrack(IEntity myEntityToTrack) {
		this.myEntityToTrack = myEntityToTrack;
	}
/**
 * Sets speed
 * @param speed
 */
	public void setSpeed(double speed) {
		mySpeed = speed;
	}

/**
 * sets current position
 * @param position
 */
	public void setPosition(PositionComponent position) {
		this.myCurrentPosition = position;
	}

	public Vector getCurrentVelocityVector(){
		if(isEntityDisplayed()){ //check if entity has been removed from map
			updateCurrentVelocityVector();
		}
		return super.getCurrentVelocityVector();
	}
	
	private boolean isEntityDisplayed(){
		if(myEntityToTrack != null){
			return !((DisplayComponent) myEntityToTrack.getComponent(ComponentTagResources.displayComponentTag)).getDelete();
		}
		return false;
	}
	/**
	 * Updates the vector towards the tracked entity and scales it for speed
	 */
	private void updateCurrentVelocityVector(){
		Vector targetPosVector = ((PositionComponent) myEntityToTrack.getComponent(ComponentTagResources.positionComponentTag)).getPositionVector();
		double xComp = targetPosVector.getX() - myCurrentPosition.getX();
		double yComp = targetPosVector.getY() - myCurrentPosition.getY();
		Vector updatedDirection = new Vector(xComp, yComp);
		updatedDirection.normalize();
		updatedDirection.scale(mySpeed);
		super.setCurrentVelocityVector(updatedDirection);
	}

}
