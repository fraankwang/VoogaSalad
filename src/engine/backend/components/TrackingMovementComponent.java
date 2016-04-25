package engine.backend.components;

import engine.backend.entities.IEntity;
import engine.backend.utilities.ComponentTagResources;

public class TrackingMovementComponent extends MovementComponent{
	
	private IEntity myEntityToTrack;

	public TrackingMovementComponent() {
		// TODO Auto-generated constructor stub
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
	
	@Override
	public Vector getCurrentVelocityVector(){
		if(myEntityToTrack != null){
			updateCurrentVelocityVector();
		}
		return super.getCurrentVelocityVector();
	}
	
	private void updateCurrentVelocityVector(){
		Vector currentDirection = super.getCurrentVelocityVector();
		Vector targetPosVector = ((PositionComponent) myEntityToTrack.getComponent(ComponentTagResources.positionComponentTag)).getPositionVector();
		double xComp = targetPosVector.getX() - currentDirection.getX();
		double yComp = targetPosVector.getY() - currentDirection.getY();
		Vector updatedDirection = new Vector(xComp, yComp);
		super.setCurrentVelocityVector(updatedDirection);
	}


}
