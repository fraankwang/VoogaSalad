

package engine.backend.map;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import engine.backend.components.DisplayComponent;
import engine.backend.components.MovementComponent;
import engine.backend.components.PathComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.Vector;
import engine.backend.entities.IEntity;

/**
 * 
 * @author raghavkedia
 *
 */

public class Path implements IPath{
	
	//This class should contain a list or array of Bezier Curves
	
	private List<BezierCurve> myCurves;
	
	public Path(List<BezierCurve> curves) {
		this.myCurves = curves;
	}
	
	public Path() {
		myCurves = new ArrayList<BezierCurve>();
	}
	
	public void updatePositionOnPath(IEntity entity, ResourceBundle myComponentTagResources){
		
		PathComponent pathComponent = (PathComponent) entity.getComponent(myComponentTagResources.getString("Path"));
		double currBezTime = pathComponent.getBezierTime();

		//turn off display component and return
		if((currBezTime >= myCurves.size() - 0.01 && pathComponent.movesWithTime())){
			
			DisplayComponent dispComponent = (DisplayComponent) entity.getComponent(myComponentTagResources.getString("Display"));
			dispComponent.doNotShow();
			
			return;
		}
		
		PositionComponent posComponent = (PositionComponent) entity.getComponent(myComponentTagResources.getString("Position"));
		MovementComponent movComponent = (MovementComponent) entity.getComponent(myComponentTagResources.getString("Movement"));
		
		Vector newPos = new Vector();
		Vector newVel = new Vector();
		
		Vector velVector = movComponent.getCurrentVelocityVector();
		
		BezierCurve currCurve = getCurveFromTime(currBezTime);
		double speed = velVector.calculateMagnitude();
		double bezTimeStep = ((pathComponent.movesWithTime()) ? 1 : -1 ) * speed / currCurve.getLength();
		
		
		double newBezTime = currBezTime + bezTimeStep;
		
		BezierCurve newCurve = getCurveFromTime(newBezTime);
		newPos = newCurve.calculateNewBezierPoint(newBezTime - Math.floor(newBezTime));
		newVel = newCurve.calculateNewBezierTangent(newBezTime - Math.floor(newBezTime));
		newVel = newVel.normalize();
		newVel = newVel.scale(speed);
		
		pathComponent.setCurveID((int) Math.floor(newBezTime));
		posComponent.setPositionVector(newPos);
		pathComponent.setBezierTime(newBezTime);
		movComponent.setCurrentVelocityVector(newVel);
		
	}
	
	public void addCurve(BezierCurve curve){
		myCurves.add(curve);
	}
	
	//return the proper curve based on the bezTime of entity. 
	private BezierCurve getCurveFromTime(double bezTime){
		
		int numCurves = myCurves.size();
		int index = (int) Math.floor(bezTime); 
		if(index == numCurves){
			return myCurves.get(index - 1);
		}
		else if(index < 0){
			return myCurves.get(0);
		}
		return myCurves.get(index); 
		
	}
	
}
