package engine.backend.map;

import engine.backend.components.Vector;

public class BezierCurve implements IBezierCurve{
	
	private static final int SEGMENT_COUNT = 50;
	private Vector startPointVector;
	private Vector control1Vector;
	private Vector control2Vector;
	private Vector endPointVector;
	private Vector[] vectors;
	private double myLength;
	
	public BezierCurve(double startX, double startY, double c1X, double c1Y, 
			double c2X, double c2Y, double endX, double endY) {

		startPointVector = new Vector(startX, startY);
		control1Vector = new Vector(c1X, c1Y);
		control2Vector = new Vector(c2X, c2Y);
		endPointVector = new Vector(endX, endY);
		addVectors();
		
		myLength = calculateBezierLength();
	}
	
	public BezierCurve(double[] points) {
		this(points[0], points[1], points[2], points[3], points[4],
				points[5], points[6], points[7]);
	}
	/**
	 * Bezier curves are defined by 4 vectors.  This intializes them from the first constructor
	 */
	private void addVectors() {
		this.vectors = new Vector[4];
		vectors[0] = startPointVector;
		vectors[1] = control1Vector;
		vectors[2] = control2Vector;
		vectors[3] = endPointVector;
	}
	/**
	 * Returns the vectors X-Y components and separated by a comma
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < vectors.length; i++) {
			Vector vector = vectors[i];
			sb.append(vector.getX());
			sb.append("-");
			sb.append(vector.getY());
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	/**
	 * Calculates the new coordinate given a time value t over the bezier curve
	 */
	public Vector calculateNewBezierPoint(double t){
		return calculateNewBezierPoint(t, startPointVector, control1Vector, control2Vector, endPointVector);
	}
	/**
	 * Calculates the new Tangent of the Bezier point given a time value t.
	 */
	public Vector calculateNewBezierTangent(double t){
		return calculateNewBezierTangent(t, startPointVector, control1Vector, control2Vector, endPointVector);
	}
	
	private Vector calculateNewBezierTangent(double t,
			Vector p0, Vector p1, Vector p2, Vector p3){
		
		Vector tangent = new Vector();
		
		double u = 1 - t;
		double tt = t*t;
		double uu = u*u;
		double uuu = uu * u;
		double ttt = tt * t;
		
		tangent = tangent.add(p0.scaleVect(-3 * uu));
		tangent = tangent.add(p1.scaleVect(3 * uu));
		tangent = tangent.add(p1.scaleVect(-6 * t * u));
		tangent = tangent.add(p2.scaleVect(-3 * tt));
		tangent = tangent.add(p2.scaleVect(6 * t * u));
		tangent = tangent.add(p3.scaleVect(3 * tt));
		
		return tangent;
		
	}
	
	private Vector calculateNewBezierPoint(double t,
			Vector p0, Vector p1, Vector p2, Vector p3){

		Vector p = new Vector();
		
		double u = 1 - t;
		double tt = t*t;
		double uu = u*u;
		double uuu = uu * u;
		double ttt = tt * t;

		p = p.add(p0.scaleVect(uuu));
		p = p.add(p1.scaleVect(3 * uu * t));
		p = p.add(p2.scaleVect(3 * u * tt));
		p = p.add(p3.scaleVect(ttt));

		return p;
	}
	/**
	 * returns myLength
	 */
	public double getLength(){
		return myLength;
	}
	
	private double calculateBezierLength(){
		
		Vector q0 = calculateNewBezierPoint(0, startPointVector, control1Vector, 
				control2Vector, endPointVector);
		double t;
		double length = 0;
		for(int i = 1; i <= SEGMENT_COUNT; i++){
			t = (double) i / (double) SEGMENT_COUNT;
			Vector q1 = calculateNewBezierPoint(t, startPointVector, control1Vector, 
				control2Vector, endPointVector);
			Vector q2 = calculateNewBezierTangent(t, startPointVector, control1Vector, 
					control2Vector, endPointVector);
			length += q1.calculateDistance(q0);

			q0 = q1;
		}
		
		return length;
		
	}
	/**
	 * Given velocity, calculate distance of bezier curve, and then divide to find time
	 * Since V = d/t, t = d/V, this is just a rough approximation. 
	 */
	public double calculateBezierTime(double velocity){
		//since V = d/t, t = d/V, this is just a rough approximation. 
		
		return myLength / velocity;
	}
	
	//given velocity, calculate distance of bezier curve, and then divide to find time

}
