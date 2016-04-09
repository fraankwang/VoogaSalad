package backend.game_object.components;

public class Vector {
	
	private double myXComponent;
	private double myYComponent;
	
	public Vector(){
		myXComponent = 0;
		myYComponent = 0;
	}
	
	public Vector(double x, double y) {
		// TODO Auto-generated constructor stub
		myXComponent = x;
		myYComponent = y;
	}
	
	public Vector add(Vector v){
		return new Vector(myXComponent + v.getX(), myYComponent + v.getY());
	}
	
	public Vector scale(double s){
		return new Vector(myXComponent * s, myYComponent * s);
	}
	
	//calculate distance between this and vector v
	public double calculateDistance(Vector v){
		double xDistance = myXComponent - v.getX();
		double yDistance = myYComponent - v.getY();
		
		return Math.sqrt(xDistance*xDistance + yDistance*yDistance);
		
	}
	
	public double calculateMagnitude(){
		double val1 = myXComponent * myXComponent;
		double val2 = myYComponent * myYComponent;
		
		return Math.sqrt(val1 + val2);
	}
	
	public double getX(){
		return myXComponent;
	}
	public double getY(){
		return myYComponent;
	}
	

}
