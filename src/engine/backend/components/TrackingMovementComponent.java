package engine.backend.components;

import engine.backend.entities.IEntity;
import engine.backend.utilities.ComponentTagResources;

public class TrackingMovementComponent extends MovementComponent{
	
	private IEntity myEntityToTrack;
	private double mySpeed;

	public TrackingMovementComponent(MovementComponent component) {
		super(component);
	}
	
	//for demo purposes
	public TrackingMovementComponent(double xspeed, double yspeed){
		super(xspeed, yspeed);
	}
	
	public IEntity getEntityToTrack() {
		return myEntityToTrack;
	}

	public void setEntityToTrack(IEntity myEntityToTrack) {
		this.myEntityToTrack = myEntityToTrack;
	}
	 
	public void setSpeed(double speed){
		 mySpeed = speed;
	 }
	
	@Override
	public Vector getCurrentVelocityVector(){
		if(myEntityToTrack != null){ //check if entity has been removed from map
			updateCurrentVelocityVector();
		}
		return super.getCurrentVelocityVector();
	}
	
	private void updateCurrentVelocityVector(){
		Vector targetPosVector = ((PositionComponent) myEntityToTrack.getComponent(ComponentTagResources.positionComponentTag)).getPositionVector();
		Vector updatedDirection = new Vector(targetPosVector.getX(), targetPosVector.getX());
		updatedDirection = updatedDirection.normalize();
		updatedDirection = updatedDirection.scale(mySpeed);
		super.setCurrentVelocityVector(updatedDirection);
	}


}
