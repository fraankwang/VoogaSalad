package engine.backend.components;

public class Vector {
	
	private double myXComponent;
	private double myYComponent;
	
	public Vector(){
		myXComponent = 0;
		myYComponent = 0;
	}
	
	public Vector(double x, double y) {
		myXComponent = x;
		myYComponent = y;
	}
	
	public Vector add(Vector v){
		return new Vector(myXComponent + v.getX(), myYComponent + v.getY());
	}
	
	public Vector(Vector v){
		myXComponent = v.getX();
		myYComponent = v.getY();
	}
	
	public void scale(double s){
		myXComponent = myXComponent * s;
		myYComponent = myYComponent * s;
	}
	
	public Vector scaleVect(double s){
		return new Vector(myXComponent * s,myYComponent * s );
	}
	
	//calculate distance between this and vector v
	public double calculateDistance(Vector v){
		double xDistance = myXComponent - v.getX();
		double yDistance = myYComponent - v.getY();
		
		return Math.sqrt(xDistance*xDistance + yDistance*yDistance);
		
	}
	
	public double calculateDirection(Vector v){
		double val1 = myYComponent - v.getY();
		double val2 = myXComponent - v.getX();
		
		return Math.atan(val1 / val2);
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
	
	public void normalize(){
		double length = calculateMagnitude();
		myXComponent = myXComponent / length;
		myYComponent = myYComponent / length;
	}
	

}
