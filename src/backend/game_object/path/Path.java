package backend.game_object.path;

import java.util.List;

import backend.game_object.components.MovementComponent;
import backend.game_object.components.PathComponent;
import backend.game_object.components.PositionComponent;
import backend.game_object.components.Vector;
import backend.game_object.entities.IEntity;

public class Path implements IPath{
	
	//This class should contain a list or array of Bezier Curves
	
	private List<BezierCurve> myCurves;
	
	public Path() {
		// TODO Auto-generated constructor stub
	}
	
	public Vector getNewPositionOnPath(IEntity entity){
		PathComponent pathComponent = (PathComponent) entity.getComponent("Path");
		PositionComponent posComponent = (PositionComponent) entity.getComponent("Position");
		MovementComponent movComponent = (MovementComponent) entity.getComponent("Movement");
		
		Vector newPos = new Vector();
		Vector velVector = movComponent.getCurrentVelocityVector();
		BezierCurve currCurve = myCurves.get(pathComponent.getCurveID());
		double speed = velVector.calculateMagnitude();
		double bezTimeStep = currCurve.getLength() / speed;
		
		
		
		return newPos;
	}
	
}
