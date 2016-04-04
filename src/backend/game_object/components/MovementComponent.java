package backend.game_object.components;

public class MovementComponent extends Component{
	
	//private double myVelocity;
	//private double myAcceleration;
	private Vector myVelocityVector;
	private double myTheta;
	
	
	//this is the angle to rotate myTheta by
	private double myAngle;
	
	private boolean canMove;
	private boolean canRotate;
	
	public MovementComponent(double velocity, double angle) {
		// TODO Auto-generated constructor stub
		myVelocityVector = new Vector(velocity, 0);
		setAngle(angle);
		//myVelocity = velocity;
		//myAcceleration = acceleration;
	}
	
	public Vector getVelocityVector(){
		return myVelocityVector;
	}
	
	public double getTheta(){
		return myTheta;
	}

	public double getAngle() {
		return myAngle;
	}

	public void setAngle(double myAngle) {
		this.myAngle = myAngle;
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
}
