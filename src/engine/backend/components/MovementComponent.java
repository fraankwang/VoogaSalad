package engine.backend.components;

/**
 * 
 * @author raghavkedia
 *
 */

public class MovementComponent extends Component implements IComponent{
	
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
	
	@Override
	public void initWithParams(String[] params) {
		//0 is velocity, 1 is theta, 2 is omega
		myCurrentVelocityVector = new Vector(Double.parseDouble(params[0]), 0);
		myDefaultVelocityVector = new Vector(Double.parseDouble(params[0]), 0);
		myTheta = Double.parseDouble(params[1]);
		myCurrentOmega = Double.parseDouble(params[2]);
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
	public String getValue() {
		return myVelocity + "";
	}
}
