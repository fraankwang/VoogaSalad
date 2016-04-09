package backend.game_object.path;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BezierCurveTest {

	double error = Math.pow(10, -7);
	@Test
	public void testBezierLengthForStraightLines() {
		BezierCurve curve = new BezierCurve(0,0, 0,0, 0,0, 2,0);
		assertEquals(1.0, curve.calculateBezierLength(), error);
		//curve = new BezierCurve(0,0, 0,0, 0,0, 3,4);
		//assertEquals(5.0, curve.calculateBezierLength(), error);
		//curve = new BezierCurve(-2,-1, -2,-1, -2,-1, 2,2);
		//assertEquals(5.0, curve.calculateBezierLength(), error);
	}
	
}
