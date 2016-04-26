package engine.backend.components;

import engine.backend.entities.IEntity;
import engine.backend.utilities.ComponentTagResources;

public class TrackingMovementComponent extends MovementComponent{
	
	private IEntity myEntityToTrack;
	private double mySpeed;
	private PositionComponent myCurrentPosition;

	public TrackingMovementComponent(TrackingMovementComponent component) {
		super(component);
	}
	
	public TrackingMovementComponent(){
	}
	
	//for demo purposes
	public TrackingMovementComponent(double xspeed, double yspeed){
		super(xspeed, yspeed);
	}
	
	public IEntity getEntityToTrack() {
		return myEntityToTrack;
	}

	public void setEntityToTrack(IEntity myEntityToTrack){ 
		this.myEntityToTrack = myEntityToTrack;
	}
	 
	public void setSpeed(double speed){
		 mySpeed = speed;
	 }
	
	@Override
	public String toString() {
		return this.getTag() + " with tracked entity id: " + this.myEntityToTrack.getID() + " with speed: " + this.mySpeed;
	}
	
	public void setPosition(PositionComponent position){
		this.myCurrentPosition = position;
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
		double xComp = targetPosVector.getX() - myCurrentPosition.getX();
		double yComp = targetPosVector.getY() - myCurrentPosition.getY();
		Vector updatedDirection = new Vector(xComp, yComp);
		updatedDirection = updatedDirection.normalize();
		updatedDirection = updatedDirection.scale(mySpeed);
		super.setCurrentVelocityVector(updatedDirection);
	}

}
