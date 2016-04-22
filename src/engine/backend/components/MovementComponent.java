package engine.backend.components;

/**
 * 
 * @author raghavkedia
 *
 */

public class MovementComponent extends Component {
	
	private double myVelocity;
	//private double myAcceleration;
	private Vector myCurrentVelocityVector;
	private Vector myDefaultVelocityVector;
	private double myTheta;
	
	//figure out direction of velocity;
	
	//this is the angle to rotate myTheta by
	private double myCurrentOmega;
	private double myInitialOmega;
	
	private boolean canMove;
	private boolean canRotate;
	
	public MovementComponent() {
	}
	
	//for demo purposes
	public MovementComponent(double xspeed, double yspeed){
		myCurrentVelocityVector = new Vector(xspeed, yspeed);
	}
	
	public Vector getCurrentVelocityVector(){
		return myCurrentVelocityVector;
	}
	public void setCurrentVelocityVector(Vector vel){
		myCurrentVelocityVector = vel;
	}
	public void setDefaultVelocityVector(Vector vel){
		myDefaultVelocityVector = vel;
	}
	public double getTheta(){
		return myTheta;
	}
	
	public void setSpeed(String deltaSpeed){
		double delta = Double.parseDouble(deltaSpeed);
		myCurrentVelocityVector = myCurrentVelocityVector.scale(delta);
	}
	
	public void setTheta(double theta){
		myTheta = theta;
	}
	
	public double getCurrentOmega() {
		return myCurrentOmega;
	}

	public void setOmega(double omega) {
		this.myCurrentOmega = omega;
	}
	
	public boolean canMove(){
		return canMove;
	}
	
	public boolean canRotate(){
		return canRotate;
	}
	
	public void setCanMove(boolean bool){
		canMove = bool;
	}
	public void setCanRotate(boolean bool){
		canRotate = bool;
	}
	
	public double getVelocity() {
		return myVelocity;
	}

	@Override
	public String getComponentInfo() {
		return "MyVelocity:" + myVelocity + "," + "CanMove:" + canMove + "," + "CanRotate:" + canRotate;
	}

	@Override
	public void update(String dataName, String data) {
		switch (dataName) {
		
		case "MyVelocity":
			this.myVelocity = Double.parseDouble(data);
			return;
		case "CanMove":
			if (data.equals("True")) {
				this.canMove = true;
				return;
			} else {
				this.canMove = false;
				return;
			}
		case "CanRotate":
			if (data.equals("True")) {
				this.canRotate = true;
				return;
			} else {
				this.canRotate = false;
				return;
			}
		}
	}
}
