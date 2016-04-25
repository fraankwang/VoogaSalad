package engine.backend.components;

import engine.backend.entities.IEntity;

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
		
	}


}
