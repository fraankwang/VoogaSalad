package engine.backend.map;


import org.junit.Test;

import engine.backend.components.DisplayComponent;
import engine.backend.components.MovementComponent;
import engine.backend.components.PathComponent;
import engine.backend.components.PositionComponent;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;

public class BezierCurveTest {

	double error = Math.pow(10, -7);
	@Test
	public void testBezierLengthForStraightLines() {
		BezierCurve curve = new BezierCurve(0,0, 0,0, 0,0, 2,0);
		//assertEquals(1.0, curve.calculateBezierLength(), error);
		//curve = new BezierCurve(0,0, 0,0, 0,0, 3,4);
		//assertEquals(5.0, curve.calculateBezierLength(), error);
		//curve = new BezierCurve(-2,-1, -2,-1, -2,-1, 2,2);
		//assertEquals(5.0, curve.calculateBezierLength(), error);
	}
	
	@Test
	public void testPathMovement(){
		IPath testPath = new Path();
		BezierCurve curve1 = new BezierCurve(0,0, 0,0, 0,0, 1,0);
		BezierCurve curve2 = new BezierCurve(1,0, 1,0, 1,0, 2,0);
		BezierCurve curve3 = new BezierCurve(2,0, 2,0, 2,0, 3,0);
		
		testPath.addCurve(curve1);
		testPath.addCurve(curve2);
		testPath.addCurve(curve3);
		
		IEntity testEntity = new Entity(1, null, null, error);
		MovementComponent movComponent = new MovementComponent(-.05, 0, 0);
		PositionComponent posComponent = new PositionComponent(3, 0);
		PathComponent pathComponent = new PathComponent(0, 0, false, 3);
		DisplayComponent dispComponent = new DisplayComponent();
		
		testEntity.addComponent(movComponent);
		testEntity.addComponent(posComponent);
		testEntity.addComponent(pathComponent);
		testEntity.addComponent(dispComponent);
		
		System.out.println(movComponent.getTag());
		
		for(int i = 0; i < 200; i++){
			testPath.updatePositionOnPath(testEntity);
			PositionComponent posComp = (PositionComponent) testEntity.getComponent("Position");
			PathComponent pathComp = (PathComponent) testEntity.getComponent("Path");
			//System.out.println(posComp.getX());
			//System.out.println(pathComp.getBezierTime());
		}
	}
	
}